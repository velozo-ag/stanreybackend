package com.stanreybackend.stanreyapi.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "carrito_producto")
public class CarritoProducto {

    @Id
    @Column(unique = true, nullable = false, name = "id_carrito_producto")
    private Long idCarritoProducto;

    @CreatedDate
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private Integer cantidad;
    
    @Column(nullable = false, name = "precio_unitario")
    private Double precioUnitario;

    @ManyToOne
    @JoinColumn(name = "carrito_id", referencedColumnName = "id_carrito", nullable = false)
    private Carrito carrito;
    
    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id_producto", nullable = false)
    private Producto producto;
}
