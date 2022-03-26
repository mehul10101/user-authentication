package com.project.userAuthentication.utils;

import com.project.userAuthentication.entities.UserEntity;
import com.project.userAuthentication.pojo.requests.SignUpRequest;
import com.project.userAuthentication.pojo.responses.SignUpResponse;
import com.project.userAuthentication.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class RegistrationUtilTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final RegistrationUtil registrationUtil = new RegistrationUtil(userRepository);
    SignUpRequest signUpRequest = getSignUpRequest();
    SignUpResponse signUpResponse = getSignUpResponse();
    Optional<UserEntity> userEntityOptional = getUserEntityOptional();

    private final String EMAIL_ALREADY_REGISTERED = "email not valid or already signed up";
    private final String USER_NAME_ALREADY_REGISTERED = "please try with different user name, "
            + signUpRequest.getUserName() + " this is already taken";
    private final String PASSWORD_TOO_SHORT = "password too short please give alteast 8 characters";
    private Optional<UserEntity> getUserEntityOptional() {
        UserEntity userEntity = new UserEntity();
        return Optional.of(userEntity);
    }

    private SignUpRequest getSignUpRequest() {
        return new SignUpRequest("email.com", "pass", "userN", "123", "name");
    }

    private SignUpResponse getSignUpResponse() {
        return new SignUpResponse();
    }

    @Test
    void emailUserNamePasswordUserValidationsUserEmailPresentTest() {
        when(userRepository.findFirstByEmail(signUpRequest.getEmail())).thenReturn(userEntityOptional);
        SignUpResponse signUpResponseActual =
                registrationUtil.emailUserNamePasswordUserValidations(signUpRequest, signUpResponse);
        // to check whether, out expected is equal to the actual.
        // So if someone does some unexpected changes to the function this test will break
        assertEquals(EMAIL_ALREADY_REGISTERED, signUpResponseActual.getResponseMessage());
    }

    @Test
    void emailUserNamePasswordUserValidationsUserEmailNotValid() {
        signUpRequest.setEmail("email");
        when(userRepository.findFirstByEmail(signUpRequest.getEmail())).thenReturn(Optional.empty());
        SignUpResponse signUpResponseActual =
                registrationUtil.emailUserNamePasswordUserValidations(signUpRequest, signUpResponse);
        assertEquals(EMAIL_ALREADY_REGISTERED, signUpResponseActual.getResponseMessage());
    }

    @Test
    void emailUserNamePasswordUserValidationsUserNameAlreadyPresent() {
        signUpRequest.setEmail("email@domain.com");
        when(userRepository.findFirstByEmail(signUpRequest.getEmail())).thenReturn(Optional.empty());
        when(userRepository.findFirstByUserName(signUpRequest.getUserName())).thenReturn(userEntityOptional);
        SignUpResponse signUpResponseActual =
                registrationUtil.emailUserNamePasswordUserValidations(signUpRequest, signUpResponse);
        assertEquals(USER_NAME_ALREADY_REGISTERED, signUpResponseActual.getResponseMessage());
    }

    @Test
    void emailUserNamePasswordUserValidationsPasswordValidation() {
        signUpRequest.setPassword("pass");
        signUpRequest.setEmail("email@domain.com");
        when(userRepository.findFirstByEmail(signUpRequest.getEmail())).thenReturn(Optional.empty());
        when(userRepository.findFirstByUserName(signUpRequest.getUserName())).thenReturn(Optional.empty());
        SignUpResponse signUpResponseActual =
                registrationUtil.emailUserNamePasswordUserValidations(signUpRequest, signUpResponse);
        assertEquals(PASSWORD_TOO_SHORT, signUpResponseActual.getResponseMessage());
    }

    @Test
    void emailUserNamePasswordUserValidationsSuccessValidation() {
        signUpRequest.setPassword("password");
        signUpRequest.setEmail("email@domain.com");
        when(userRepository.findFirstByEmail(signUpRequest.getEmail())).thenReturn(Optional.empty());
        when(userRepository.findFirstByUserName(signUpRequest.getUserName())).thenReturn(Optional.empty());
        SignUpResponse signUpResponseActual =
                registrationUtil.emailUserNamePasswordUserValidations(signUpRequest, signUpResponse);
        assertNull(signUpResponseActual);
    }
}