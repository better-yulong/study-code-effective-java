package com.test.jvm.demo1;


/***
 * 为了多次载入执行类而加入 的加载器<br/>
 * 把defineClass开方法开放出来，只有外部显示调用 的时候才会使用到loadByte方法
 * 由虚拟机调用 时，仍然按照原有的双亲委派规则使用loadClass方法进行类加载
 * 
 * @author  zyl
 * */
public class HotSwapClassLoader extends ClassLoader {

		public HotSwapClassLoader(){
			//基于指定的类加载器创建新的ClassLoader，其中也会涉及到先基于SecurityManager检测SecurityConstants 的系统属性值  createClassLoader
			//自己定义的类加载器的父类加载器就是AppClassLoader，而AppClassLoader的父类加载器是ExtClassLoader，ExtClassLoader的父类加载器是BootstrapClassLoader,
			//而初始化AppClassLoader的时候将ExtClassLoader对象作为参数传给了AppClassLoader，从这里也就看到了为什么说AppClassLoader的父类加载器是ExtClassLoader。 
			super(HotSwapClassLoader.class.getClassLoader()); //即指定AppClassLoader 为当前自定义类加载器的父加载器---基于双亲委派模型
		}
		
		public Class loadByte(byte[] classByte) {
	        return defineClass(null, classByte, 0, classByte.length);
	    }
}
