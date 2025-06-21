package com.stanreybackend.stanreyapi.DTO;

import java.time.LocalDateTime;

public class CarritoProductoDTO {

    private Long idCarritoProducto;

    private LocalDateTime fechaCreacion;

    private Integer cantidad;

    private Double precioUnitario;

    private Long carritoId;

    private Long productoId;

    public CarritoProductoDTO() {
    }

    public CarritoProductoDTO(Long idCarritoProducto, LocalDateTime fechaCreacion, Integer cantidad,
                             Double precioUnitario, Long carritoId, Long productoId) {
        this.idCarritoProducto = idCarritoProducto;
        this.fechaCreacion = fechaCreacion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.carritoId = carritoId;
        this.productoId = productoId;
    }

    public Long getIdCarritoProducto() {
        return idCarritoProducto;
    }

    public void setIdCarritoProducto(Long idCarritoProducto) {
        this.idCarritoProducto = idCarritoProducto;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Long getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(Long carritoId) {
        this.carritoId = carritoId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }
}