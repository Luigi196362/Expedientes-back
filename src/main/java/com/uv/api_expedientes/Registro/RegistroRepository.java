package com.uv.api_expedientes.Registro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uv.api_expedientes.Registro.dto.RegistroDTO;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {

}
