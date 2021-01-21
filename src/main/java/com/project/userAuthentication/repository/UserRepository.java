package com.project.userAuthentication.repository;

import com.project.userAuthentication.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findFirstByEmail(String email);

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findFirstByUserName(String userName);
}
