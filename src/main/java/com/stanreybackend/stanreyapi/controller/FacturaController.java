package com.stanreybackend.stanreyapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stanreybackend.stanreyapi.DTO.FacturaDTO;
import com.stanreybackend.stanreyapi.DTO.DetalleFacturaDTO;
import com.stanreybackend.stanreyapi.entity.DetalleFactura;
import com.stanreybackend.stanreyapi.entity.Factura;
import com.stanreybackend.stanreyapi.service.DetalleFacturaService;
import com.stanreybackend.stanreyapi.service.FacturaService;

@RestController
@CrossOrigin
@RequestMapping("/stanrey")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @PostMapping("/factura/finalizar-compra")
    public ResponseEntity<String> finalizarCompra(@RequestBody FacturaDTO facturaDTO, @RequestBody List<DetalleFacturaDTO> detallesDTO) {
        String facturaId = facturaService.addFactura(facturaDTO);

        for (DetalleFacturaDTO detalleDTO : detallesDTO) {
            detalleDTO.setFacturaId(Long.valueOf(facturaId));
            detalleFacturaService.addDetalleFactura(detalleDTO);
        }

        return ResponseEntity.ok(facturaId);
    }

    @GetMapping("/factura/{id_factura}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable Long id_factura) {
        Factura factura = facturaService.findByIdFactura(id_factura);
        if (factura != null) {
            return ResponseEntity.ok(factura);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/factura/detalles/{id_factura}")
    public ResponseEntity<List<DetalleFactura>> getDetallesByFacturaId(@PathVariable Long id_factura) {
        List<DetalleFactura> detalles = detalleFacturaService.findByFacturaId(id_factura);
        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/factura/usuario/{id_usuario}")
    public ResponseEntity<List<Factura>> getFacturasByUsuarioId(@PathVariable Long id_usuario) {
        List<Factura> facturas = facturaService.findByUsuarioId(id_usuario);
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/factura/lista")
    public ResponseEntity<List<Factura>> getAllFacturas() {
        List<Factura> facturas = facturaService.findAll();
        return ResponseEntity.ok(facturas);
    }

    @PutMapping("/it_admin/factura/baja/{id_factura}")
    public ResponseEntity<String> bajaFacturaById(@PathVariable Long id_factura) {
        String id = facturaService.bajaByIdFactura(id_factura);
        if (id != null) {
            return ResponseEntity.ok(id);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/it_admin/factura/alta/{id_factura}")
    public ResponseEntity<String> altaFacturaById(@PathVariable Long id_factura) {
        String id = facturaService.altaByIdFactura(id_factura);
        if (id != null) {
            return ResponseEntity.ok(id);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/it_admin/factura/modificar/{id_factura}")
    public ResponseEntity<String> updateFactura(@PathVariable Long id_factura, @RequestBody FacturaDTO facturaDTO) {
        String id = facturaService.updateFactura(id_factura, facturaDTO);
        if (id != null) {
            return ResponseEntity.ok(id);
        }
        return ResponseEntity.notFound().build();
    }
}