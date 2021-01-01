package com.project.uberAuthentication.services;

import com.project.uberAuthentication.entities.UserEntity;
import com.project.uberAuthentication.pojo.SignUpRequest;
import com.project.uberAuthentication.pojo.SignUpResponse;
import com.project.uberAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Service
@Component
public class SignUpService {

    @Autowired
    private UserRepository userRepository;

    // business logic for sign up with some validations
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        SignUpResponse signUpResponse = new SignUpResponse();
        if(userRepository.findFirstByEmail(signUpRequest.getEmail()).isPresent() || !isValid(signUpRequest.getEmail())){
            signUpResponse.setResponseMessage("email not valid or already signed up");
            return signUpResponse;
        }
        if(userRepository.findFirstByUserName(signUpRequest.getUserName()).isPresent()){
            signUpResponse.setResponseMessage("please try with different user name, "+ signUpRequest.getUserName() +" this is already taken");
            return signUpResponse;
        }
        if (signUpRequest.getPassword().length() < 8){
            signUpResponse.setResponseMessage("password too short please give alteast 8 characters");
            return signUpResponse;
        }
        signUpResponse.setResponseMessage(createUser(signUpRequest));
        return signUpResponse;
    }

    // creating user entity and saving it
    private String createUser(SignUpRequest signUpRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signUpRequest.getEmail());
        userEntity.setUserName(signUpRequest.getUserName());
        userEntity.setPassword(BCrypt.hashpw(signUpRequest.getPassword(), BCrypt.gensalt(12)));
        userRepository.save(userEntity);
        return "Welcome " + signUpRequest.getUserName() + ", account created successfully";
    }

    // basic regex to valid email: https://www.geeksforgeeks.org/check-email-address-valid-not-java/
    public static boolean isValid(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();

    }

}
