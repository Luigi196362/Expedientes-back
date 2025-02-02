package com.uv.api_expedientes.Auth;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String telefono;
    private String password;
    private String facultad;
    private Date fecha_creacion;
    private Long rol;

}
