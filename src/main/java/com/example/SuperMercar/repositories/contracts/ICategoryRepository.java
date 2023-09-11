
package com.example.SuperMercar.repositories.contracts;

import com.example.SuperMercar.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICategoryRepository extends JpaRepository<Category, Long>{
    
}
