package com.uv.api_expedientes.NotaEvolucion;

import com.uv.api_expedientes.Registro.Registro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "NotaEvolucion")
public class NotaEvolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    
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

    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getInterrogatorio() {
        return interrogatorio;
    }
    public void setInterrogatorio(String interrogatorio) {
        this.interrogatorio = interrogatorio;
    }
    public int getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }
    public int getTalla() {
        return talla;
    }
    public void setTalla(int talla) {
        this.talla = talla;
    }
    public float getImc() {
        return imc;
    }
    public void setImc(float imc) {
        this.imc = imc;
    }
    public String getTa() {
        return ta;
    }
    public void setTa(String ta) {
        this.ta = ta;
    }
    public int getFc() {
        return fc;
    }
    public void setFc(int fc) {
        this.fc = fc;
    }
    public int getFr() {
        return fr;
    }
    public void setFr(int fr) {
        this.fr = fr;
    }
    public int getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }
    public int getSaturacion() {
        return saturacion;
    }
    public void setSaturacion(int saturacion) {
        this.saturacion = saturacion;
    }
    public int getGlicemia() {
        return glicemia;
    }
    public void setGlicemia(int glicemia) {
        this.glicemia = glicemia;
    }
    public int getHemoglobina() {
        return hemoglobina;
    }
    public void setHemoglobina(int hemoglobina) {
        this.hemoglobina = hemoglobina;
    }
    public String getHemotipo() {
        return hemotipo;
    }
    public void setHemotipo(String hemotipo) {
        this.hemotipo = hemotipo;
    }
    public String getPadecimiento() {
        return padecimiento;
    }
    public void setPadecimiento(String padecimiento) {
        this.padecimiento = padecimiento;
    }
    public String getExploracion() {
        return exploracion;
    }
    public void setExploracion(String exploracion) {
        this.exploracion = exploracion;
    }
    public String getAnalisis() {
        return analisis;
    }
    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }
    public String getPlan() {
        return plan;
    }
    public void setPlan(String plan) {
        this.plan = plan;
    }
    public String getDiagnostico() {
        return diagnostico;
    }
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    public String getTratamiento() {
        return tratamiento;
    }
    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }
    public Registro getRegistro() {
        return registro;
    }
    public void setRegistro(Registro registro) {
        this.registro = registro;
    }


    
}
