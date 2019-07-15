package com.aoe.demo.rpc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aoe.demo.rpc.hessian.HessianExampleInterf1;

@Controller
public class EntryController {
	
	@Autowired
	private HessianExampleInterf1 hessianExampleService;
	
	@RequestMapping("/entry")
	@ResponseBody
	public Object entry(){
		List<String> params =  new ArrayList<String>();
		params.add("parm");
		System.out.println("result0:" + hessianExampleService.getServiceNames(params).get(0));
		return "entry";
	}

}
