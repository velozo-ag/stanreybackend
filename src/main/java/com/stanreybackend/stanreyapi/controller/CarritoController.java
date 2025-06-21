package com.stanreybackend.stanreyapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stanreybackend.stanreyapi.DTO.CarritoDTO;
import com.stanreybackend.stanreyapi.DTO.CarritoProductoDTO;
import com.stanreybackend.stanreyapi.entity.Carrito;
import com.stanreybackend.stanreyapi.entity.CarritoProducto;
import com.stanreybackend.stanreyapi.service.CarritoProductoService;
import com.stanreybackend.stanreyapi.service.CarritoService;

@RestController
@CrossOrigin
@RequestMapping("/stanrey")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private CarritoProductoService carritoProductoService;

    @PostMapping("/carrito/save")
    public ResponseEntity<String> saveCarrito(@RequestBody CarritoDTO carritoDTO) {
        String id = carritoService.addCarrito(carritoDTO);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/carrito/{id_carrito}")
    public ResponseEntity<Carrito> getCarritoById(@PathVariable Long id_carrito) {
        Carrito carrito = carritoService.findByIdCarrito(id_carrito);
        if (carrito != null) {
            return ResponseEntity.ok(carrito);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/carrito/usuario/{id_usuario}")
    public ResponseEntity<Carrito> getCarritoByUsuarioId(@PathVariable Long id_usuario) {
        Carrito carrito = carritoService.findByUsuarioId(id_usuario);
        if (carrito != null) {
            return ResponseEntity.ok(carrito);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/carrito/productos/{id_carrito}")
    public ResponseEntity<List<CarritoProducto>> getProductosInCarrito(@PathVariable Long id_carrito) {
        List<CarritoProducto> productos = carritoProductoService.findByCarritoId(id_carrito);
        return ResponseEntity.ok(productos);
    }

    @PostMapping("/carrito/agregar-producto")
    public ResponseEntity<String> agregarProductoACarrito(@RequestBody CarritoProductoDTO carritoProductoDTO) {
        String id = carritoProductoService.addCarritoProducto(carritoProductoDTO);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/carrito/lista")
    public ResponseEntity<List<Carrito>> getAllCarritos() {
        List<Carrito> carritos = carritoService.findAll();
        return ResponseEntity.ok(carritos);
    }
}