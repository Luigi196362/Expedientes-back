package com.uv.api_expedientes.Notas.NotaEvolucion;

import java.util.Date;

import com.uv.api_expedientes.Pacientes.Paciente;
import com.uv.api_expedientes.Users.User;

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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notas_evolucion")
public class NotaEvolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private String interrogatorio;
    private int peso;
    private int talla;
    private float imc;
    private String ta;
    private int fc;
    private int fr;
    private int temperatura;
    private int saturacion;
    private int glicemia;
    private int hemoglobina;
    private String hemotipo;
    private String padecimiento;
    private String exploracion;
    private String analisis;
    private String plan;
    private String diagnostico;
    private String tratamiento;
    private Date fecha_creacion;
}