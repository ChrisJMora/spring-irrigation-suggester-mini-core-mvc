package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.WrappedEntity;

import java.util.List;
//All available CRUD methods for Users
public interface ClientService {
    public WrappedEntity<Client> saveClient(Client client);
    public WrappedEntity<Client> updateClient(String username,
                                              String email, Client client);
    public WrappedEntity<Client> getClient(String username, String email);
    public WrappedEntity<List<Client>> getAllClients();
    public void deleteClient(String username, String email);
}
