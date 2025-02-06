package com.uv.api_expedientes.Pacientes;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uv.api_expedientes.Pacientes.dto.PacienteEditDto;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping("/ver")
    public ArrayList<Paciente> obtenerPacientes() {
        return pacienteService.obtenerPacientes();
    }

    @PostMapping("/crear")
    public Paciente guardarPaciente(@RequestBody Paciente paciente) {
        return this.pacienteService.guardarPaciente(paciente);
    }

    @GetMapping("/ver/{id}")
    public Optional<Paciente> obtenerPorId(@PathVariable("id") Long id) {
        return this.pacienteService.obtenerPorId(id);
    }

    @PutMapping("/editar/{id}")
    public String actualizarPaciente(@PathVariable("id") long id,
            @RequestBody PacienteEditDto pacienteEditDto) {
        return this.pacienteService.actualizarPaciente(id, pacienteEditDto);
    }

    @DeleteMapping("/eliminar/{id}")
    public String desactivarPaciente(@PathVariable("id") long id) {
        return this.pacienteService.desactivarPaciente(id);
    }
}
