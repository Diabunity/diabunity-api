package com.diabunity.diabunityapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Swagger {
    @RequestMapping("/swagger")
    public String index(Model modelo) {
      return "index";
    }

}
