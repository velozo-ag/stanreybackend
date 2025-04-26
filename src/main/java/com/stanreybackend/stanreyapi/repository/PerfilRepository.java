package com.stanreybackend.stanreyapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.stanreybackend.stanreyapi.entity.Perfil;



@EnableJpaRepositories
@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>{
 
    Optional<Perfil> findByIdPerfil(Long idPerfil);
    
}
