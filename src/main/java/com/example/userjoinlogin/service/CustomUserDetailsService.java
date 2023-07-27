package com.example.userjoinlogin.service;

import com.example.userjoinlogin.domain.UserEntity;
import com.example.userjoinlogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userDetails = userRepository.findByUserName(username);
        if(userDetails == null) {
            throw new UsernameNotFoundException(username);
        }
        return null;
    }

}
