package com.uv.api_expedientes.AccessControl.Recursos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursoRepository extends CrudRepository<Recurso, Integer> {

}
