
package com.example.SuperMercar.controllers;

import com.example.SuperMercar.controllers.DTO.ProductDTO;
import com.example.SuperMercar.services.contracts.IProductService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.SuperMercar.entities.Product;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private IProductService productService;
    
    @GetMapping("/list")
    public ResponseEntity<?> getAllProducts(){
        List<ProductDTO> productList = productService.findAll()
                .stream()
                .map(product -> ProductDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .category(product.getCategory())
                        .build())
                .toList();
        return  ResponseEntity.ok(productList);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> findProductById(@PathVariable Long id){
        Optional<Product> productOptional = productService.findById(id);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            ProductDTO productDTO = ProductDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .category(product.getCategory())
                    .build();
            return ResponseEntity.ok(productDTO);

        } return  ResponseEntity.notFound().build();
    }
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) throws URISyntaxException {
        if(productDTO.getName().isBlank()){

            return ResponseEntity.badRequest().build();
        }
        productService.save(Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .category(productDTO.getCategory())
                .build());
            return ResponseEntity.created(new URI("/product/save")).build();
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setCategory(productDTO.getCategory());
            productService.save(product);
            return  ResponseEntity.ok("Registro actualizado correctamente");
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id ){
        if(id != null){
            productService.deleteById(id);
            return ResponseEntity.ok("Registro eliminado correctamente");
        }
        return  ResponseEntity.badRequest().build();
    }
}
