package com.revature.caliber.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BootController {
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String homePage() {
        return "index";
    }
}