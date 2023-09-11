package com.example.SuperMercar.services.contracts;

import com.example.SuperMercar.entities.DetalleVenta;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IDetalleVentaService {

    List<DetalleVenta> saveDetalleVenta(List<DetalleVenta> detalleVentas);

}
