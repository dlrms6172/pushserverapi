package com.example.push.api.common.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController implements ErrorController {



    @Override
    public String getErrorPath() {
        return "/error";
    }
}
