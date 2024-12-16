package com.uv.api_expedientes.Registro;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registro")
public class RegistroController {
    @Autowired
    RegistroService registroService;

    @GetMapping()
    public ArrayList<Registro> obtenerRegistros() {
        return registroService.obtenerRegistros();
    }

    @GetMapping(path = "/{id}")
    public Optional<Registro> obtenerPorId(@PathVariable("id") Long id) {
        return this.registroService.obtenerPorId(id);
    }

}
