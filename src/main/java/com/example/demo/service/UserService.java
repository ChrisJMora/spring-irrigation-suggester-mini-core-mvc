package com.example.demo.service;

import com.example.demo.model.admin.UserEntity;
import com.example.demo.model.httpResponse.WrappedEntity;

import java.util.List;
//All available CRUD methods for Users
public interface UserService {
    WrappedEntity<UserEntity> saveUser(UserEntity user);
    WrappedEntity<UserEntity> updateUser(String username, String email, UserEntity user);
    WrappedEntity<UserEntity> getUser(String username, String email);
    WrappedEntity<List<UserEntity>> getAllUsers();
    void deleteUser(String username, String email);
}
