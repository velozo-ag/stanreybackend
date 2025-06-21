package com.stanreybackend.stanreyapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stanreybackend.stanreyapi.entity.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    Optional<Factura> findByIdFactura(Long idFactura);
    List<Factura> findByUsuarioIdUsuario(Long usuarioId);
}