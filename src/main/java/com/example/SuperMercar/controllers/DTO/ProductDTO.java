package com.example.SuperMercar.controllers.DTO;
import com.example.SuperMercar.entities.Category;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Category category;
}
