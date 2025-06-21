package com.stanreybackend.stanreyapi.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class FacturaDTO {

    private Long idFactura;

    private LocalDateTime fecha;

    private Double importeTotal;

    private String formaPago;

    private String estado;

    private Long usuarioId;

    private List<Long> detalleIds;

    public FacturaDTO() {
    }

    public FacturaDTO(Long idFactura, LocalDateTime fecha, Double importeTotal, String formaPago, String estado,
                     Long usuarioId, List<Long> detalleIds) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.importeTotal = importeTotal;
        this.formaPago = formaPago;
        this.estado = estado;
        this.usuarioId = usuarioId;
        this.detalleIds = detalleIds;
    }

    public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(Double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<Long> getDetalleIds() {
        return detalleIds;
    }

    public void setDetalleIds(List<Long> detalleIds) {
        this.detalleIds = detalleIds;
    }
}