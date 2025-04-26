package com.stanreybackend.stanreyapi.DTO;

public class PerfilDTO {

    private Long idPerfil;
    private String descripcion;

    public PerfilDTO(Long idPerfil, String descripcion) {
        this.idPerfil = idPerfil;
        this.descripcion = descripcion;
    }

    public PerfilDTO() {
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
