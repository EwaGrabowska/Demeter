package com.demeter.userservice.controller;

import com.demeter.userservice.service.UserRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRegisterService userRegisterService;

    @GetMapping("/register")
    public String register(@RequestHeader("Authorization") String token){
        userRegisterService.registerUser(token.substring(7));
        return "Registration successfull.";
    }
}
