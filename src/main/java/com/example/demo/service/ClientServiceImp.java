package com.example.demo.service;

import com.example.demo.exception.ClientUsernameOrEmailAreadyExistsException;
import com.example.demo.exception.IncorrectCredentialsException;
import com.example.demo.exception.NoSuchClientExistsException;
import com.example.demo.model.Client;
import com.example.demo.model.Credentials;
import com.example.demo.model.WrappedEntity;
import com.example.demo.utils.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.persistence.ClientRepository;

import java.util.*;

@Service
public class ClientServiceImp implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public WrappedEntity<Client> saveClient(Client client) {
        Map<String, String> details = new HashMap<>();
        if (ClientUsernameIsValid(client.getUsername())) {
            details.put("username", "The username already exists ");
        }
        if (ClientEmailIsValid(client.getEmail())) {
            details.put("email", "The email aready exists");
        }
        if (details.isEmpty()) {
            client.setPassword(AuthenticationUtil.hashPassword(client.getPassword()));
            return new WrappedEntity<>(clientRepository.save(client));
        } else {
            throw new ClientUsernameOrEmailAreadyExistsException("Invalid " +
                    "input", details);
        }
    }

    @Override
    public WrappedEntity<List<Client>> getAllClients() {
        return new WrappedEntity<>(clientRepository.findAll());
    }

    @Override
    public WrappedEntity<Client> getClient(String username, String email) {
        return new WrappedEntity<>(getClientByUsernameOrEmail(username, email));
    }

    @Override
    public void authClient(Credentials credentials) {
        Client client =
                getClientByUsernameOrEmail(credentials.getIdentifier(),
                        credentials.getIdentifier());
        if (!AuthenticationUtil.verifyPassword(credentials.getPassword(),
                client.getPassword())) {
            throw new IncorrectCredentialsException("Login failed. Please verify your " +
                    "credentials and try again.");
        }
    }

    @Override
    public WrappedEntity<Client> updateClient(String username,
                                              String email, Client client) {
        Map<String, String> details = new HashMap<>();  // Error details
        Client _client = getClientByUsernameOrEmail(username, email);

        // Check if the username is different from the previous one, then
        // verify if is valid.
        if (username != null && !_client.getUsername().equals(username)) {
            if (ClientUsernameIsValid(client.getUsername())) {
                details.put("username", "The username already exists ");
            }
        }

        // Check if the email is different from the previous one, then verify
        // if is valid.
        if (email != null && !_client.getEmail().equals(email)) {
            if (ClientEmailIsValid(client.getEmail())) {
                details.put("email", "The email aready exists");
            }
        }

        if (details.isEmpty()) {
            _client.setUsername(client.getUsername());
            _client.setEmail(client.getEmail());
            _client.setFirstName(client.getFirstName());
            _client.setLastName(client.getLastName());
            return new WrappedEntity<>(clientRepository.save(_client));
        } else {
            throw new ClientUsernameOrEmailAreadyExistsException("Invalid " +
                    "input", details);
        }
    }

    @Override
    public void deleteClient(String username, String email) {
        Client client = getClientByUsernameOrEmail(username, email);
        clientRepository.delete(client);
    }

    private boolean ClientUsernameIsValid(String username) {
        Optional<Client> _client = clientRepository.findByUsername(username);
        return _client.isPresent();
    }

    private boolean ClientEmailIsValid(String email) {
        Optional<Client> _client = clientRepository.findByEmail(email);
        return _client.isPresent();
    }

    private Client getClientByUsernameOrEmail(String username, String email) {
        Optional<Client> client =
                clientRepository.findByUsernameOrEmail(username, email);
        if (client.isEmpty()) {
            throw new NoSuchClientExistsException("No client was found");
        } else {
            return client.get();
        }
    }
}
