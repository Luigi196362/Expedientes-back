package com.uv.api_expedientes.Permisos.Roles;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends CrudRepository<Rol, Long> {
     Optional<Rol> findByNombre(String nombre);
}
