package com.example.demo.persistence;

import com.example.demo.model.admin.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(String name);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByNameOrEmail(String name, String email);
}
