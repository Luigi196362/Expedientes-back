package com.uv.api_expedientes.Usuarios.dto;

import java.util.Date;

public class UsuarioResponseDto {
    private String nombre;
    private String telefono;
    private String facultad;
    private Date fecha_creacion;
    private String rolNombre; // Cambié de Long a String para el nombre del rol

    // Constructor
    public UsuarioResponseDto(String nombre, String telefono, String facultad, Date fecha_creacion, String rolNombre) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.facultad = facultad;
        this.fecha_creacion = fecha_creacion;
        this.rolNombre = rolNombre;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }
}
