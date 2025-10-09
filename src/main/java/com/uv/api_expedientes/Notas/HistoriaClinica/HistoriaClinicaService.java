package com.uv.api_expedientes.Notas.HistoriaClinica;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.uv.api_expedientes.Pacientes.Paciente;
import com.uv.api_expedientes.Pacientes.PacienteRepository;
import com.uv.api_expedientes.Users.User;
import com.uv.api_expedientes.Users.UserRepository;
import com.uv.api_expedientes.jwt.JwtService;

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
                        HistoriaClinica historiaClinica) {

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
                                .setAntecedentes_heredo_familiares(historiaClinica.getAntecedentes_heredo_familiares());
                nuevaHistoriaClinica.setAntecedentes_personales_no_patologicos(
                                historiaClinica.getAntecedentes_personales_no_patologicos());
                nuevaHistoriaClinica.setAntecedentes_personales_patologicos(
                                historiaClinica.getAntecedentes_personales_patologicos());
                nuevaHistoriaClinica.setMedicamentos_actuales(historiaClinica.getMedicamentos_actuales());
                nuevaHistoriaClinica.setDiagnostico_inicial(historiaClinica.getDiagnostico_inicial());
                nuevaHistoriaClinica.setTratamiento(historiaClinica.getTratamiento());
                nuevaHistoriaClinica.setObservaciones(historiaClinica.getObservaciones());
                nuevaHistoriaClinica.setAlergias(historiaClinica.getAlergias());
                historiaClinicaRepository.save(nuevaHistoriaClinica);

                return "Registro con éxito";
        }

}