package com.stanreybackend.stanreyapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.stanreybackend.stanreyapi.entity.Producto;
import java.util.List;


@EnableJpaRepositories
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
 
    Optional<Producto> findByIdProducto(Long idProducto);
    
}
