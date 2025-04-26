package com.stanreybackend.stanreyapi.DTO;

import com.stanreybackend.stanreyapi.entity.Persona;

public class UsuarioDTO {

   private Long idUsuario;

    private String usuario;
    private String password;

    private Long perfilId;

    private Integer estado;

    private Persona persona;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long idUsuario, String usuario, String password, Long perfilId, Integer estado, Persona persona) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.password = password;
        this.perfilId = perfilId;
        this.estado = estado;
        this.persona = persona;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Long perfilId) {
        this.perfilId = perfilId;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
