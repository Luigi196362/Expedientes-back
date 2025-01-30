package com.uv.api_expedientes.Permisos.Roles.dto;

import java.util.List;
import java.util.Map;

public class RolResponseDTO {
    private String nombre;
    private String descripcion;
    private Map<String, List<String>> recursos; // Recurso -> Acciones

    public RolResponseDTO(String nombre, String descripcion, Map<String, List<String>> recursos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.recursos = recursos;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Map<String, List<String>> getRecursos() {
        return recursos;
    }

    public void setRecursos(Map<String, List<String>> recursos) {
        this.recursos = recursos;
    }
}
