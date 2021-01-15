package com.project.userAuthentication.controllers;

import com.project.userAuthentication.pojo.requests.LoginRequest;
import com.project.userAuthentication.pojo.responses.LoginResponse;
import com.project.userAuthentication.pojo.requests.SignUpRequest;
import com.project.userAuthentication.pojo.responses.SignUpResponse;
import com.project.userAuthentication.services.UserLoginService;
import com.project.userAuthentication.services.UserSignUpService;
import com.project.userAuthentication.utils.ApiError;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/auth/user")
@Slf4j
public class UserAuthenticationController {

    @Autowired
    private final UserSignUpService signUpService;

    @Autowired
    private final UserLoginService loginService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws ApiError {
        log.info("got login request with user name {}", loginRequest.getUserName());
//        loginService = new UserLoginService();
        return loginService.signIn(loginRequest);
    }

    @PostMapping("/signUp")
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        log.info("request for creating an account with user name {}", signUpRequest.getUserName());
//        signUpService = new UserSignUpService();
        return signUpService.signUp(signUpRequest);
    }

}
