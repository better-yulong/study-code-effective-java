package com.zyl.effective.demo.two.builder;

import com.zyl.effective.demo.two.builder.interf.Builder;

public class NutritionFacts {

	private final int servingSize ;
	private final int servings ;
	private final int calories;
	private final int fat ;
	private final int sodium ;
	private final int carbohydrate ;
	
	public static class NFBuilder implements Builder{
		//required paremeters
		private final int servingSize ;
		private final int servings ;
		
		//Optional paremeters
		private int calories = 0 ;
		private int fat = 0 ;
		private int sodium = 0 ;
		private int carbohydrate = 0 ;
		
		public NFBuilder(int servingSize,int servings){
			this.servingSize = servingSize ;
			this.servings = servings;
		}
		
		public NFBuilder calories(int val){
			this.calories = val;
			return this;
		}
		
		public NFBuilder fat(int val){
			this.fat = val;
			return this;
		}
		
		public NFBuilder sodium(int val){
			this.sodium = val;
			return this;
		}
		public NFBuilder carbohydrate(int val){
			this.carbohydrate = val;
			return this;
		}
		
		public NutritionFacts build(){
			//optional: add paremeter check
			return new NutritionFacts(this);
		}
	}
	
	private NutritionFacts(NFBuilder builder){
		//optional: add paremeter check
		this.servingSize = builder.servingSize ;
		this.servings = builder.servings ;
		this.calories = builder.calories ;
		this.fat = builder.fat ;
		this.sodium = builder.sodium ;
		this.carbohydrate = builder.carbohydrate ;
	}
	
	public static void main(String[] args){
		NutritionFacts cacaCola = new NutritionFacts.NFBuilder(240, 8).calories(100).sodium(20).carbohydrate(10).build();
		System.out.println(cacaCola.servingSize);
		System.out.println(cacaCola.sodium);
		System.out.println(cacaCola.fat);
	}
}

/***
 * Builder构建器模式，区别于重叠构造器、JavaBeans模式，可保证线程全亦可有较好的可读性.
 * 
 * 运行结果：
 *  240
	20
	0

Builder模式可使用公共接口
 * */
