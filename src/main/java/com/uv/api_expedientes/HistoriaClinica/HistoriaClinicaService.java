package com.uv.api_expedientes.HistoriaClinica;

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
public class HistoriaClinicaService {

    @Autowired
    HistoriaClinicaRepository historiaClinicaRepository;

    @Autowired
    UserRepository usuarioRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    RegistroRepository registroRepository;

    public String guardarHistoria(String Username, long idPaciente, HistoriaClinica historiaClinica) {

        Registro nuevoRegistro = new Registro();
        HistoriaClinica nuevaHistoriaClinica = new HistoriaClinica();

        User usuario = usuarioRepository.findByUsername(Username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        nuevoRegistro.setUsuario(usuario);

        nuevoRegistro.setFecha_creacion(new Date());
        nuevoRegistro.setTipoRegistro("Historia clínica");

        Paciente pacienteRegistro = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        nuevoRegistro.setPaciente(pacienteRegistro);

        Registro registroGuardado = registroRepository.save(nuevoRegistro);

        nuevaHistoriaClinica.setAntecedentes_heredo_familiares(historiaClinica.getAntecedentes_heredo_familiares());
        nuevaHistoriaClinica
                .setAntecedentes_personales_no_patologicos(historiaClinica.getAntecedentes_personales_no_patologicos());
        nuevaHistoriaClinica
                .setAntecedentes_personales_patologicos(historiaClinica.getAntecedentes_personales_patologicos());
        nuevaHistoriaClinica.setMedicamentos_actuales(historiaClinica.getMedicamentos_actuales());
        nuevaHistoriaClinica.setDiagnostico_inicial(historiaClinica.getDiagnostico_inicial());
        nuevaHistoriaClinica.setTratamiento(historiaClinica.getTratamiento());
        nuevaHistoriaClinica.setObservaciones(historiaClinica.getObservaciones());
        nuevaHistoriaClinica.setAlergias(historiaClinica.getAlergias());
        nuevaHistoriaClinica.setRegistro(registroGuardado);
        historiaClinicaRepository.save(nuevaHistoriaClinica);

        return "Registro con éxito";
    }

}
