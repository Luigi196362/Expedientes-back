package com.uv.api_expedientes.Notas.HistoriaClinica.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoriaClinicaDto {
    private Integer id;
    private String antecedentes_heredo_familiares;
    private String antecedentes_personales_no_patologicos;
    private String antecedentes_personales_patologicos;
    private String medicamentos_actuales;
    private String diagnostico_inicial;
    private String tratamiento;
    private String observaciones;
    private String alergias;
    private Date fecha_creacion;
}
