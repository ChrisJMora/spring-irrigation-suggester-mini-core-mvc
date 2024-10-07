package com.example.demo.controller;

import com.example.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.ClientServiceImp;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientServiceImp userService;

    // Add a new user
    @PostMapping("/create")
    public ResponseEntity<Client> addUser(@RequestBody Client newClient){
        try {
            return ResponseEntity.ok(userService.saveClient(newClient));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Get user by id
    @GetMapping("/read/{id}")
    public ResponseEntity<Client> getUserbyId(@PathVariable("id") long id){
        try {
            // get user from database by id, throw exception if not found
            return ResponseEntity.ok(userService.getClientById(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Get all users
    @GetMapping("/read")
    public ResponseEntity<List<Client>> getAllUsers(){
        try {
            return ResponseEntity.ok(userService.getAllClients());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Update user by id
    @PutMapping("/update/{id}")
    public ResponseEntity<Client> updateUser(@PathVariable long id, @RequestBody Client client){
        try {
            // get user from database by id, throw exception if not found
            Client _client = userService.getClientById(id);
            _client.setId(id);
            _client.setFirstName(client.getFirstName());
            _client.setLastName(client.getLastName());
            _client.setEmail(client.getEmail());
            _client.setPassword(client.getPassword());
            // Save updated user
            return ResponseEntity.ok(userService.saveClient(_client));
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    // Delete user by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        try {
            userService.deleteClient(id);
            return ResponseEntity.ok("User deleted");
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
