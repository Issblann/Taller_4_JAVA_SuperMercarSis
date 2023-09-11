package com.example.SuperMercar.controllers.DTO;

import com.example.SuperMercar.entities.Client;
import com.example.SuperMercar.entities.DetalleVenta;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaDTO {

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String fecha;
    private Client client;
    private List<DetalleVenta> DetalleVenta = new ArrayList<>();
    private BigDecimal totalVenta;


}
