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
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "id_usuario")
    private Long idUsuario;

    @Column(nullable = false, unique = true)
    private String usuario;
    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "perfil_id", referencedColumnName = "id_perfil", nullable = false)
    private Perfil perfil;

    @Column(nullable = false)
    @ColumnDefault("1")
    private Integer estado;

    @OneToOne
    @JoinColumn(name = "persona", referencedColumnName = "dni", nullable = false, unique = true)
    private Persona persona;
}
