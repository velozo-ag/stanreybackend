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
    private PersonaRepository personaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUsuario(UsuarioDTO usuarioDTO) {

        personaRepository.save(usuarioDTO.getPersona());

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

    public Usuario findByIdUsuario(Long id_usuario) {
        return usuarioRepository.findByIdUsuario(id_usuario).orElse(null);
    }

    public Usuario findByUsuario(String usuario) {
        return usuarioRepository.findByUsuario(usuario).orElse(null);
    }

    public Long bajaByIdUsuario(Long id_usuario) {
        Usuario usuario = findByIdUsuario(id_usuario);
        if (usuario.getEstado() == 1) {
            usuario.setEstado(0);
            usuarioRepository.save(usuario);
        }

        return usuario.getIdUsuario();
    }

    public Long bajaByUsuario(String username) {
        Usuario usuario = findByUsuario(username);
        if (usuario.getEstado() == 1) {
            usuario.setEstado(0);
            usuarioRepository.save(usuario);
        }

        return usuario.getIdUsuario();
    }

    public Long altaByIdUsuario(Long id_usuario) {
        Usuario usuario = findByIdUsuario(id_usuario);
        if (usuario.getEstado() == 0) {
            usuario.setEstado(1);
            usuarioRepository.save(usuario);
        }

        return usuario.getIdUsuario();
    }

    public Long altaByUsuario(String username) {
        Usuario usuario = findByUsuario(username);
        if (usuario.getEstado() == 0) {
            usuario.setEstado(1);
            usuarioRepository.save(usuario);
        }

        return usuario.getIdUsuario();
    }

    public List<Usuario> findAll() {

        List<Usuario> usuarios = new ArrayList();
        usuarios = usuarioRepository.findAll();

        return usuarios;
    }
}
