package com.uv.api_expedientes.Registro;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegistroService {

    @Autowired
    RegistroRepository registroRepository;

    public ArrayList<Registro> obtenerRegistros() {
        return (ArrayList<Registro>) registroRepository.findAll();
    }

        public Optional<Registro> obtenerPorId(Long id) {
        return registroRepository.findById(id);
    }
}
