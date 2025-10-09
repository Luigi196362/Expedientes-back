package com.uv.api_expedientes.Notas.NotaEvolucion;

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
public class NotaEvolucionService {

    private final NotaEvolucionRepository notaEvolucionRepository;

    private final UserRepository usuarioRepository;

    private final PacienteRepository pacienteRepository;

    private final JwtService jwtService;

    public String guardarNota(HttpServletRequest request, Integer idPaciente,
            NotaEvolucion notaEvolucion) {

        NotaEvolucion nuevaNotaEvolucion = new NotaEvolucion();

        String Username = jwtService.getUsernameFromToken(jwtService.getTokenFromRequest(request));

        User usuario = usuarioRepository.findByUsername(Username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Paciente pacienteRegistro = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        nuevaNotaEvolucion.setUsuario(usuario);
        nuevaNotaEvolucion.setPaciente(pacienteRegistro);
        nuevaNotaEvolucion.setFecha_creacion(new Date());
        nuevaNotaEvolucion.setInterrogatorio(notaEvolucion.getInterrogatorio());
        nuevaNotaEvolucion.setPeso(notaEvolucion.getPeso());
        nuevaNotaEvolucion.setTalla(notaEvolucion.getTalla());
        nuevaNotaEvolucion.setImc(notaEvolucion.getImc());
        nuevaNotaEvolucion.setTa(notaEvolucion.getTa());
        nuevaNotaEvolucion.setFc(notaEvolucion.getFc());
        nuevaNotaEvolucion.setFr(notaEvolucion.getFr());
        nuevaNotaEvolucion.setTemperatura(notaEvolucion.getTemperatura());
        nuevaNotaEvolucion.setSaturacion(notaEvolucion.getSaturacion());
        nuevaNotaEvolucion.setGlicemia(notaEvolucion.getGlicemia());
        nuevaNotaEvolucion.setHemoglobina(notaEvolucion.getHemoglobina());
        nuevaNotaEvolucion.setHemotipo(notaEvolucion.getHemotipo());
        nuevaNotaEvolucion.setPadecimiento(notaEvolucion.getPadecimiento());
        nuevaNotaEvolucion.setExploracion(notaEvolucion.getExploracion());
        nuevaNotaEvolucion.setAnalisis(notaEvolucion.getAnalisis());
        nuevaNotaEvolucion.setPlan(notaEvolucion.getPlan());
        nuevaNotaEvolucion.setDiagnostico(notaEvolucion.getDiagnostico());
        nuevaNotaEvolucion.setTratamiento(notaEvolucion.getTratamiento());
        notaEvolucionRepository.save(nuevaNotaEvolucion);
        return "Registro con éxito";
    }

}