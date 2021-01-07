package com.openclassrooms.watchlist.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class CustomErrorController implements ErrorController {

    private final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

    public String getErrorPath(){
        return "/error";
    }

    @GetMapping("/error")
    public ModelAndView handleError(HttpServletResponse response){
        int code = response.getStatus();
        System.out.println("Error with code " + code + " Happened");
        logger.error("Error with code {} happened! Do something!", code);
        return new ModelAndView("error");

    }
}
