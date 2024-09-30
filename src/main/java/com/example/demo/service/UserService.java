package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;
//All available CRUD methods for Users
public interface UserService {
    public User saveUser(User newUser);
    public User getUserById(long id) throws Exception;
    public List<User> getAllUsers();
    public void deleteUser(long id);
}
