package com.stanreybackend.stanreyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stanreybackend.stanreyapi.DTO.CategoriaDTO;
import com.stanreybackend.stanreyapi.DTO.PerfilDTO;
import com.stanreybackend.stanreyapi.DTO.ProductoDTO;
import com.stanreybackend.stanreyapi.DTO.UsuarioDTO;
import com.stanreybackend.stanreyapi.service.CategoriaService;
import com.stanreybackend.stanreyapi.service.PerfilService;
import com.stanreybackend.stanreyapi.service.ProductoService;
import com.stanreybackend.stanreyapi.service.UsuarioService;

@RestController
@RequestMapping("/stanrey")
public class TestController {

    // @Autowired
    // private UsuarioService usuarioService;
    // @Autowired
    // private PerfilService perfilService;
    // @Autowired
    // private CategoriaService categoriaService;
    // @Autowired
    // private ProductoService productoService;

    // @PostMapping("/test/saveUsuario")
    // public String saveUser(@RequestBody UsuarioDTO usuarioDTO) {
    //     String id = usuarioService.addUsuario(usuarioDTO); 
    //     return id;
    // }

    // @PostMapping("/test/savePerfil")
    // public String savePerfil(@RequestBody PerfilDTO perfilDTO) {
    //     String id = perfilService.addPerfil(perfilDTO); 
    //     return id;
    // }

    // @PostMapping("/test/saveCategoria")
    // public String saveCategoria(@RequestBody CategoriaDTO categoriaDTO) {
    //     String id = categoriaService.addCategoria(categoriaDTO); 
    //     return id;
    // }
    
    // @PostMapping("/test/saveProducto")
    // public String saveProducto(@RequestBody ProductoDTO productoDTO) {
    //     String id = productoService.addProducto(productoDTO);
    //     return id;
    // }

    @GetMapping("/it_admin")
    @PreAuthorize("hasRole('IT_ADMIN')")
    public String adminAccess() {
        return "Contenido administradores";
    }

    @GetMapping("/client")
    @PreAuthorize("hasRole('CLIENT')")
    public String sellerAccess() {
        return "Contenido clientes";
    }

    @GetMapping("/manager")
    @PreAuthorize("hasRole('MANAGER')")
    public String managerAccess() {
        return "Contenido gerentes";
    }
}