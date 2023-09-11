package com.example.SuperMercar.controllers.DTO;
import com.example.SuperMercar.entities.Product;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
    private Long id;
    private String name;
    private List<Product> productList = new ArrayList<>();
}
