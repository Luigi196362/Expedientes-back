package com.uv.api_expedientes.Notas.HistoriaClinica;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.uv.api_expedientes.Pacientes.Paciente;
import com.uv.api_expedientes.Users.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "historia_clinica")
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private String antecedentes_heredo_familiares;
    private String antecedentes_personales_no_patologicos;
    private String antecedentes_personales_patologicos;
    private String medicamentos_actuales;
    private String diagnostico_inicial;
    private String tratamiento;
    private String observaciones;
    private String alergias;
    private Date fecha_creacion;

}