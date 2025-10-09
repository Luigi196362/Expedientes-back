package com.uv.api_expedientes.Pacientes.dtos;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdPacienteDto {
    private Integer id;
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
    private List<Registros> registros;

    @Data
    public static class Registros {
        private Integer registro_id;
        private String tipo_registro;
        private Date fecha_creacion;

    }
}
