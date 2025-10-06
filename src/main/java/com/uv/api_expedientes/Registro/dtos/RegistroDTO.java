package com.uv.api_expedientes.Registro.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDTO {
    private String usuario;
    private String paciente;
    private Date fecha_creacion;
    private String tipo_registro;
    // private Long idRegistro;

}