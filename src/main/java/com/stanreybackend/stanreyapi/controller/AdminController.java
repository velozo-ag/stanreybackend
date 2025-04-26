package com.stanreybackend.stanreyapi.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stanreybackend.stanreyapi.DTO.UsuarioDTO;
import com.stanreybackend.stanreyapi.entity.Usuario;
import com.stanreybackend.stanreyapi.service.UsuarioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
@RequestMapping("/stanrey/it_admin")
public class AdminController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/save")
    public String saveUsuario(@RequestBody UsuarioDTO usuarioDTO) {

        String id = usuarioService.addUsuario(usuarioDTO);
        return id;
    }
    
    @GetMapping("/usuario/list")
    public List<Usuario> getUsuario() {
        
        return usuarioService.findAll();
    }
    
}
