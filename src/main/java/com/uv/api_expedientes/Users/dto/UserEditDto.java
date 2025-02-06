package com.uv.api_expedientes.Users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEditDto {
    private String username;
    private String telefono;
    private String password;
    private String facultad;
    private Long rol;

}
