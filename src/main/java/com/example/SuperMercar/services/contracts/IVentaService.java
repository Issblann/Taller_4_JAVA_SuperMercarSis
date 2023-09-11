package com.example.SuperMercar.services.contracts;
import com.example.SuperMercar.entities.Client;
import com.example.SuperMercar.entities.Venta;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IVentaService {

    List<Venta> findAll();

    Optional<Venta> findById(Long id);

    Venta save(Venta venta);

    void deleteById(Long id);

}
