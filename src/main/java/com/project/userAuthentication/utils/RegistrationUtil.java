package com.project.userAuthentication.utils;

import com.project.userAuthentication.pojo.requests.SignUpRequest;
import com.project.userAuthentication.pojo.responses.SignUpResponse;
import com.project.userAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RegistrationUtil {

    @Autowired
    private UserRepository userRepository;


    public SignUpResponse emailUserNamePasswordUserValidations(SignUpRequest signUpRequest, SignUpResponse signUpResponse) {
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
        return null;
    }

    // basic regex to valid email: https://www.geeksforgeeks.org/check-email-address-valid-not-java/
    private static boolean isValid(String email){
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
