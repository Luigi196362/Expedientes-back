package com.uv.api_expedientes.Permisos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PermisoRepository extends JpaRepository<Permiso, Long> {
    List<Permiso> findByRolId(Long rolId); // Buscar permisos por ID del Rol

    @Modifying
    @Query("DELETE FROM Permiso p WHERE p.rol.id = :rolId")
    void deleteByRolId(@Param("rolId") Long rolId);

    Optional<Permiso> findByRolIdAndRecursoIdAndAccionId(Long rolId, Long recursoId, Long accionId);
}
