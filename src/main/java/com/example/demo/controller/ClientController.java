package com.example.demo.controller;

import com.example.demo.exception.ClientUsernameOrEmailAreadyExistsException;
import com.example.demo.exception.IncorrectCredentialsException;
import com.example.demo.exception.NoSuchClientExistsException;
import com.example.demo.model.*;
import com.example.demo.model.Error;
import com.example.demo.model.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.ClientServiceImp;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientServiceImp clientService;

    @PostMapping("/create")
    public ResponseEntity<ApiResult> addClient(@RequestBody Client client) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(clientService.saveClient(client));
        } catch (ClientUsernameOrEmailAreadyExistsException caee) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorDetail(caee.getMessage(), caee.getDetails()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResult> getAllClients() {
        try {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(clientService.getAllClients());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<ApiResult> authClient(@RequestBody Credentials credentials) {
        try {
            clientService.authClient(credentials);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResult(ResponseStatus.SUCCESS));
        } catch (NoSuchClientExistsException nscee) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Error(nscee.getMessage()));
        } catch (IncorrectCredentialsException ice) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Error(ice.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity<ApiResult> getClientByUsernameOrPassword(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(clientService.getClient(username, email));
        } catch (NoSuchClientExistsException nscee) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Error(nscee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResult> updateUser(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestBody Client client) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(clientService.updateClient(username, email, client));
        } catch (NoSuchClientExistsException nscee) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Error(nscee.getMessage()));
        } catch (ClientUsernameOrEmailAreadyExistsException caee) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorDetail(caee.getMessage(), caee.getDetails()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }

    // Delete user by id
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResult> deleteUser(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email) {
        try {
            clientService.deleteClient(username, email);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResult(ResponseStatus.SUCCESS));
        } catch (NoSuchClientExistsException nscee) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Error(nscee.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }
}
