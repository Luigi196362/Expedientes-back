package com.uv.api_expedientes.Pacientes;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uv.api_expedientes.Pacientes.dto.PacienteEditDto;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    public ArrayList<Paciente> obtenerPacientes() {
        // Filtrar si estan activos
        return (ArrayList<Paciente>) pacienteRepository.findByActivoTrue();
    }

    public Paciente guardarPaciente(Paciente paciente) {
        Paciente nuevoPaciente = Paciente.builder()
                .matricula(paciente.getMatricula())
                .nombre(paciente.getNombre())
                .sexo(paciente.getSexo())
                .fecha_nacimiento(paciente.getFecha_nacimiento())
                .grupo(paciente.getGrupo())
                .semestre(paciente.getSemestre())
                .telefono(paciente.getTelefono())
                .programa_educativo(paciente.getPrograma_educativo())
                .ocupacion(paciente.getOcupacion())
                .residencia(paciente.getResidencia())
                .religion(paciente.getReligion())
                .escolaridad(paciente.getEscolaridad())
                .nss(paciente.getNss())
                .origen(paciente.getOrigen())
                .estado_civil(paciente.getEstado_civil())
                .facultad(paciente.getFacultad())
                .fecha_creacion(new Date())
                .activo(true)
                .build();

        return pacienteRepository.save(nuevoPaciente);
    }

    public String actualizarPaciente(long id, PacienteEditDto pacienteEditDto) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        if (!paciente.isActivo()) {
            throw new RuntimeException("Paciente no activo");
        }

        Optional.ofNullable(pacienteEditDto.getMatricula()).ifPresent(paciente::setMatricula);
        Optional.ofNullable(pacienteEditDto.getNombre()).ifPresent(paciente::setNombre);
        Optional.ofNullable(pacienteEditDto.getSexo()).ifPresent(paciente::setSexo);
        Optional.ofNullable(pacienteEditDto.getFecha_nacimiento()).ifPresent(paciente::setFecha_nacimiento);
        Optional.ofNullable(pacienteEditDto.getGrupo()).ifPresent(paciente::setGrupo);
        Optional.ofNullable(pacienteEditDto.getSemestre()).ifPresent(paciente::setSemestre);
        Optional.ofNullable(pacienteEditDto.getTelefono()).ifPresent(paciente::setTelefono);
        Optional.ofNullable(pacienteEditDto.getPrograma_educativo()).ifPresent(paciente::setPrograma_educativo);
        Optional.ofNullable(pacienteEditDto.getOcupacion()).ifPresent(paciente::setOcupacion);
        Optional.ofNullable(pacienteEditDto.getResidencia()).ifPresent(paciente::setResidencia);
        Optional.ofNullable(pacienteEditDto.getReligion()).ifPresent(paciente::setReligion);
        Optional.ofNullable(pacienteEditDto.getEscolaridad()).ifPresent(paciente::setEscolaridad);
        Optional.ofNullable(pacienteEditDto.getNss()).ifPresent(paciente::setNss);
        Optional.ofNullable(pacienteEditDto.getOrigen()).ifPresent(paciente::setOrigen);
        Optional.ofNullable(pacienteEditDto.getEstado_civil()).ifPresent(paciente::setEstado_civil);
        Optional.ofNullable(pacienteEditDto.getFacultad()).ifPresent(paciente::setFacultad);

        pacienteRepository.save(paciente);
        return "Paciente editado correctamente";
    }

    public Optional<Paciente> obtenerPorId(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isEmpty()) {
            throw new RuntimeException("Paciente no encontrado");
        }
        if (!paciente.get().isActivo()) {
            throw new RuntimeException("Paciente no está activo");
        }
        return paciente;
    }

    public String desactivarPaciente(Long id) {
        try {
            Paciente paciente = pacienteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
            if (!paciente.isActivo()) {
                return "Paciente ya está desactivado";
            }
            paciente.setActivo(false);
            pacienteRepository.save(paciente);
            return "Se eliminó el paciente";
        } catch (Exception e) {
            throw new RuntimeException("No se pudo eliminar el paciente");
        }
    }

}
