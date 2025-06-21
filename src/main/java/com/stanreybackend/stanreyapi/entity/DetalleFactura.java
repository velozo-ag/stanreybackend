package com.stanreybackend.stanreyapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detalle_factura")
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_factura")
    private Long idDetalleFactura;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false, referencedColumnName = "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "factura_id", nullable = false, referencedColumnName = "id_factura")
    private Factura factura;
}
