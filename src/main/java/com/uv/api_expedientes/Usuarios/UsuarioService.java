package com.uv.api_expedientes.Usuarios;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uv.api_expedientes.Roles.Rol;
import com.uv.api_expedientes.Roles.RolRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ArrayList<Usuario> obtenerUsuarios() {
        return (ArrayList<Usuario>) usuarioRepository.findAll();
    }

    public Usuario guardarUsuario(Usuario usuario) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(usuario.getNombre());
        nuevoUsuario.setTelefono(usuario.getTelefono());
        nuevoUsuario.setFacultad(usuario.getFacultad());
        nuevoUsuario.setActivo(true);
        nuevoUsuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        Rol rol = rolRepository.findById(usuario.getRol().getId()).orElseThrow(()
                -> new RuntimeException("Rol no encontrado"));
        nuevoUsuario.setRol(rol);

        return usuarioRepository.save(nuevoUsuario);
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario actualizarUsuario(long id, Usuario usuario) {
        Usuario actualizarUsuario = usuarioRepository.findById(id).get();
        actualizarUsuario.setNombre(usuario.getNombre());
        actualizarUsuario.setTelefono(usuario.getTelefono());
        actualizarUsuario.setRol(usuario.getRol());
        actualizarUsuario.setFacultad(usuario.getFacultad());

        actualizarUsuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        Rol rol = rolRepository.findById(usuario.getRol().getId()).orElseThrow(()
                -> new RuntimeException("Rol no encontrado"));
        actualizarUsuario.setRol(rol);

        return usuarioRepository.save(actualizarUsuario);
    }

    public boolean desactivar(Long id) {
        try {
            Usuario usuario = usuarioRepository.findById(id).get();
            usuario.setActivo(false);
            usuarioRepository.save(usuario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
