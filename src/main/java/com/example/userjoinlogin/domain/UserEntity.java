package com.example.userjoinlogin.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_name")
    private String userName;
    
    @Column
    private String password;
    
    @Column
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    protected UserEntity() {};

    public static UserEntity of(String userName, String password, String email) {
        UserEntity user = new UserEntity();
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }
}
