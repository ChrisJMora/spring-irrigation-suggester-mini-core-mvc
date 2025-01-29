package com.example.demo.application.ports.services;

import com.example.demo.domain.models.UserEntity;
import com.example.demo.domain.models.WrappedEntity;

import java.util.List;
//All available CRUD methods for Users
public interface UserService {
    WrappedEntity<UserEntity> saveUser(UserEntity user);
    WrappedEntity<UserEntity> updateUser(String username, String email, UserEntity user);
    WrappedEntity<UserEntity> getUser(String username, String email);
    WrappedEntity<List<UserEntity>> getAllUsers();
    void deleteUser(String username, String email);
}
