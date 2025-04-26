package com.stanreybackend.stanreyapi.entity;

import org.hibernate.annotations.ColumnDefault;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "id_producto")
    private Long idProducto;

    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String descripcion;
    
    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false, name = "url_imagen")
    private String urlImagen;
    @Column
    private String color;

    @Column(nullable = false)
    @ColumnDefault("1")
    private Integer estado;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id_categoria", nullable = false)
    private Categoria categoria;
}
