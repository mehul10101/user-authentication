package com.project.userAuthentication.services;

import com.project.userAuthentication.entities.UserEntity;
import com.project.userAuthentication.pojo.requests.SignUpRequest;
import com.project.userAuthentication.pojo.responses.SignUpResponse;
import com.project.userAuthentication.repository.UserRepository;
import com.project.userAuthentication.utils.RegistrationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class UserSignUpService {

    private final UserRepository userRepository;
    private final RegistrationUtil registrationUtil;

    @Autowired
    public UserSignUpService(UserRepository userRepository,
                             RegistrationUtil registrationUtil){
        this.userRepository = userRepository;
        this.registrationUtil = registrationUtil;
    }

    // business logic for sign up with some validations
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        SignUpResponse signUpResponse = new SignUpResponse();
        SignUpResponse signUpResponse1 = registrationUtil.emailUserNamePasswordUserValidations(signUpRequest, signUpResponse);
        if (signUpResponse1 != null) {
            return signUpResponse1;
        }
        signUpResponse.setResponseMessage(createUser(signUpRequest));
        return signUpResponse;
    }

    // creating user entity and saving it
    private String createUser(SignUpRequest signUpRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signUpRequest.getEmail());
        userEntity.setName(signUpRequest.getName());
        userEntity.setPhoneNumber(signUpRequest.getPhoneNumber());
        userEntity.setUserName(signUpRequest.getUserName());
        userEntity.setPassword(BCrypt.hashpw(signUpRequest.getPassword(), BCrypt.gensalt(12)));
        userRepository.save(userEntity);
        return "Welcome " + signUpRequest.getUserName() + ", account created successfully";
    }

}
