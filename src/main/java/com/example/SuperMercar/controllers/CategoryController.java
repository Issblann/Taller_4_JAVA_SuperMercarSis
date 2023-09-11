
package com.example.SuperMercar.controllers;

import com.example.SuperMercar.controllers.DTO.CategoryDTO;
import com.example.SuperMercar.entities.Category;
import com.example.SuperMercar.services.contracts.ICategoryService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    
    @GetMapping("/list")
    public ResponseEntity<?> getAllCategories(){
        List<CategoryDTO> categoryList = categoryService.findAll()
                .stream()
                .map(category -> CategoryDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .productList(category.getProductList())
                    .build())
                .toList();
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            CategoryDTO categoryDTO = CategoryDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .productList(category.getProductList())
                    .build();
            return  ResponseEntity.ok(categoryDTO);
        } return ResponseEntity.notFound().build();

    }
    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) throws URISyntaxException {
        if (categoryDTO.getName().isBlank()){
            return ResponseEntity.badRequest().build();
        }

        categoryService.save(Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .build());
        return ResponseEntity.created(new URI("/category/save")).build();
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        Optional<Category> categoryOptional = categoryService.findById(id);
        if(categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            category.setName(categoryDTO.getName());
            categoryService.save(category);
            return ResponseEntity.ok("Registro actualizado correctamente");
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long id){
        if (id != null){
            categoryService.deleteById(id);
            return ResponseEntity.ok("Registro eliminado correctamente");
        }

        return ResponseEntity.badRequest().build();
    }
    
    
}
