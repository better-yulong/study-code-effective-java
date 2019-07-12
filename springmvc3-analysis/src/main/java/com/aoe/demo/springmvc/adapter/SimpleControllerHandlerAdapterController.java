package com.aoe.demo.springmvc.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/*
 import org.springframework.web.servlet.mvc.AbstractController;
 
 @Controller(value="/beanNameUrlHttp")
public class SimpleControllerHandlerAdapterController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		  ModelAndView model = new ModelAndView("/index.jsp");
	      model.addObject("message", "Welcome!");
	      return model;
	}

}
*/

@Controller(value="/beanNameUrlHttp")
public class SimpleControllerHandlerAdapterController implements org.springframework.web.servlet.mvc.Controller {

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 ModelAndView model = new ModelAndView("/index.jsp");
	      model.addObject("message", "Welcome!");
	      return model;
	}
} 