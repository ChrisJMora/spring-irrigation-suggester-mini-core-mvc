package com.example.demo.service;

import com.example.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.persistence.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImp implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client saveClient(Client newClient) {
        return clientRepository.save(newClient);
    }

    @Override
    public Client getClientById(long id) throws Exception {
        Optional<Client> user = clientRepository.findById(id);
        if (user.isEmpty()) throw new Exception();
        return user.get();
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void deleteClient(long id) {
        clientRepository.deleteById(id);
    }
}
