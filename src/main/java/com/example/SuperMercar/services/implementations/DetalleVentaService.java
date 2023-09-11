package com.example.SuperMercar.services.implementations;

import com.example.SuperMercar.entities.DetalleVenta;
import com.example.SuperMercar.repositories.contracts.IDetalleVentaRepository;
import com.example.SuperMercar.services.contracts.IDetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaService implements IDetalleVentaService {

    @Autowired
    private IDetalleVentaRepository detalleVentaRepository;

    @Override
    public List<DetalleVenta> saveDetalleVenta(List<DetalleVenta> detalleVentas) {
        return detalleVentaRepository.saveAll(detalleVentas);
    }
}
