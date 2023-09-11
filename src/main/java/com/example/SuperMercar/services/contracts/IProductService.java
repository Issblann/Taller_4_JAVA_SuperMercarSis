
package com.example.SuperMercar.services.contracts;

import com.example.SuperMercar.entities.Product;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

public interface IProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    void save(Product product);
    void deleteById(Long id);
    
}
