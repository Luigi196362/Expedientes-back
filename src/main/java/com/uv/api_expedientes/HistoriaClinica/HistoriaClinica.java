package com.uv.api_expedientes.HistoriaClinica;

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
@Table(name = "HistoriaClinica")
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "registro_id")
    private Registro registro;

    private String antecedentes_heredo_familiares;
    private String antecedentes_personales_no_patologicos;
    private String antecedentes_personales_patologicos;
    private String medicamentos_actuales;
    private String diagnostico_inicial;
    private String tratamiento;
    private String observaciones;
    private String alergias;

}
