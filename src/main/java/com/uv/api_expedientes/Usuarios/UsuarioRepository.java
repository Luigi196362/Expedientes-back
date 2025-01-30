package com.uv.api_expedientes.Usuarios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    // Filtrar si estan activos
    List<Usuario> findByActivoTrue();

    Optional<Usuario> findByNombre(String nombre);
}
