package com.uv.api_expedientes.Users.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String nombre;
    private String telefono;
    private String facultad;
    private Date fecha_creacion;
    private String rolNombre; // Cambié de Long a String para el nombre del rol

}
