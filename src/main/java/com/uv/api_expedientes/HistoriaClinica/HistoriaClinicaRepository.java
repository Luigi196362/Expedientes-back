package com.uv.api_expedientes.HistoriaClinica;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriaClinicaRepository extends CrudRepository<HistoriaClinica, Long> {

}
