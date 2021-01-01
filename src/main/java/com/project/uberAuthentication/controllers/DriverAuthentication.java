package com.project.uberAuthentication.controllers;

import com.project.uberAuthentication.pojo.LoginRequest;
import com.project.uberAuthentication.pojo.LoginResponse;
import com.project.uberAuthentication.pojo.SignUpRequest;
import com.project.uberAuthentication.pojo.SignUpResponse;
import com.project.uberAuthentication.services.LoginService;
import com.project.uberAuthentication.services.SignUpService;
import com.project.uberAuthentication.utils.ApiError;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/auth/driver")
public class DriverAuthentication {

    private final SignUpService signUpService;
    private final LoginService loginService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws ApiError {
        return loginService.signIn(loginRequest);
    }

    @PostMapping("/signUp")
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return signUpService.signUp(signUpRequest);
    }

}
