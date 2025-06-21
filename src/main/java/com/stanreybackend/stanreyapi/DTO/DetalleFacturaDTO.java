package com.stanreybackend.stanreyapi.DTO;

public class DetalleFacturaDTO{

    private Long idDetalleFactura;

    private Integer cantidad;

    private Double subtotal;

    private Long productoId;

    private Long facturaId;

    public DetalleFacturaDTO() {
    }

    public DetalleFacturaDTO(Long idDetalleFactura, Integer cantidad, Double subtotal, Long productoId, Long facturaId) {
        this.idDetalleFactura = idDetalleFactura;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.productoId = productoId;
        this.facturaId = facturaId;
    }

    public Long getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(Long idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Long facturaId) {
        this.facturaId = facturaId;
    }
}