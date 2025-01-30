package com.uv.api_expedientes.Permisos.Recursos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface RecursoRepository extends CrudRepository<Recurso, Long> {
    Optional<Recurso> findByNombre(String nombre);
}
