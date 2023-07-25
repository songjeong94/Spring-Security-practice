package com.example.userjoinlogin.service;

import com.example.userjoinlogin.domain.UserEntity;
import com.example.userjoinlogin.dto.UserDto;
import com.example.userjoinlogin.dto.request.UserJoinRequest;
import com.example.userjoinlogin.dto.request.UserLoginRequest;
import com.example.userjoinlogin.exception.ErrorCode;
import com.example.userjoinlogin.exception.SnsApplicationException;
import com.example.userjoinlogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encode;

    @Transactional
    public UserDto join(UserJoinRequest user) {
        userRepository.findByUserName(user.getUserName()).ifPresent(it -> {
                throw new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME, String.format("%s is duplicated name", user.getEmail()));
        });
        UserEntity userEntity = UserEntity.of(user.getUserName(), encode.encode(user.getPassword()), user.getEmail());

        userRepository.save(userEntity);
        return UserDto.fromEntity(userEntity);
    }

    public UserDto login(UserLoginRequest user) {
        UserEntity userEntity = userRepository.findByUserName(user.getUserName()).orElseThrow(() -> new SnsApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s user name is not founded", user.getUserName())));
        if(encode.matches(user.getPassword(), userEntity.getPassword())) {
            return UserDto.fromEntity(userEntity);
        } else throw new SnsApplicationException(ErrorCode.INVALID_PASSWORD);
    }



}