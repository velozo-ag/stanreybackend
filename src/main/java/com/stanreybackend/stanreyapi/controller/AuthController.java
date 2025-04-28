package com.stanreybackend.stanreyapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.stanreybackend.stanreyapi.DTO.UsuarioDTO;
import com.stanreybackend.stanreyapi.entity.Usuario;
import com.stanreybackend.stanreyapi.repository.UsuarioRepository;
import com.stanreybackend.stanreyapi.security.JwtUtil;
import com.stanreybackend.stanreyapi.service.UsuarioService;

import java.util.Map;

@RestController
@RequestMapping("/stanrey/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
            UserDetailsService userDetailsService, UsuarioRepository usuarioRepository,
            UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usuarioDTO.getUsuario(), usuarioDTO.getPassword()));

            UserDetails userDetails = userDetailsService.loadUserByUsername(usuarioDTO.getUsuario());
            String token = jwtUtil.generateToken(userDetails.getUsername());

            Usuario usuario = usuarioService.findByUsuario(usuarioDTO.getUsuario());
            if (usuario == null) {
                return ResponseEntity.status(404).body(Map.of("error", "Usuario no encontrado"));
            }
            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "usuario", new UsuarioDTO(usuario.getIdUsuario(), usuario.getUsuario(), null,
                            usuario.getPerfil().getIdPerfil(), usuario.getEstado(), usuario.getPersona())));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(Map.of("error", "Credenciales inválidas"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error interno del servidor"));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UsuarioDTO usuario) {
        if (usuarioRepository.existsByUsuario(usuario.getUsuario())) {
            return ResponseEntity.badRequest().body(Map.of("error", "El usuario ya está registrado"));
        }
        usuarioService.addUsuario(usuario);
        return ResponseEntity.ok(Map.of("message", "Usuario registrado: " + usuario.getUsuario()));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestHeader("Authorization") String token) {
        try {
            if (token == null || !token.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body(Map.of("error", "Token inválido"));
            }
            String jwt = token.replace("Bearer ", "");
            String username = jwtUtil.extractUsername(jwt);
            if (username == null || !jwtUtil.validateToken(jwt, username)) {
                return ResponseEntity.status(401).body(Map.of("error", "Token inválido"));
            }
            return ResponseEntity.ok(Map.of("message", "Token válido"));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "Token inválido"));
        }
    }
}