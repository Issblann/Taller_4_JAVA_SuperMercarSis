
package com.example.SuperMercar.repositories.contracts;

import com.example.SuperMercar.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
    
}
