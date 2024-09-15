package com.example.Thawaq.Service;

import com.example.Thawaq.Model.Client;
import com.example.Thawaq.Repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    public Client getClientById(Integer id) {
        return clientRepository.findClientById(id);
    }
}
