package com.stanreybackend.stanreyapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stanreybackend.stanreyapi.DTO.ProductoDTO;
import com.stanreybackend.stanreyapi.entity.Producto;
import com.stanreybackend.stanreyapi.service.CategoriaService;
import com.stanreybackend.stanreyapi.service.ProductoService;
import com.stanreybackend.stanreyapi.service.UsuarioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin
@RequestMapping("/stanrey")
public class ProductoController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    ProductoService productoService;
    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/producto/lista")
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }
    
    @GetMapping("/producto/{id_producto}")
    public Producto getProductoById(@PathVariable Long id_producto) {
        return productoService.findByIdProducto(id_producto);
    }
    
    @PostMapping("/it_admin/producto/save")
    public String saveProducto(@RequestBody ProductoDTO productoDTO) {
        String id = productoService.addProducto(productoDTO);
        return id;
    }
    
    @PutMapping("/it_admin/producto/baja/{id_producto}")
    public String bajaProductoById(@PathVariable("id_producto") Long id_producto) {
        String id = productoService.bajaByIdproducto(id_producto);
        return id;
    }
    
    @PutMapping("/it_admin/producto/alta/{id_producto}")
    public String altaProductoById(@PathVariable("id_producto") Long id_producto) {
        String id = productoService.altaByIdproducto(id_producto);
        return id;
    }
    
    @PutMapping("/it_admin/producto/modificar/{id_producto}")
    public String updateProducto(@PathVariable("id_producto") Long id_producto, @RequestBody ProductoDTO productoDTO) {
        String id = productoService.updateProducto(id_producto, productoDTO);
        return id;
    }

}
