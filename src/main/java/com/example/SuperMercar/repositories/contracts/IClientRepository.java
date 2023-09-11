package com.example.SuperMercar.repositories.contracts;

import com.example.SuperMercar.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, Long> {
}
