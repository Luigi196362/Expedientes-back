package com.uv.api_expedientes.NotaEvolucion;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaEvolucionRepository extends CrudRepository<NotaEvolucion, Long> {

}
