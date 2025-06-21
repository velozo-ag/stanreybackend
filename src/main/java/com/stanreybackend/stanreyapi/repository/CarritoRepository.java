package com.stanreybackend.stanreyapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stanreybackend.stanreyapi.entity.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    Optional<Carrito> findByIdCarrito(Long idCarrito);
    Optional<Carrito> findByUsuarioIdUsuario(Long usuarioId);
}