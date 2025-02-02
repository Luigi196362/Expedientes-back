package com.uv.api_expedientes.Permisos.Roles;

import com.uv.api_expedientes.Permisos.Permiso;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "rol", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Permiso> permisos; // Relación con Permisos

    public Rol(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
