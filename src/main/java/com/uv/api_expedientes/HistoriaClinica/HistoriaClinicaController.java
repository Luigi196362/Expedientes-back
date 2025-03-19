package com.uv.api_expedientes.HistoriaClinica;

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
public class HistoriaClinicaController {

    @Autowired
    HistoriaClinicaService historiaClinicaService;

    @PostMapping(path = "/crear/historia/{Username}/{idPaciente}")
    public ResponseEntity<Map<String, String>> guardarHistoria(@PathVariable String Username,
            @PathVariable long idPaciente, @RequestBody HistoriaClinica historiaClinica) {
        this.historiaClinicaService.guardarHistoria(Username, idPaciente, historiaClinica);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Registro exitoso");

        return ResponseEntity.ok(response);
    }

}
