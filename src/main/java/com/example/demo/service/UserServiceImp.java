package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.persistence.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public User saveUser(User newUser) {
        return repository.save(newUser);
    }

    @Override
    public User getUserById(long id) throws Exception {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) throw new Exception();
        return user.get();
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public void deleteUser(long id) {
        repository.deleteById(id);
    }
}
