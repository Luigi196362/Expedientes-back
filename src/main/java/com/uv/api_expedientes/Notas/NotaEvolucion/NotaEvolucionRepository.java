package com.uv.api_expedientes.Notas.NotaEvolucion;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaEvolucionRepository extends CrudRepository<NotaEvolucion, Integer> {
    List<NotaEvolucion> findByPacienteId(Integer idPaciente);
}