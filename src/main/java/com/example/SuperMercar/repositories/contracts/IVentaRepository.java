package com.example.SuperMercar.repositories.contracts;


import com.example.SuperMercar.entities.Client;
import com.example.SuperMercar.entities.Venta;
import jakarta.websocket.ClientEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IVentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByClient(Client client);

    List<Venta> findByFecha(String date);
    List<Venta> findByClientAndFechaBetween(Client client, String startDate, String endCate);

    @Query("SELECT SUM(v.total) FROM Venta v WHERE v.client = :client")
    Double getTotalVentasByClient(@Param("client") ClientEndpoint client);
}
