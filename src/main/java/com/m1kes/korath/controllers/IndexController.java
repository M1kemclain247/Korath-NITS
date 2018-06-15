package com.m1kes.korath.controllers;

import com.m1kes.korath.NITS_Core;
import com.m1kes.korath.objects.Host;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView index(@RequestParam(value = "category" ,required = false) Integer id ,
                              @RequestParam(value = "sort" ,required = false) String sort) {

        System.out.println("Sort order is : "+ sort);

        List<Host> hosts = NITS_Core.getMain().getDaoManager().getHosts().getForCategory( id == null || id == 0 ? 1 : id);
        ModelAndView map = new ModelAndView("index");
        map.addObject("hosts", hosts);

        return map;
    }



}