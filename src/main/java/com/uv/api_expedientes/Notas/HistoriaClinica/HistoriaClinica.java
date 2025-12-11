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

    // Identificacion
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private Date fecha_creacion;

    // Interrogatorio y exploracion
    private String motivo_consulta;
    private String interrogatorio;
    private String padecimiento_actual;
    private String exploracion_fisica;

    // Signos vitales
    private int peso;
    private int talla;
    private float imc;
    private String tension_arterial;
    private int frecuencia_cardiaca;
    private int frecuencia_respiratoria;
    private int temperatura;
    private int saturacion;
    private int glicemia;
    private int hemoglobina;
    private String hemotipo;

    // Antecedentes
    private String antecedentes_heredo_familiares;
    private String antecedentes_no_patologicos;
    private String antecedentes_patologicos;
    private String antecedentes_quirurgicos;
    private String medicamentos_actuales;
    private String alergias;
    private String antecedentes_gineco_obstetricos;
    private String cancer_prostata;
    private String vacunas;
    private String adicciones;

    // Diagnostico y medicaciones
    private String diagnostico;
    private String tratamiento;
    private String plan_tratamiento;
    private String observaciones;

}
