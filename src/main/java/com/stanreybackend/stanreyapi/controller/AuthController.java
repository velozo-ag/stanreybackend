package com.stanreybackend.stanreyapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.stanreybackend.stanreyapi.DTO.UsuarioDTO;
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
    public ResponseEntity<?> authenticateUser(@RequestBody UsuarioDTO usuario) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usuario.getUsuario(), usuario.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getUsuario());
            String token = jwtUtil.generateToken(userDetails.getUsername());

            String role = userDetails.getAuthorities().stream()
                    .map(authority -> authority.getAuthority().replace("ROLE_", ""))
                    .findFirst()
                    .orElse("UNKNOWN");

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "role", role));
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
}