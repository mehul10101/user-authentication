package com.project.userAuthentication.services;

import com.project.userAuthentication.repository.UserRepository;
import com.project.userAuthentication.security.jwt.JwtUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
class UserLoginServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final JwtUtils jwtUtil = mock(JwtUtils.class);
    private final AuthenticationManager authenticationManager = mock(AuthenticationManager.class);

    private final UserLoginService userLoginService = new UserLoginService(userRepository, jwtUtil, authenticationManager);

    @Test
    void addNumbersTest() {
        List<Integer> integers = new ArrayList<Integer>(){{
            add(5);
            add(7);
        }};
        int result = userLoginService.addNumbers(integers);
        assertEquals(12, result);
        assertNotEquals(5, result);
    }
}