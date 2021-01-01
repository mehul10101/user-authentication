package com.project.uberAuthentication.services;

import com.project.uberAuthentication.entities.UserEntity;
import com.project.uberAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findFirstByUserName(userName);
        userEntityOptional.orElseThrow(() -> new UsernameNotFoundException("Notfound"));
        return MyUserDetails.build(userEntityOptional.get());
    }
}
