package com.stanreybackend.stanreyapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.stanreybackend.stanreyapi.entity.Categoria;

@EnableJpaRepositories
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
 
    Optional<Categoria> findByIdCategoria(Long idCategoria);
    Optional<Categoria> findByDescripcion(String descripcion);
    Optional<Categoria> findByEstado(Integer estado);
    
}
