package com.stanreybackend.stanreyapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stanreybackend.stanreyapi.entity.DetalleFactura;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {

    Optional<DetalleFactura> findByIdDetalleFactura(Long idDetalleFactura);
    List<DetalleFactura> findByFacturaIdFactura(Long facturaId);
}