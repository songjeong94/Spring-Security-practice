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

    private String userName;
    private String email;

    public static UserLoginResponse fromDto(UserDto userDto) {
        return new UserLoginResponse(
                userDto.getUserName(),
                userDto.getEmail()
        );
    }

    public static UserLoginResponse fromEntity(UserEntity user) {
        return new UserLoginResponse(
                user.getUserName(),
                user.getEmail()
        );
    }
}
