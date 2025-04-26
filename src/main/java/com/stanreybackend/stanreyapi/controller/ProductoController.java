package com.stanreybackend.stanreyapi.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stanreybackend.stanreyapi.DTO.ProductoDTO;
import com.stanreybackend.stanreyapi.DTO.UsuarioDTO;
import com.stanreybackend.stanreyapi.entity.Producto;
import com.stanreybackend.stanreyapi.entity.Usuario;
import com.stanreybackend.stanreyapi.service.CategoriaService;
import com.stanreybackend.stanreyapi.service.ProductoService;
import com.stanreybackend.stanreyapi.service.UsuarioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin
@RequestMapping("/stanrey/producto")
public class ProductoController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    ProductoService productoService;
    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/lista")
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }

    @GetMapping("/{id_producto}")
    public Producto getProductoById(@PathVariable Long id_producto) {
        return productoService.findByIdProducto(id_producto);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('IT_ADMIN')")
    public String saveProducto(@RequestBody ProductoDTO productoDTO) {
        String id = productoService.addProducto(productoDTO);
        return id;
    }

    @PutMapping("/baja/{id_producto}")
    @PreAuthorize("hasRole('IT_ADMIN')")
    public String bajaProductoById(@PathVariable("id_producto") Long id_producto) {
        String id = productoService.bajaByIdproducto(id_producto);
        return id;
    }

    @PutMapping("/alta/{id_producto}")
    @PreAuthorize("hasRole('IT_ADMIN')")
    public String altaProductoById(@PathVariable("id_producto") Long id_producto) {
        String id = productoService.altaByIdproducto(id_producto);
        return id;
    }

    @PutMapping("/modificar/{id_producto}")
    @PreAuthorize("hasRole('IT_ADMIN')")
    public String updateProducto(@PathVariable("id_producto") Long id_producto, @RequestBody ProductoDTO productoDTO) {
        String id = productoService.updateProducto(id_producto, productoDTO);
        return id;
    }

}
