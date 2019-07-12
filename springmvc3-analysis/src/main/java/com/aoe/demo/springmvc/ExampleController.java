package com.aoe.demo.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller(value="/exampleController")
//@RequestMapping("/example")
public class ExampleController {

    @RequestMapping("/helloWorld")
    @ResponseBody
    public String helloWorld_m(Model model) {
        model.addAttribute("message", "Hello World!");
        return "ExampleController helloWorld...";
    }
/*    
    @RequestMapping("/exampleController")
    @ResponseBody
    public String exampleController(Model model) {
    	model.addAttribute("message", "Hello World!");
    	return "ExampleController exampleController...";
    }*/
}