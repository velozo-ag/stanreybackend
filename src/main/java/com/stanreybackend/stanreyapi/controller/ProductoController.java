package com.stanreybackend.stanreyapi.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stanreybackend.stanreyapi.DTO.ProductoDTO;
import com.stanreybackend.stanreyapi.entity.Categoria;
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

    @GetMapping("/categoria/lista")
    public List<Categoria> getAllCategorias() {
        return categoriaService.findAll();
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

    @PostMapping("/it_admin/producto/uploadImagen")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("El archivo está vacío");
        }

        try {
            String uploadDirectory = "src/main/resources/images";

            File directory = new File(uploadDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String originalFilename = file.getOriginalFilename();
            String filename = System.currentTimeMillis() + "_" + originalFilename;

            Path filePath = Paths.get(uploadDirectory, filename);
            Files.copy(file.getInputStream(), filePath);

            return ResponseEntity.ok(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar archivo");
        }
    }

    @GetMapping("/producto/imagen/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("src/main/resources/images").resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header("Content-Type", "image/jpeg") 
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
