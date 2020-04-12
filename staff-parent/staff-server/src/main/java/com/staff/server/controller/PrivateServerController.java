package com.staff.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/server")
public class PrivateServerController {

    @RequestMapping("/hello")
    public String hello() {
       return "private hello";
    }
}
