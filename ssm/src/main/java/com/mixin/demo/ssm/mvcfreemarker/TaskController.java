package com.mixin.demo.ssm.mvcfreemarker;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/task")
public class TaskController {

    @RequestMapping("/mvc1")
    public ModelAndView mvc1(ModelAndView modelAndView) {
        modelAndView.addObject("name","TaskController");
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
