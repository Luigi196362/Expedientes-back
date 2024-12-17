package com.uv.api_expedientes.NotaEvolucion;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/NotaEvolucion")
public class NotaEvolucionController {

    @Autowired
    NotaEvolucionService notaEvolucionService;

    @GetMapping()
    public ArrayList<NotaEvolucion> obtenerNotas() {
        return notaEvolucionService.obtenerNotas();
    }

    @GetMapping(path = "/{id}")
    public Optional<NotaEvolucion> obtenerPorId(@PathVariable("id") Long id) {
        return this.notaEvolucionService.obtenerPorId(id);
    }

    @PostMapping(path ="/{idRegistro}/{idPaciente}")
    public NotaEvolucion guardarNota(@PathVariable("idRegistro") long idUsuario,@PathVariable("idPaciente") long idPaciente, @RequestBody NotaEvolucion notaEvolucion) {
        return this.notaEvolucionService.guardarNota(idUsuario, idPaciente, notaEvolucion);
    }

}
