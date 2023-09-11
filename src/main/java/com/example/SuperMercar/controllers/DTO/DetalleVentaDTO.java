package com.example.SuperMercar.controllers.DTO;

import com.example.SuperMercar.entities.Product;
import com.example.SuperMercar.entities.Venta;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaDTO {
    private Long id;
    private Venta venta;
    private Product product;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
}
