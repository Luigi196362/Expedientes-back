package com.uv.api_expedientes.AccessControl.Roles.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRolDto {
    private String nombre;
    private String descripcion;
}