package com.example.userjoinlogin.dto;

import com.example.userjoinlogin.domain.UserEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String userName;
    private String password;
    private String email;

    public static UserDto of(String userName, String password, String email) {
        UserDto user = new UserDto();
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }

    public static UserDto fromEntity(UserEntity userEntity) {
        return UserDto.of(userEntity.getUserName(), userEntity.getPassword(), userEntity.getEmail());
    }

}
