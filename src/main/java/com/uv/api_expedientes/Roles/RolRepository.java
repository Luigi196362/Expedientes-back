package com.uv.api_expedientes.Roles;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolRepository extends CrudRepository<Rol, Long> {
     List<Rol> findByActivoTrue();
}
