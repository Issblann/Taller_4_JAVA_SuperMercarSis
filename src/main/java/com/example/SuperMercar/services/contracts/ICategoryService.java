
package com.example.SuperMercar.services.contracts;

import com.example.SuperMercar.entities.Category;
import java.util.List;
import java.util.Optional;


public interface ICategoryService {
    List<Category>findAll();
    Optional<Category> findById(Long id);
    void save(Category category);
    void deleteById(Long id);
}
