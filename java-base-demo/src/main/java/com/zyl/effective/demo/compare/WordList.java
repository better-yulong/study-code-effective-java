package com.zyl.effective.demo.compare;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class WordList {

	public static void main(String[] args) {
		 	Set<String> s = new TreeSet<>();
	        Collections.addAll(s, new String[]{"a","e","c"});
	        System.out.println(s);

	}
}


/**
 * 结果：[a, c, e]
 * */
