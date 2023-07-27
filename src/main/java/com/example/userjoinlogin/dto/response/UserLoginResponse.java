package com.example.userjoinlogin.dto.response;

import com.example.userjoinlogin.domain.UserEntity;
import com.example.userjoinlogin.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse {

    private String token;
}
