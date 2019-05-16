package com.test.jvm;

import java.util.ArrayList;

public class FinalSyntaxCheck {
	
	public void foo(final int arg){
		final int var = 0 ;
		//do something
		//arg = 3 ;
		//var = 3 ;
	}
	
/*	public void foo(int arg){
		int var = 0 ;
	}*/

}
