package com.stanreybackend.stanreyapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.stanreybackend.stanreyapi.entity.Persona;

@EnableJpaRepositories
@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{
 
    Optional<Persona> findByDni(Long dni);
    Optional<Persona> findByEmail(String email);
    
}
