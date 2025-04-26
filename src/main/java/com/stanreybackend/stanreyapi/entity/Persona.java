package com.stanreybackend.stanreyapi.entity;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @Column(unique = true, nullable = false)
    private Long dni;

    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private Integer telefono;

    // private Direccion direccion;
}
