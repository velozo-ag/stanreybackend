package com.stanreybackend.stanreyapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.stanreybackend.stanreyapi.entity.Usuario;

@EnableJpaRepositories
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
 
    Optional<Usuario> findByUsuario(String usuario);
    boolean existsByUsuario(String usuario);

    Optional<Usuario> findByIdUsuario(Long idUsuario);
    
}
