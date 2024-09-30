package com.sahil.programming.userservice.controller;

import com.sahil.programming.userservice.dtos.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class UserController {
    public SignUpResponseDto signup(SignUpRequestDto requestDto){
        return null;
    }

    public LoginResponseDto login(LoginRequestDto requestDto){
        return null;
    }

    public UserDto validate(@RequestHeader("Authorization") String token){
        return null;
    }

    public void logout(){

    }
}
