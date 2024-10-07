package com.example.demo.service;

import com.example.demo.model.Client;

import java.util.List;
//All available CRUD methods for Users
public interface ClientService {
    public Client saveClient(Client newClient);
    public Client getClientById(long id) throws Exception;
    public List<Client> getAllClients();
    public void deleteClient(long id);
}
