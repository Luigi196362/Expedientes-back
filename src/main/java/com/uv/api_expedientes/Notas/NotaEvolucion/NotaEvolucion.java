package com.uv.api_expedientes.Notas.NotaEvolucion;

import com.uv.api_expedientes.Registro.Registro;

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
@Table(name = "NotaEvolucion")
public class NotaEvolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "registro_id")
    private Registro registro;

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

}