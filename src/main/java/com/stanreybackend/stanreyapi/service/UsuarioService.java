package com.stanreybackend.stanreyapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stanreybackend.stanreyapi.DTO.UsuarioDTO;
import com.stanreybackend.stanreyapi.entity.Usuario;
import com.stanreybackend.stanreyapi.repository.PerfilRepository;
import com.stanreybackend.stanreyapi.repository.PersonaRepository;
import com.stanreybackend.stanreyapi.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUsuario(UsuarioDTO usuarioDTO) {

        Usuario usuario = new Usuario(
                usuarioDTO.getIdUsuario(),
                usuarioDTO.getUsuario(),
                passwordEncoder.encode(usuarioDTO.getPassword()),
                perfilRepository.findByIdPerfil(usuarioDTO.getPerfilId()).orElse(null),
                usuarioDTO.getEstado(),
                usuarioDTO.getPersona());

        usuarioRepository.save(usuario);

        return usuario.getIdUsuario().toString();
    };

    public List<Usuario> findAll() {

        List<Usuario> usuarios = new ArrayList();
        usuarios = usuarioRepository.findAll();

        return usuarios;
    }
}
