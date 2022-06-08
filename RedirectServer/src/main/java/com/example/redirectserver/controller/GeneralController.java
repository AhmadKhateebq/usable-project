package com.example.redirectserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class GeneralController {
    @RequestMapping(value ="/")
    public HttpServletRequest get(HttpServletRequest request){
        return request;
    }
}
