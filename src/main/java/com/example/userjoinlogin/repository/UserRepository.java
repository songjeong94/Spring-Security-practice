package com.example.userjoinlogin.repository;

import com.example.userjoinlogin.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String userName);

    List<UserEntity> findByEmail(String email);

}
