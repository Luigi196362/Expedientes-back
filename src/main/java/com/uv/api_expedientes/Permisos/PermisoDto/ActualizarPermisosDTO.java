package com.uv.api_expedientes.Permisos.PermisoDto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarPermisosDTO {
    private Long rolId;
    private List<PermisoDTO> permisos;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PermisoDTO {
        private Long recursoId;
        private List<Long> accionesIds;
    }
}
