package com.project.userAuthentication.services;

import com.project.userAuthentication.entities.UserEntity;
import com.project.userAuthentication.pojo.requests.LoginRequest;
import com.project.userAuthentication.pojo.responses.LoginResponse;
import com.project.userAuthentication.repository.UserRepository;
import com.project.userAuthentication.security.jwt.JwtUtils;
import com.project.userAuthentication.utils.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

// The service class will contain the business logic and will talk directly to the rest end point
@Service
@Component
@Slf4j
public class UserLoginService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserLoginService(UserRepository userRepository,
                            JwtUtils jwtUtil,
                            AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }


    public LoginResponse signIn(LoginRequest loginRequest) throws ApiError {
        Optional<UserEntity> userOptional = userRepository.findFirstByUserName(loginRequest.getUserName());
        UserEntity userEntity = getUserEntityAndValidateUserNameAndPassword(loginRequest, userOptional);
        log.info("login successful");

        return getLoginResponse(loginRequest, userEntity);

    }

    private LoginResponse getLoginResponse(LoginRequest loginRequest, UserEntity userEntity) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(userEntity.getId());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtUtil.generateJwtToken(authentication);
        loginResponse.setToken(token);
        return loginResponse;
    }

    //to validate userName and password using BCrypt
    private UserEntity getUserEntityAndValidateUserNameAndPassword(LoginRequest loginRequest, Optional<UserEntity> userOptional) throws ApiError {
        if(!userOptional.isPresent()){
            throw new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, "email not registered");
        }
        UserEntity userEntity = userOptional.get();
        if(!BCrypt.checkpw(loginRequest.getPassword(), userEntity.getPassword())){
            throw new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, "password doesn't match");
        }
        return userEntity;
    }

}
