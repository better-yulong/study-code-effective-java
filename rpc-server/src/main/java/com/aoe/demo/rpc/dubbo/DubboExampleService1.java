package com.aoe.demo.rpc.dubbo;

import java.util.ArrayList;
import java.util.List;

public class DubboExampleService1 implements DubboExampleInterf1 {

	public List serviceProvider(List params) {
		System.out.println("DubboExampleService1 param0:" + params.get(0));
		List serviceNames = new ArrayList<String>();
		serviceNames.add("DubboExampleService1");
		return serviceNames ;
	}

}
