package com.stanreybackend.stanreyapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stanreybackend.stanreyapi.DTO.DetalleFacturaDTO;
import com.stanreybackend.stanreyapi.DTO.FacturaDTO;
import com.stanreybackend.stanreyapi.entity.DetalleFactura;
import com.stanreybackend.stanreyapi.entity.Factura;
import com.stanreybackend.stanreyapi.service.DetalleFacturaService;
import com.stanreybackend.stanreyapi.service.FacturaService;

@RestController
@RequestMapping("/stanrey")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @PostMapping("/factura/finalizar-compra")
    public ResponseEntity<String> finalizarCompra(@RequestBody FacturaRequest facturaRequest) {
        String facturaId = facturaService.addFactura(facturaRequest.getFacturaDTO(), facturaRequest.getDetallesDTO());
        return ResponseEntity.ok(facturaId);
    }

    @GetMapping("/factura/usuario/{id_usuario}")
    public ResponseEntity<List<Factura>> getFacturasByUsuarioId(@PathVariable Long id_usuario) {
        List<Factura> facturas = facturaService.findByUsuarioId(id_usuario);
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/factura/detalles/{id_factura}")
    public ResponseEntity<List<DetalleFactura>> getDetallesByFacturaId(@PathVariable Long id_factura) {
        List<DetalleFactura> detalles = detalleFacturaService.findByFacturaId(id_factura);
        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/factura/lista")
    public List<Factura> listarFacturas() {
        return facturaService.findAll();
    }
}