package com.uv.api_expedientes.Permisos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PermisoRepository extends CrudRepository<Permiso, Long> {
    List<Permiso> findByRolId(Long rolId); // Buscar permisos por ID del Rol

    Optional<Permiso> findByRolIdAndRecursoIdAndAccionId(Long rolId, Long recursoId, Long accionId);
}
