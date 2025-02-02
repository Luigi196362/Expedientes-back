package com.uv.api_expedientes.Users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.uv.api_expedientes.Permisos.Permiso;
import com.uv.api_expedientes.Permisos.Roles.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    @Column(nullable = false)
    private String username;
    private String password;
    private String telefono;
    private String facultad;
    private boolean activo;
    private Date fecha_creacion;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Agregar el rol como autoridad principal
        authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.getNombre().toUpperCase()));

        // Agregar los permisos asociados al rol
        if (rol.getPermisos() != null) {
            for (Permiso permiso : rol.getPermisos()) {
                String authority = permiso.getRecurso().getNombre().toUpperCase() + "_" +
                        permiso.getAccion().getNombre().toUpperCase();
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }

        return authorities;
    }

    @Override
    public String getUsername() {

        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
