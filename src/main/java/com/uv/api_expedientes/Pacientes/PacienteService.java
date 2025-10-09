package com.uv.api_expedientes.Pacientes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.uv.api_expedientes.Notas.HistoriaClinica.HistoriaClinica;
import com.uv.api_expedientes.Notas.HistoriaClinica.HistoriaClinicaRepository;
import com.uv.api_expedientes.Notas.NotaEvolucion.NotaEvolucion;
import com.uv.api_expedientes.Notas.NotaEvolucion.NotaEvolucionRepository;
import com.uv.api_expedientes.Pacientes.dtos.AllPacientesDto;
import com.uv.api_expedientes.Pacientes.dtos.IdPacienteDto;
import com.uv.api_expedientes.Pacientes.dtos.PacienteEditDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    private final NotaEvolucionRepository notaEvolucionRepository;

    private final HistoriaClinicaRepository historiaClinicaRepository;

    public AllPacientesDto obtenerPacientes() {

        List<Paciente> pacientes = pacienteRepository.findByActivoTrue();

        int cantidad_mujeres = (int) pacientes.stream().filter(p -> p.getSexo() == 2).count();
        int cantidad_hombres = (int) pacientes.stream().filter(p -> p.getSexo() == 1).count();
        int cantidad_registros = pacientes.size();

        List<AllPacientesDto.PacienteInfo> pacientesInfo = new ArrayList<>();
        for (Paciente p : pacientes) {
            AllPacientesDto.PacienteInfo info = new AllPacientesDto.PacienteInfo();
            info.setId(p.getId());
            info.setMatricula(p.getMatricula());
            info.setNombre(p.getNombre());
            info.setSexo(p.getSexo());
            info.setTelefono(p.getTelefono());
            info.setFecha_nacimiento(p.getFecha_nacimiento());
            info.setFecha_creacion(p.getFecha_creacion());
            pacientesInfo.add(info);
        }

        AllPacientesDto dto = AllPacientesDto.builder()
                .cantidad_registros(cantidad_registros)
                .cantidad_mujeres(cantidad_mujeres)
                .cantidad_hombres(cantidad_hombres)
                .pacientes(pacientesInfo)
                .build();

        return dto;
    }

    public Void guardarPaciente(Paciente paciente) {
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
        pacienteRepository.save(nuevoPaciente);
        return null;
    }

    public Void actualizarPaciente(int id, PacienteEditDto pacienteEditDto) {
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
        return null;
    }

    public IdPacienteDto obtenerPorId(int id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        if (!paciente.isActivo()) {
            throw new RuntimeException("Paciente no está activo");
        }

        List<NotaEvolucion> notasEvolucion = notaEvolucionRepository.findByPacienteId(id);

        List<HistoriaClinica> historiasClinicas = historiaClinicaRepository.findByPacienteId(id);

        List<IdPacienteDto.Registros> registros = new ArrayList<>();

        for (NotaEvolucion nota : notasEvolucion) {
            IdPacienteDto.Registros registro = new IdPacienteDto.Registros();
            registro.setRegistro_id(nota.getId());
            registro.setTipo_registro("Nota de Evolución");
            registro.setFecha_creacion(nota.getFecha_creacion());
            registros.add(registro);
        }
        for (HistoriaClinica historia : historiasClinicas) {
            IdPacienteDto.Registros registro = new IdPacienteDto.Registros();
            registro.setRegistro_id(historia.getId());
            registro.setTipo_registro("Historia Clínica");
            registro.setFecha_creacion(historia.getFecha_creacion());
            registros.add(registro);
        }

        registros.sort((r1, r2) -> r2.getFecha_creacion().compareTo(r1.getFecha_creacion()));

        IdPacienteDto idPacienteDto = IdPacienteDto.builder()
                .id(paciente.getId())
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
                .registros(registros)
                .build();
        return idPacienteDto;
    }

    public String desactivarPaciente(int id) {
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