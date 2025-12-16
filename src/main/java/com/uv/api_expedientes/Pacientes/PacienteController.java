package com.uv.api_expedientes.Pacientes;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uv.api_expedientes.Pacientes.dtos.AllPacientesDto;
import com.uv.api_expedientes.Pacientes.dtos.IdPacienteDto;
import com.uv.api_expedientes.Pacientes.dtos.PacienteEditDto;
import com.uv.api_expedientes.Pacientes.dtos.SatisticsPacienteDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping("/Ver")
    public AllPacientesDto obtenerPacientes() {
        return pacienteService.obtenerPacientes();
    }

    @PostMapping("/Crear")
    public ResponseEntity<Void> guardarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @GetMapping("/Ver/{id}")
    public ResponseEntity<IdPacienteDto> obtenerPorId(@PathVariable("id") int id) {
        return ResponseEntity.ok(pacienteService.obtenerPorId(id));
    }

    @PutMapping("/Editar/{id}")
    public ResponseEntity<Void> actualizarPaciente(@PathVariable("id") int id,
            @RequestBody PacienteEditDto pacienteEditDto) {
        return ResponseEntity.ok(pacienteService.actualizarPaciente(id, pacienteEditDto));
    }

    @DeleteMapping("/Eliminar/{id}")
    public ResponseEntity<String> desactivarPaciente(@PathVariable("id") int id) {
        return ResponseEntity.ok(pacienteService.desactivarPaciente(id));
    }

    @GetMapping("/Estadisticas")
    public ResponseEntity<SatisticsPacienteDto> obtenerEstadisticas() {
        return ResponseEntity.ok(pacienteService.obtenerEstadisticas());
    }

    @GetMapping("/Estadisticas/Rango")
    public ResponseEntity<SatisticsPacienteDto> obtenerEstadisticasRango(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date startDate,

            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date endDate) {
        return ResponseEntity.ok(
                pacienteService.obtenerEstadisticasRango(startDate, endDate));
    }
}