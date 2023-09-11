package com.example.SuperMercar.controllers;

import com.example.SuperMercar.controllers.DTO.VentaDTO;
import com.example.SuperMercar.entities.Client;
import com.example.SuperMercar.entities.DetalleVenta;
import com.example.SuperMercar.entities.Venta;
import com.example.SuperMercar.services.contracts.IClientService;
import com.example.SuperMercar.services.contracts.IDetalleVentaService;
import com.example.SuperMercar.services.contracts.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    private IVentaService ventaService;

    @Autowired
    private IClientService clientService;

    @Autowired
    private IDetalleVentaService detalleVentaService;
    @GetMapping("/list")
    public ResponseEntity<List<VentaDTO>> getAllVentas(){
        List<VentaDTO> ventaList = ventaService.findAll()
                .stream()
                .map(venta -> VentaDTO.builder()
                        .id(venta.getId())
                        .client(venta.getClient())
                        .fecha(venta.getFecha())
                        .DetalleVenta(venta.getDetalleVenta())
                        .totalVenta(venta.getTotal())
                        .build())
                .toList();
        return ResponseEntity.ok(ventaList);
    }
    @GetMapping("/list/{id}")
    public ResponseEntity<VentaDTO> getVentaById(@PathVariable Long id){
        Optional<Venta> ventaOptional = ventaService.findById(id);
        if (ventaOptional.isPresent()){
            Venta venta = ventaOptional.get();
            VentaDTO ventaDTO = VentaDTO.builder()
                    .id(venta.getId())
                    .client(venta.getClient())
                    .fecha(venta.getFecha())
                    .DetalleVenta(venta.getDetalleVenta())
                    .totalVenta(venta.getTotal())
                    .build();
            return ResponseEntity.ok(ventaDTO);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/create")
    public ResponseEntity<?> createVenta(@RequestBody VentaDTO ventaDTO){

        try {
            if (ventaDTO.getClient() == null || ventaDTO.getDetalleVenta() == null) {
                return ResponseEntity.badRequest().build();
            }

            Venta venta = Venta.builder()
                    .client(ventaDTO.getClient())
                    .fecha(ventaDTO.getFecha())
                    .DetalleVenta(ventaDTO.getDetalleVenta())
                    .build();


            ventaService.save(venta);

            return ResponseEntity.created(new URI("/venta/save")).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la venta");
        }
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable Long id){
        if(id != null){
            ventaService.deleteById(id);
            return ResponseEntity.ok("Registro eliminado correctamente");
        }
        return  ResponseEntity.badRequest().build();
    }
    }

