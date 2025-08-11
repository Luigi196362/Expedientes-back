package com.uv.api_expedientes.Auth.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {
    private String matricula;
    private String username;
    private String curp;
    private String rfc;
    private String cedulaProfesional;
    private String especialidad;
    private String password;
    private String telefono;
    private String facultad;
    private Integer rolId;
}
