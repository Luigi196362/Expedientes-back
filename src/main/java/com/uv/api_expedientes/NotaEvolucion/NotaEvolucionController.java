package com.uv.api_expedientes.NotaEvolucion;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registros")
public class NotaEvolucionController {

    @Autowired
    NotaEvolucionService notaEvolucionService;

    // @GetMapping()
    // public ArrayList<NotaEvolucion> obtenerNotas() {
    // return notaEvolucionService.obtenerNotas();
    // }

    // @GetMapping(path = "/{id}")
    // public Optional<NotaEvolucion> obtenerPorId(@PathVariable("id") Long id) {
    // return this.notaEvolucionService.obtenerPorId(id);
    // }

    @PostMapping(path = "/crear/nota/{Username}/{idPaciente}")
    public ResponseEntity<Map<String, String>> guardarNota(@PathVariable String Username,
            @PathVariable long idPaciente, @RequestBody NotaEvolucion notaEvolucion) {
        this.notaEvolucionService.guardarNota(Username, idPaciente, notaEvolucion);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Registro exitoso");

        return ResponseEntity.ok(response);
    }

}
