package com.example.demo.interfaces.controllers;

import com.example.demo.domain.exceptions.admin.UserNameOrEmailAreadyExistsException;
import com.example.demo.domain.exceptions.admin.NoSuchUserExistsException;
import com.example.demo.domain.models.ApiResult;
import com.example.demo.domain.models.Error;
import com.example.demo.domain.models.ErrorDetail;
import com.example.demo.domain.models.ResponseStatus;
import com.example.demo.domain.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.demo.application.services.UserServiceImp;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImp userService;

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/add")
    public ResponseEntity<ApiResult> addClient(@RequestBody UserEntity user) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(userService.saveUser(user));
        } catch (UserNameOrEmailAreadyExistsException caee) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorDetail(caee.getMessage(), caee.getDetails()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/all")
    public ResponseEntity<ApiResult> getAllClients() {
        try {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(userService.getAllUsers());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping()
    public ResponseEntity<ApiResult> getClientByUsernameOrPassword(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userService.getUser(username, email));
        } catch (NoSuchUserExistsException nscee) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Error(nscee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PutMapping("/update")
    public ResponseEntity<ApiResult> updateUser(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestBody UserEntity user) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userService.updateUser(username, email, user));
        } catch (NoSuchUserExistsException nscee) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Error(nscee.getMessage()));
        } catch (UserNameOrEmailAreadyExistsException caee) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorDetail(caee.getMessage(), caee.getDetails()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResult> deleteUser(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email) {
        try {
            userService.deleteUser(username, email);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResult(ResponseStatus.SUCCESS));
        } catch (NoSuchUserExistsException nscee) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Error(nscee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }
}
