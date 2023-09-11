package com.example.SuperMercar.repositories.contracts;

import com.example.SuperMercar.entities.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}
