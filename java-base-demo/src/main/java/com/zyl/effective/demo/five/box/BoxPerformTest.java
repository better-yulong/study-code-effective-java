package com.zyl.effective.demo.five.box;

import java.util.Date;

public class BoxPerformTest {

	public static void main(String[] args) {
		Date startDate = new Date();
		System.out.println("start: " + startDate.getTime());
		
		Long sum = 0L ;
		for(long i=0 ;i<Integer.MAX_VALUE;i++){
			sum += i ;
		}
		System.out.println("sum:" + sum);
		Date middleDate = new Date();
		System.out.println("first: " + (middleDate.getTime() - startDate.getTime()));
		
		
		long sum2 = 0L ;
		for(long j=0 ;j<Integer.MAX_VALUE;j++){
			sum2 += j ;
		}
		System.out.println("sum2:" + sum2);
		
		Date endDate = new Date();
		System.out.println("end: " + (endDate.getTime() - middleDate.getTime()));
	}

}
/*
 * 
  运行结果：
	start: 1557132832635
	sum:2305843005992468481
	first: 32906
	sum2:2305843005992468481
	end: 5906
 * 
 * 
 * 
 */