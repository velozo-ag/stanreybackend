package com.stanreybackend.stanreyapi.DTO;

public class UsuarioDTO {

   private Long idUsuario;

    private String usuario;
    private String password;

    private Long perfilId;

    private Integer estado;

    private Long dni;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long idUsuario, String usuario, String password, Long perfilId, Integer estado, Long dni) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.password = password;
        this.perfilId = perfilId;
        this.estado = estado;
        this.dni = dni;
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

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

}
