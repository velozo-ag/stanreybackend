package com.stanreybackend.stanreyapi.DTO;

public class PersonaDTO {
    private Long dni;
    
    private String nombre;
    private String apellido;

    private String email;
    private Integer telefono;
    // private Direccion direccion;
    
    public PersonaDTO() {
    }

    public PersonaDTO(Long dni, String nombre, String apellido, String email, Integer telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

}
