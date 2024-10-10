package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.Credentials;
import com.example.demo.model.WrappedEntity;

import java.util.List;
//All available CRUD methods for Users
public interface ClientService {
    WrappedEntity<Client> saveClient(Client client);
    WrappedEntity<Client> updateClient(String username,
                                              String email, Client client);
    WrappedEntity<Client> getClient(String username, String email);
    void authClient(Credentials credentials);
    WrappedEntity<List<Client>> getAllClients();
    void deleteClient(String username, String email);
}
