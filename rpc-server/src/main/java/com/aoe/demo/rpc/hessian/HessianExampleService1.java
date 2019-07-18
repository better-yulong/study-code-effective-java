package com.aoe.demo.rpc.hessian;

import java.util.ArrayList;
import java.util.List;

public class HessianExampleService1 implements HessianExampleInterf1 {
	
	/* (non-Javadoc)
	 * @see com.aoe.demo.rpc.hessian.HessianExampleInterf1#getServiceNames(java.util.List)
	 */
	public List getServiceNames(List paramList){
		System.out.println("HessianExampleService1 param0:" + paramList.get(0));
		List serviceNames = new ArrayList<String>();
		serviceNames.add("service hessianExampleService1");
		return serviceNames ;
	}

}
