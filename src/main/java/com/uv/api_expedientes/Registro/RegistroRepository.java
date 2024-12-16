package com.uv.api_expedientes.Registro;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends CrudRepository<Registro, Long>{
    
}
