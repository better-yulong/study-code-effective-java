package com.aoe.demo.rpc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RpcController {
	
	@RequestMapping("/rpc")
	@ResponseBody
	public Object rpc(){
		return "rpc...";
	}

}
