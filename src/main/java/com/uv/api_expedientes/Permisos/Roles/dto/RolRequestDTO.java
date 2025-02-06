package com.uv.api_expedientes.Permisos.Roles.dto;

import lombok.Data;
import java.util.List;

@Data
public class RolRequestDTO {

    private String nombre;
    private String descripcion;
    private List<PermisoDTO> permisos;

    @Data
    public static class PermisoDTO {
        private Long recursoId;
        private List<Long> accionesIds;
    }
}
