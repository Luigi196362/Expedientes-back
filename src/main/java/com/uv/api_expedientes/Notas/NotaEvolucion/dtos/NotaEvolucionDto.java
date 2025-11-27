package com.uv.api_expedientes.Notas.NotaEvolucion.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotaEvolucionDto {
    private Integer id;
    private String interrogatorio;
    private int peso;
    private int talla;
    private float imc;
    private String ta;
    private int fc;
    private int fr;
    private int temperatura;
    private int saturacion;
    private int glicemia;
    private int hemoglobina;
    private String hemotipo;
    private String padecimiento;
    private String exploracion;
    private String analisis;
    private String plan;
    private String diagnostico;
    private String tratamiento;
    private Date fecha_creacion;
}
