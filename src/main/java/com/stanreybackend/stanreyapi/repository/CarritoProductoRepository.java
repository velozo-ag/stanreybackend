package com.stanreybackend.stanreyapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stanreybackend.stanreyapi.entity.CarritoProducto;

@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Long> {

    Optional<CarritoProducto> findByIdCarritoProducto(Long idCarritoProducto);
    List<CarritoProducto> findByCarritoIdCarrito(Long carritoId);

    @Modifying
    @Query("DELETE FROM CarritoProducto cp WHERE cp.carrito.idCarrito = :carritoId")
    void deleteByCarritoIdCarrito(Long carritoId);
}