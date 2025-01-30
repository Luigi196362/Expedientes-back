package com.uv.api_expedientes.Pacientes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Long> {
    // Filtrar si estan activos
    List<Paciente> findByActivoTrue();
}
