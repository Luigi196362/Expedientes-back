package com.uv.api_expedientes.Pacientes;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    public ArrayList<Paciente> obtenerPacientes() {
        // Filtrar si estan activos
        return (ArrayList<Paciente>) pacienteRepository.findByActivoTrue();
    }

    public Paciente guardarPaciente(Paciente paciente) {
        Paciente nuevoPaciente = new Paciente();
        nuevoPaciente.setMatricula(paciente.getMatricula());
        nuevoPaciente.setNombre(paciente.getNombre());
        nuevoPaciente.setSexo(paciente.getSexo());
        nuevoPaciente.setFecha_nacimiento(paciente.getFecha_nacimiento());
        nuevoPaciente.setGrupo(paciente.getGrupo());
        nuevoPaciente.setSemestre(paciente.getSemestre());
        nuevoPaciente.setTelefono(paciente.getTelefono());
        nuevoPaciente.setPrograma_educativo(paciente.getPrograma_educativo());
        nuevoPaciente.setOcupacion(paciente.getOcupacion());
        nuevoPaciente.setResidencia(paciente.getResidencia());
        nuevoPaciente.setReligion(paciente.getReligion());
        nuevoPaciente.setEscolaridad(paciente.getEscolaridad());
        nuevoPaciente.setNss(paciente.getNss());
        nuevoPaciente.setOrigen(paciente.getOrigen());
        nuevoPaciente.setEstado_civil(paciente.getEstado_civil());
        nuevoPaciente.setFacultad(paciente.getFacultad());
        nuevoPaciente.setFecha_creacion(new Date());
        nuevoPaciente.setActivo(true);
        return pacienteRepository.save(nuevoPaciente);
    }

    public Paciente actualizarPaciente(long id, Paciente paciente) {
        Paciente actualizarPaciente = pacienteRepository.findById(id).get();
        actualizarPaciente.setMatricula(paciente.getMatricula());
        actualizarPaciente.setNombre(paciente.getNombre());
        actualizarPaciente.setSexo(paciente.getSexo());
        actualizarPaciente.setFecha_nacimiento(paciente.getFecha_nacimiento());
        actualizarPaciente.setGrupo(paciente.getGrupo());
        actualizarPaciente.setSemestre(paciente.getSemestre());
        actualizarPaciente.setTelefono(paciente.getTelefono());
        actualizarPaciente.setPrograma_educativo(paciente.getPrograma_educativo());
        actualizarPaciente.setOcupacion(paciente.getOcupacion());
        actualizarPaciente.setResidencia(paciente.getResidencia());
        actualizarPaciente.setReligion(paciente.getReligion());
        actualizarPaciente.setEscolaridad(paciente.getEscolaridad());
        actualizarPaciente.setNss(paciente.getNss());
        actualizarPaciente.setOrigen(paciente.getOrigen());
        actualizarPaciente.setEstado_civil(paciente.getEstado_civil());
        actualizarPaciente.setFacultad(paciente.getFacultad());
        actualizarPaciente.setFecha_creacion(paciente.getFecha_creacion());

        return pacienteRepository.save(actualizarPaciente);
    }

    public Optional<Paciente> obtenerPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public boolean desactivar(Long id) {
        try {
            Paciente paciente = pacienteRepository.findById(id).get();
            paciente.setActivo(false);
            pacienteRepository.save(paciente);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
