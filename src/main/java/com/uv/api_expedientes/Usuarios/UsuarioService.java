package com.uv.api_expedientes.Usuarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uv.api_expedientes.Permisos.Roles.Rol;
import com.uv.api_expedientes.Permisos.Roles.RolRepository;
import com.uv.api_expedientes.Usuarios.dto.UsuarioRequestDTO;
import com.uv.api_expedientes.Usuarios.dto.UsuarioResponseDto;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ArrayList<UsuarioResponseDto> obtenerUsuarios() {
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) usuarioRepository.findByActivoTrue();
        ArrayList<UsuarioResponseDto> usuariosResponse = new ArrayList<>();

        for (Usuario usuario : usuarios) {

            String rolNombre = usuario.getRol().getNombre();

            UsuarioResponseDto usuarioResponseDto = new UsuarioResponseDto(
                    usuario.getNombre(),
                    usuario.getTelefono(),
                    usuario.getFacultad(),
                    usuario.getFecha_creacion(),
                    rolNombre);

            usuariosResponse.add(usuarioResponseDto);
        }

        return usuariosResponse;
    }

    public Usuario guardarUsuario(UsuarioRequestDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setFacultad(usuarioDTO.getFacultad());
        usuario.setActivo(true);
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuario.setFecha_creacion(new Date());

        Rol rol = rolRepository.findById(usuarioDTO.getRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario actualizarUsuario(long id, UsuarioRequestDTO usuarioDTO) {
        Usuario actualizarUsuario = usuarioRepository.findById(id).get();
        actualizarUsuario.setNombre(usuarioDTO.getNombre());
        actualizarUsuario.setTelefono(usuarioDTO.getTelefono());
        actualizarUsuario.setFacultad(usuarioDTO.getFacultad());
        actualizarUsuario.setFecha_creacion(usuarioDTO.getFecha_creacion());
        actualizarUsuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));

        Rol rol = rolRepository.findById(usuarioDTO.getRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
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
