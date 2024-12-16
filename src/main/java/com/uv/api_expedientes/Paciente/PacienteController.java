package com.uv.api_expedientes.Paciente;

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

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping()
    public ArrayList<Paciente> obtenerPacientes() {
        return pacienteService.obtenerPacientes();
    }

    @PostMapping()
    public Paciente guardarPaciente(@RequestBody Paciente paciente) {
        return this.pacienteService.guardarPaciente(paciente);
    }

    @GetMapping(path = "/{id}")
    public Optional<Paciente> obtenerPorId(@PathVariable("id") Long id) {
        return this.pacienteService.obtenerPorId(id);
    }

    @PutMapping(path = "/{id}")
    public Paciente actualizarPaciente(@PathVariable("id") long id, @RequestBody Paciente paciente) {
        return this.pacienteService.actualizarPaciente(id, paciente);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPaciente(@PathVariable("id") long id) {
        boolean ok = this.pacienteService.desactivar(id);
        if (ok) {
            return "Se elimino el paciente " + id;
        } else {
            return "No se pudo eliminar el paciente " + id;
        }
    }
}
