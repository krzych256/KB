package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.service.CountryService;

@Controller
public class MainController {

  @Autowired
  private CountryService countryService;

  @RequestMapping("/")
  public String welcomes(Model model) {
    model.addAttribute("model", countryService.getAllCountries());
    return "index";
  }
}
