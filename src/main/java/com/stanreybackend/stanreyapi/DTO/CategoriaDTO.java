package com.stanreybackend.stanreyapi.DTO;

public class CategoriaDTO {

    private Long idCategoria;

    private String descripcion;
    private Integer estado;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Long idCategoria, String descripcion, Integer estado) {
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
