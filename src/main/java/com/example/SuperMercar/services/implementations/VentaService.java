package com.example.SuperMercar.services.implementations;

import com.example.SuperMercar.entities.Client;
import com.example.SuperMercar.entities.DetalleVenta;
import com.example.SuperMercar.entities.Product;
import com.example.SuperMercar.entities.Venta;
import com.example.SuperMercar.repositories.contracts.IDetalleVentaRepository;
import com.example.SuperMercar.repositories.contracts.IProductRepository;
import com.example.SuperMercar.repositories.contracts.IVentaRepository;
import com.example.SuperMercar.services.contracts.IVentaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository ventaRepository;

    @Autowired
    private IProductRepository productRepository;

    private IDetalleVentaRepository detalleVentaRepository;

    @Override
    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> findById(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    @Transactional
    public Venta save(Venta venta) {
        BigDecimal totalVenta = BigDecimal.ZERO;

        Venta ventaSaved = ventaRepository.save(venta);

        for (DetalleVenta detalle: venta.getDetalleVenta()) {
            Product product = productRepository.findById(detalle.getProduct().getId()).orElse(null);
            if (product != null){
                BigDecimal precioActual = product.getPrice();
                detalle.setPrecioUnitario(precioActual);

                BigDecimal subtotal = precioActual.multiply(BigDecimal.valueOf(detalle.getCantidad()));
                detalle.setSubtotal(subtotal);
                totalVenta = totalVenta.add(subtotal);

                detalle.setVenta(ventaSaved);
                detalleVentaRepository.save(detalle);
            }
        }
        return ventaRepository.save(ventaSaved);
    }

    @Override
    public void deleteById(Long id) {
        ventaRepository.deleteById(id);
    }

}
