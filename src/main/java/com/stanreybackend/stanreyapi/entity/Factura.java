package com.stanreybackend.stanreyapi.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Long idFactura;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(name = "importe_total", nullable = false)
    private Double importeTotal;

    @Column(name = "forma_pago", nullable = false)
    private String formaPago;

    @Column(nullable = false)
    @ColumnDefault("1")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false, referencedColumnName = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetalleFactura> detalles;
}
