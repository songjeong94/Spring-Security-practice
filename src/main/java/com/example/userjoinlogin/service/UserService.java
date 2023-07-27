package com.example.userjoinlogin.service;

import com.example.userjoinlogin.domain.UserEntity;
import com.example.userjoinlogin.dto.UserDto;
import com.example.userjoinlogin.dto.request.UserJoinRequest;
import com.example.userjoinlogin.dto.request.UserLoginRequest;
import com.example.userjoinlogin.exception.ErrorCode;
import com.example.userjoinlogin.exception.SnsApplicationException;
import com.example.userjoinlogin.repository.UserRepository;
import com.example.userjoinlogin.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encode;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;

    public UserDto loadUserByUserName(String userName) {
        return userRepository.findByUserName(userName).map(UserDto::fromEntity).orElseThrow(() ->
                        new SnsApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", userName)));
    }

    @Transactional
    public UserDto join(UserJoinRequest user) {
        userRepository.findByUserName(user.getUserName()).ifPresent(it -> {
                throw new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME, String.format("%s is duplicated name", user.getEmail()));
        });

        UserEntity userEntity = UserEntity.of(user.getUserName(), encode.encode(user.getPassword()), user.getEmail());

        userRepository.save(userEntity);
        return UserDto.fromEntity(userEntity);
    }

    public String login(String userName, String password) {
        UserEntity userEntity = userRepository.findByUserName(userName).orElseThrow(() -> new SnsApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s user name is not founded", userName)));
        if(encode.matches(password, userEntity.getPassword())) {
        } else throw new SnsApplicationException(ErrorCode.INVALID_PASSWORD);

        String token = JwtTokenUtils.generateToken(userName,secretKey,expiredTimeMs);

        return token;
    }



}