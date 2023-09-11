package com.example.SuperMercar.services.contracts;


import com.example.SuperMercar.entities.Client;
import com.example.SuperMercar.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IClientService {

    List<Client> findAll();
    Optional<Client> findById(Long id);
    void save(Client client);
    void deleteById(Long id);
}
