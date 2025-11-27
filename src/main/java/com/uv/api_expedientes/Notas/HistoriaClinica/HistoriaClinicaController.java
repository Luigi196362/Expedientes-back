package com.uv.api_expedientes.Notas.HistoriaClinica;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uv.api_expedientes.Notas.HistoriaClinica.dtos.HistoriaClinicaDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/registros")
@RequiredArgsConstructor
public class HistoriaClinicaController {

    private final HistoriaClinicaService historiaClinicaService;

    @PostMapping("/Crear/Historia/{idPaciente}")
    public ResponseEntity<Map<String, String>> guardarHistoria(HttpServletRequest request,
            @PathVariable Integer idPaciente, @RequestBody HistoriaClinicaDto historiaClinicaDto) {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", historiaClinicaService.guardarHistoria(request, idPaciente, historiaClinicaDto));
        return ResponseEntity.ok(response);
    }

    @GetMapping("Ver/Historia/{id}")
    public ResponseEntity<HistoriaClinicaDto> obtenerHistoria(@PathVariable Integer id) {
        return ResponseEntity.ok(historiaClinicaService.obtenerPorId(id));
    }
}