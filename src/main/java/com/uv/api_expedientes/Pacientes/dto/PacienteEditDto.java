package com.uv.api_expedientes.Pacientes.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PacienteEditDto {

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

}
