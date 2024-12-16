package com.uv.api_expedientes.Registro;

import java.util.Date;

import com.uv.api_expedientes.Paciente.Paciente;
import com.uv.api_expedientes.Usuarios.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Registro")
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private Date fecha_creacion;

    
    public long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    
}
