package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.UserServiceImp;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceImp userService;

    // Add a new user
    @PostMapping("/create")
    public ResponseEntity<User> addUser(@RequestBody User newUser){
        try {
            return ResponseEntity.ok(userService.saveUser(newUser));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Get user by id
    @GetMapping("/read/{id}")
    public ResponseEntity<User> getUserbyId(@PathVariable("id") long id){
        try {
            // get user from database by id, throw exception if not found
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Get all users
    @GetMapping("/read")
    public ResponseEntity<List<User>> getAllUsers(){
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Update user by id
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user){
        try {
            // get user from database by id, throw exception if not found
            User _user = userService.getUserById(id);
            _user.setId(id);
            _user.setFirstName(user.getFirstName());
            _user.setLastName(user.getLastName());
            _user.setEmail(user.getEmail());
            _user.setPassword(user.getPassword());
            // Save updated user
            return ResponseEntity.ok(userService.saveUser(_user));
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    // Delete user by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted");
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
