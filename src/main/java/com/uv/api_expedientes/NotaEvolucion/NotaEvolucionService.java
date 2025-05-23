package com.uv.api_expedientes.NotaEvolucion;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uv.api_expedientes.Pacientes.Paciente;
import com.uv.api_expedientes.Pacientes.PacienteRepository;
import com.uv.api_expedientes.Registro.Registro;
import com.uv.api_expedientes.Registro.RegistroRepository;
import com.uv.api_expedientes.Users.User;
import com.uv.api_expedientes.Users.UserRepository;

@Service
public class NotaEvolucionService {

    @Autowired
    NotaEvolucionRepository notaEvolucionRepository;

    @Autowired
    UserRepository usuarioRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    RegistroRepository registroRepository;

    // public ArrayList<NotaEvolucion> obtenerNotas() {
    // return (ArrayList<NotaEvolucion>) notaEvolucionRepository.findAll();
    // }

    // public Optional<NotaEvolucion> obtenerPorId(Long id) {
    // return notaEvolucionRepository.findById(id);
    // }

    public String guardarNota(String Username, long idPaciente, NotaEvolucion notaEvolucion) {

        Registro nuevoRegistro = new Registro();
        NotaEvolucion nuevaNotaEvolucion = new NotaEvolucion();

        User usuario = usuarioRepository.findByUsername(Username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        nuevoRegistro.setUsuario(usuario);

        nuevoRegistro.setFecha_creacion(new Date());
        nuevoRegistro.setTipoRegistro("Nota de evolución");

        Paciente pacienteRegistro = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        nuevoRegistro.setPaciente(pacienteRegistro);

        Registro registroGuardado = registroRepository.save(nuevoRegistro);

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
        nuevaNotaEvolucion.setRegistro(registroGuardado);
        notaEvolucionRepository.save(nuevaNotaEvolucion);
        return "Registro con éxito";
    }

}
