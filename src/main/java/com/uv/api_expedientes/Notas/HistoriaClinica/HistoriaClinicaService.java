package com.uv.api_expedientes.Notas.HistoriaClinica;

import java.util.Date;

import org.springframework.stereotype.Service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.uv.api_expedientes.Pacientes.Paciente;
import com.uv.api_expedientes.Pacientes.PacienteRepository;
import com.uv.api_expedientes.Users.User;
import com.uv.api_expedientes.Users.UserRepository;
import com.uv.api_expedientes.jwt.JwtService;

import com.uv.api_expedientes.Notas.HistoriaClinica.dtos.HistoriaClinicaDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoriaClinicaService {

        private final HistoriaClinicaRepository historiaClinicaRepository;

        private final UserRepository usuarioRepository;

        private final PacienteRepository pacienteRepository;

        private final JwtService jwtService;

        public String guardarHistoria(HttpServletRequest request, int idPaciente,
                        HistoriaClinicaDto historiaClinicaDto) {

                HistoriaClinica nuevaHistoriaClinica = new HistoriaClinica();

                String Username = jwtService.getUsernameFromToken(jwtService.getTokenFromRequest(request));

                User usuario = usuarioRepository.findByUsername(Username)
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                Paciente pacienteRegistro = pacienteRepository.findById(idPaciente)
                                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

                nuevaHistoriaClinica.setUsuario(usuario);
                nuevaHistoriaClinica.setPaciente(pacienteRegistro);
                nuevaHistoriaClinica.setFecha_creacion(new Date());
                nuevaHistoriaClinica
                                .setAntecedentes_heredo_familiares(historiaClinicaDto.getAntecedentes_heredo_familiares());
                nuevaHistoriaClinica.setAntecedentes_personales_no_patologicos(
                                historiaClinicaDto.getAntecedentes_personales_no_patologicos());
                nuevaHistoriaClinica.setAntecedentes_personales_patologicos(
                                historiaClinicaDto.getAntecedentes_personales_patologicos());
                nuevaHistoriaClinica.setMedicamentos_actuales(historiaClinicaDto.getMedicamentos_actuales());
                nuevaHistoriaClinica.setDiagnostico_inicial(historiaClinicaDto.getDiagnostico_inicial());
                nuevaHistoriaClinica.setTratamiento(historiaClinicaDto.getTratamiento());
                nuevaHistoriaClinica.setObservaciones(historiaClinicaDto.getObservaciones());
                nuevaHistoriaClinica.setAlergias(historiaClinicaDto.getAlergias());
                historiaClinicaRepository.save(nuevaHistoriaClinica);

                return "Registro con éxito";
        }

        public HistoriaClinicaDto obtenerPorId(int id) {
                HistoriaClinica historia = historiaClinicaRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Historia clínica no encontrada"));

                return HistoriaClinicaDto.builder()
                                .id(historia.getId())
                                .antecedentes_heredo_familiares(historia.getAntecedentes_heredo_familiares())
                                .antecedentes_personales_no_patologicos(historia.getAntecedentes_personales_no_patologicos())
                                .antecedentes_personales_patologicos(historia.getAntecedentes_personales_patologicos())
                                .medicamentos_actuales(historia.getMedicamentos_actuales())
                                .diagnostico_inicial(historia.getDiagnostico_inicial())
                                .tratamiento(historia.getTratamiento())
                                .observaciones(historia.getObservaciones())
                                .alergias(historia.getAlergias())
                                .fecha_creacion(historia.getFecha_creacion())
                                .build();
        }

}