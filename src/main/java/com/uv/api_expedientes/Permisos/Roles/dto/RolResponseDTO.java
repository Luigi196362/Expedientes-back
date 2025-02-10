package com.uv.api_expedientes.Permisos.Roles.dto;

import java.util.List;

import com.uv.api_expedientes.Permisos.PermisoDto.ActualizarPermisosDTO.PermisoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolResponseDTO {
    private long id;
    private String nombre;
    private String descripcion;
    private List<PermisoDTO> permisos;
}
