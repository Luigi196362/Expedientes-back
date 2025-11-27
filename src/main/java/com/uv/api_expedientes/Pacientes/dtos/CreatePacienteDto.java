package com.uv.api_expedientes.Pacientes.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePacienteDto {
    private String matricula;
    private String nombre;
    private int sexo;
    private Date fecha_nacimiento;
    private int grupo;
    private int semestre;
    private String telefono;
    private String programa_educativo;
    private String ocupacion;
    private String residencia;
    private String religion;
    private String escolaridad;
    private String nss;
    private String origen;
    private String estado_civil;
    private String facultad;

    // Identificación
    private String tipo_paciente;
    private String curp;

    // Contacto y Ubicación
    private String email;
    private String calle;
    private String numero_exterior;
    private String numero_interior;
    private String colonia;
    private String cp;
    private String municipio;
    private String entidad_federativa;

    // Responsable
    private String nombre_responsable;
    private String parentesco_responsable;
    private String telefono_responsable;
    private String direccion_responsable;

    // Trabajador
    private String numero_personal;
    private String puesto;
    private String area_adscripcion;
    private String tipo_contratacion;

    // Sociodemográfico
    //private boolean habla_lengua_indigena;
    private String lengua_indigena;
}