package com.aoe.demo.rpc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EntryController {
	
	@RequestMapping("/entry")
	public Object entry(){
		return "entry";
	}

}
