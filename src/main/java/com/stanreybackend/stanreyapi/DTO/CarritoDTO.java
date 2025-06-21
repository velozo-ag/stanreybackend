package com.stanreybackend.stanreyapi.DTO;

import java.time.LocalDateTime;

public class CarritoDTO {

    private Long idCarrito;

    private LocalDateTime fechaCreacion;

    private Integer estado;

    private Long usuarioId;

    public CarritoDTO() {
    }

    public CarritoDTO(Long idCarrito, LocalDateTime fechaCreacion, Integer estado, Long usuarioId) {
        this.idCarrito = idCarrito;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.usuarioId = usuarioId;
    }

    public Long getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Long idCarrito) {
        this.idCarrito = idCarrito;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}