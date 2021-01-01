package com.project.uberAuthentication.repository;

import com.project.uberAuthentication.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findFirstByEmail(String email);

    Optional<UserEntity> findFirstByUserName(String userName);
}
