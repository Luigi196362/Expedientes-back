package com.uv.api_expedientes.Permisos;

import com.uv.api_expedientes.Permisos.Acciones.Accion;
import com.uv.api_expedientes.Permisos.Acciones.AccionRepository;
import com.uv.api_expedientes.Permisos.Recursos.Recurso;
import com.uv.api_expedientes.Permisos.Recursos.RecursoRepository;
import com.uv.api_expedientes.Permisos.Roles.Rol;
import com.uv.api_expedientes.Permisos.Roles.RolRepository;

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
@Table(name = "permiso")
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "recurso_id", nullable = false)
    private Recurso recurso;

    @ManyToOne
    @JoinColumn(name = "accion_id", nullable = false)
    private Accion accion;

    public Permiso(Rol rol, Recurso recurso, Accion accion) {
        this.rol = rol;
        this.recurso = recurso;
        this.accion = accion;

    }
}
