package com.uv.api_expedientes.Notas.NotaEvolucion;

import java.util.Date;

import org.springframework.stereotype.Service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.uv.api_expedientes.Pacientes.Paciente;
import com.uv.api_expedientes.Pacientes.PacienteRepository;
import com.uv.api_expedientes.Users.User;
import com.uv.api_expedientes.Users.UserRepository;
import com.uv.api_expedientes.jwt.JwtService;

import com.uv.api_expedientes.Notas.NotaEvolucion.dtos.NotaEvolucionDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotaEvolucionService {

    private final NotaEvolucionRepository notaEvolucionRepository;

    private final UserRepository usuarioRepository;

    private final PacienteRepository pacienteRepository;

    private final JwtService jwtService;

    public String guardarNota(HttpServletRequest request, Integer idPaciente,
            NotaEvolucionDto notaEvolucionDto) {

        NotaEvolucion nuevaNotaEvolucion = new NotaEvolucion();

        String Username = jwtService.getUsernameFromToken(jwtService.getTokenFromRequest(request));

        User usuario = usuarioRepository.findByUsername(Username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Paciente pacienteRegistro = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        nuevaNotaEvolucion.setUsuario(usuario);
        nuevaNotaEvolucion.setPaciente(pacienteRegistro);
        nuevaNotaEvolucion.setFecha_creacion(new Date());
        nuevaNotaEvolucion.setInterrogatorio(notaEvolucionDto.getInterrogatorio());
        nuevaNotaEvolucion.setPeso(notaEvolucionDto.getPeso());
        nuevaNotaEvolucion.setTalla(notaEvolucionDto.getTalla());
        nuevaNotaEvolucion.setImc(notaEvolucionDto.getImc());
        nuevaNotaEvolucion.setTa(notaEvolucionDto.getTa());
        nuevaNotaEvolucion.setFc(notaEvolucionDto.getFc());
        nuevaNotaEvolucion.setFr(notaEvolucionDto.getFr());
        nuevaNotaEvolucion.setTemperatura(notaEvolucionDto.getTemperatura());
        nuevaNotaEvolucion.setSaturacion(notaEvolucionDto.getSaturacion());
        nuevaNotaEvolucion.setGlicemia(notaEvolucionDto.getGlicemia());
        nuevaNotaEvolucion.setHemoglobina(notaEvolucionDto.getHemoglobina());
        nuevaNotaEvolucion.setHemotipo(notaEvolucionDto.getHemotipo());
        nuevaNotaEvolucion.setPadecimiento(notaEvolucionDto.getPadecimiento());
        nuevaNotaEvolucion.setExploracion(notaEvolucionDto.getExploracion());
        nuevaNotaEvolucion.setAnalisis(notaEvolucionDto.getAnalisis());
        nuevaNotaEvolucion.setPlan(notaEvolucionDto.getPlan());
        nuevaNotaEvolucion.setDiagnostico(notaEvolucionDto.getDiagnostico());
        nuevaNotaEvolucion.setTratamiento(notaEvolucionDto.getTratamiento());
        notaEvolucionRepository.save(nuevaNotaEvolucion);
        return "Registro con éxito";
    }

    public NotaEvolucionDto obtenerPorId(int id) {
        NotaEvolucion nota = notaEvolucionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota de evolución no encontrada"));

        return NotaEvolucionDto.builder()
                .id(nota.getId())
                .interrogatorio(nota.getInterrogatorio())
                .peso(nota.getPeso())
                .talla(nota.getTalla())
                .imc(nota.getImc())
                .ta(nota.getTa())
                .fc(nota.getFc())
                .fr(nota.getFr())
                .temperatura(nota.getTemperatura())
                .saturacion(nota.getSaturacion())
                .glicemia(nota.getGlicemia())
                .hemoglobina(nota.getHemoglobina())
                .hemotipo(nota.getHemotipo())
                .padecimiento(nota.getPadecimiento())
                .exploracion(nota.getExploracion())
                .analisis(nota.getAnalisis())
                .plan(nota.getPlan())
                .diagnostico(nota.getDiagnostico())
                .tratamiento(nota.getTratamiento())
                .fecha_creacion(nota.getFecha_creacion())
                .build();
    }

}