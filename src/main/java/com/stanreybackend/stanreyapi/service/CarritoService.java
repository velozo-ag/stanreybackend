package com.stanreybackend.stanreyapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stanreybackend.stanreyapi.DTO.CarritoDTO;
import com.stanreybackend.stanreyapi.entity.Carrito;
import com.stanreybackend.stanreyapi.repository.CarritoRepository;
import com.stanreybackend.stanreyapi.repository.UsuarioRepository;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String addCarrito(CarritoDTO carritoDTO) {
        Carrito carrito = new Carrito(
                carritoDTO.getIdCarrito(),
                carritoDTO.getFechaCreacion(),
                carritoDTO.getEstado(),
                usuarioRepository.findByIdUsuario(carritoDTO.getUsuarioId()).orElse(null)
        );

        carritoRepository.save(carrito);

        return carrito.getIdCarrito().toString();
    }

    public Carrito findByIdCarrito(Long idCarrito) {
        return carritoRepository.findByIdCarrito(idCarrito).orElse(null);
    }

    public Carrito findByUsuarioId(Long usuarioId) {
        return carritoRepository.findByUsuarioIdUsuario(usuarioId).orElse(null);
    }

    public String bajaByIdCarrito(Long idCarrito) {
        Carrito carrito = findByIdCarrito(idCarrito);
        if (carrito != null && carrito.getEstado() == 1) {
            carrito.setEstado(0);
            carritoRepository.save(carrito);
        }
        return carrito != null ? carrito.getIdCarrito().toString() : null;
    }

    public String altaByIdCarrito(Long idCarrito) {
        Carrito carrito = findByIdCarrito(idCarrito);
        if (carrito != null && carrito.getEstado() == 0) {
            carrito.setEstado(1);
            carritoRepository.save(carrito);
        }
        return carrito != null ? carrito.getIdCarrito().toString() : null;
    }

    public List<Carrito> findAll() {
        return carritoRepository.findAll();
    }
}