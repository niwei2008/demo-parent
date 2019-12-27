package com.mixin.demo.ssm.mvcjsp;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/task2")
public class TaskController2 {

    @RequestMapping("/mvc2")
    public ModelAndView mvc2(Model model) {
        model.addAttribute("haha","动态设置的属性");
        return new ModelAndView("indexjsp");
    }
}