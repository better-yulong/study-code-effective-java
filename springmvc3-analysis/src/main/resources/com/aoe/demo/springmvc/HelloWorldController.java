package com.aoe.demo.springmvc;

@Controller
public class HelloWorldController {

    @RequestMapping("/hello/helloWorld")
    public String helloWorld(Model model) {
        model.addAttribute("message", "Hello World!");
        return "helloWorld";
    }
}