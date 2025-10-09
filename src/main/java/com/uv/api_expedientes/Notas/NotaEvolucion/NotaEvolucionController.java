package com.uv.api_expedientes.Notas.NotaEvolucion;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/registros")
@RequiredArgsConstructor
public class NotaEvolucionController {

    private final NotaEvolucionService notaEvolucionService;

    @PostMapping("/Crear/Nota/{idPaciente}")
    public ResponseEntity<Map<String, String>> crearHistoria(HttpServletRequest request,
            @PathVariable Integer idPaciente,
            @RequestBody NotaEvolucion notaEvolucion) {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", notaEvolucionService.guardarNota(request, idPaciente, notaEvolucion));
        return ResponseEntity.ok(response);
    }
}