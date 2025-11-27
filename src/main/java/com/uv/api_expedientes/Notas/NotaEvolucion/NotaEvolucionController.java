package com.uv.api_expedientes.Notas.NotaEvolucion;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uv.api_expedientes.Notas.NotaEvolucion.dtos.NotaEvolucionDto;

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
            @RequestBody NotaEvolucionDto notaEvolucionDto) {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", notaEvolucionService.guardarNota(request, idPaciente, notaEvolucionDto));
        return ResponseEntity.ok(response);
    }

    @GetMapping("Ver/Nota/{id}")
    public ResponseEntity<NotaEvolucionDto> obtenerNota(@PathVariable Integer id) {
        return ResponseEntity.ok(notaEvolucionService.obtenerPorId(id));
    }
}