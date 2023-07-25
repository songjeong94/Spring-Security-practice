package com.example.userjoinlogin.service;

import com.example.userjoinlogin.domain.UserEntity;
import com.example.userjoinlogin.dto.UserDto;
import com.example.userjoinlogin.dto.request.UserJoinRequest;
import com.example.userjoinlogin.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

//    @Test
//    void WhenUserJoin_Success() {
//        String userName = "song";
//        String password = "password";
//        String email = "song@example.com";
//        UserEntity user = UserEntity.of(userName, password, email);
//
//        when(userRepository.findByUserName(userName)).thenReturn(Optional.empty());
//        when(userRepository.save(any())).thenReturn(userName, password, email);
//
//        assertDoesNotThrow(() -> userService.join(user));
//    }

//    @Test
//    void WhenUserJoin_Fail_
}