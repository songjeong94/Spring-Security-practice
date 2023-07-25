package com.example.userjoinlogin.dto.response;

import com.example.userjoinlogin.domain.UserEntity;
import com.example.userjoinlogin.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinResponse {

    private String userName;
    private String email;

    public static UserJoinResponse fromEntity(UserEntity userEntity) {
        return new UserJoinResponse(
        userEntity.getUserName(),
        userEntity.getEmail());
    }

    public static UserJoinResponse fromUserDto(UserDto userDto) {
        return new UserJoinResponse(
                userDto.getUserName(),
                userDto.getEmail());
    }
}
