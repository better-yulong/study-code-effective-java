package com.test.jvm.javac;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner6;
import javax.tools.Diagnostic.Kind;

/**
 * 程序名称规范的编译器插件
 * 如果程序命名不规范，将会输出一个编译器的WARNING信息
 * */
public class NameChecker {
	private final Messager messager ;
	
	NameCheckScanner nameCheckScanner = new NameCheckScanner();
	
	NameChecker(ProcessingEnvironment processingEnv){
		this.messager = processingEnv.getMessager();
	}
	
	/**
	 * 对java程序命名进行检查，根据《Java语言规范（第3版）》第6.8节的要求，Java程序命名应当符合下列格式：
	 * 
	 * <ul>
	 * 	<li>类或接口：符合驼式命名法，首字母大写。</li>
	 * 	<li>方法：符合驼式命名法，首字母小写。</li>
	 * 	<li>字段：</li>
	 * <ul>
	 * <li>类、实例变量：符合驼式命名法，首字母小写。</li>
	 * <li>常量：要求全部大写及_，但不能以_开头。</li>
	 * </ul>
	 * </ul>
	 * 
	 * 
	 * */
	public void checkName(Element element){
		nameCheckScanner.scan(element);
	}
	

	private class NameCheckScanner extends ElementScanner6<Void,Void>{
	    /**
	     * 此方法用于检查Java类
	     *
	     */
		@Override
	    public Void visitType(TypeElement e, Void p) {
	         scan(e.getEnclosedElements(), p);
 			 checkCamelCase(e,true);
	         super.visitType(e, p);
	         return null;
	    }
		
		/**
		 * 检查方法命名是否合法
		 * */
		@Override
		public Void visitExecutable(ExecutableElement e, Void p) {
			if(e.getKind() == ElementKind.METHOD){
				Name name = e.getSimpleName();
				if(name.contentEquals(e.getEnclosingElement().getSimpleName())){
					//默认是可存在与类名相同的非构造函数方法的
	    			messager.printMessage(Kind.WARNING, "一个普通方法" + name + "不应当与类名重复，避免与构造函数产生混淆", e);
	    			checkCamelCase(e,false);
				}
				super.visitExecutable(e, p);
			}
			return null;
		}

		/**
		 * 检查变量命名是否合法
		 * */
	    @Override
		public Void visitVariable(VariableElement e, Void p) {
			//如果这个Variable是检举或常量，则按大写命名检查；否则按照驼式命名法规则检查（首字母小写）
	    	if(e.getKind()==ElementKind.ENUM_CONSTANT || e.getConstantValue()!=null || heuristicallyConstant(e))
	    		checkAllCaps(e);
	    	else
	    		checkCamelCase(e,false);
	    	
			return null;
		}

	    /**
	     * 判断一个变量是否为常量
	     * */
	    private boolean heuristicallyConstant(VariableElement e){
	    	if(e.getEnclosingElement().getKind()==ElementKind.INTERFACE)
	    		return true;
	    	else if(e.getKind()==ElementKind.FIELD && e.getModifiers().containsAll(new HashSet<>(Arrays.asList(new Integer[]{Modifier.PUBLIC,Modifier.STATIC,Modifier.FINAL}))))
	    		return true;
	    	else
	    		return false;
	    }
	    
	    public void checkCamelCase(Element e,boolean initiallCaps){
	    	String name = e.getSimpleName().toString();
	    	boolean previousUpper = false ;
	    	boolean conventional = true ;
	    	int firstCodePoint = name.codePointAt(0);
	    	if(Character.isUpperCase(firstCodePoint)){
	    		previousUpper = true ;
	    		if(!initiallCaps){
	    			messager.printMessage(Kind.WARNING, "名称" + name + "应当以小写字母开头", e);
	    			return ;
	    		}
	    	}else if(Character.isLowerCase(firstCodePoint)){
	    		if(initiallCaps){
	    			messager.printMessage(Kind.WARNING, "名称" + name + "应当以大写字母开头", e);
	    			return ;
	    		}
	    	}else{
	    		conventional = false ;
	    	}
	    	
	    	if(conventional){
	    		int cp = firstCodePoint ;
	    		for(int i = Character.charCount(cp);i<name.length();i+=Character.charCount(cp)){
	    			cp = name.codePointAt(i);
	    			if(Character.isUpperCase(cp)){
	    				if(previousUpper){
	    					conventional = false;
	    					break;
	    				}
	    				previousUpper = true ;
	    			}else{
	    				previousUpper = false ;
	    			}
	    		}
	    	}
	    	
	    	if(!conventional){
    			messager.printMessage(Kind.WARNING, "名称" + name + "应当符合驼峰式命名法（Camel Case Names）", e);
	    	}
	    	
	    }
	    

		private void checkAllCaps(Element e){
	    	String name = e.getSimpleName().toString();
	    	boolean conventional = true ;
	    	int firstCodePoint = name.codePointAt(0);
	    	
	    	if(!Character.isUpperCase(firstCodePoint)){
	    		conventional = false ;
	    	}else{
	    		boolean previousUnderscore = false ;
	    		int cp = firstCodePoint ;
	    		for(int i= Character.charCount(cp);i<name.length();i+=Character.charCount(cp)){
	    			cp = name.codePointAt(i);
	    			if(cp ==(int)'_'){
	    				if(previousUnderscore){
	    					conventional = false;
	    					break ;
	    				}
	    				previousUnderscore = true;
	    			}else{
	    				previousUnderscore = false ;
	    				if(!Character.isUpperCase(cp) && !Character.isLowerCase(cp)){
	    					conventional = false ;
	    					break ;
	    				}
	    			}
	    		}
	    	}
	    	
	    	if(!conventional){
    			messager.printMessage(Kind.WARNING, "常量" + name + "应当全部以大写字母或下划线命名并且以字母开头", e);
	    	}
	    }
	}
}
