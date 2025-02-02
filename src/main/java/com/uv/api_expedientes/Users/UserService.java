
package com.uv.api_expedientes.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uv.api_expedientes.Auth.RegisterRequest;
import com.uv.api_expedientes.Permisos.Roles.Rol;
import com.uv.api_expedientes.Permisos.Roles.RolRepository;
import com.uv.api_expedientes.Users.dto.UserResponseDto;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ArrayList<UserResponseDto> getUsers() {
        ArrayList<User> users = (ArrayList<User>) userRepository.findByActivoTrue();
        ArrayList<UserResponseDto> usersResponse = new ArrayList<>();

        for (User user : users) {

            String rolNombre = user.getRol().getNombre();

            UserResponseDto userResponseDto = new UserResponseDto(
                    user.getUsername(),
                    user.getTelefono(),
                    user.getFacultad(),
                    user.getFecha_creacion(),
                    rolNombre);

            usersResponse.add(userResponseDto);
        }

        return usersResponse;
    }

    public Optional<User> obtenerPorId(Long id) {
        return userRepository.findById(id);
    }

    // public Usuario actualizarUsuario(long id, RegisterRequest usuarioDTO) {
    // Usuario actualizarUsuario = usuarioRepository.findById(id).get();
    // actualizarUsuario.setNombre(usuarioDTO.getNombre());
    // actualizarUsuario.setTelefono(usuarioDTO.getTelefono());
    // actualizarUsuario.setFacultad(usuarioDTO.getFacultad());
    // actualizarUsuario.setFecha_creacion(usuarioDTO.getFecha_creacion());
    // actualizarUsuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));

    // Rol rol = rolRepository.findById(usuarioDTO.getRol())
    // .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    // actualizarUsuario.setRol(rol);

    // return usuarioRepository.save(actualizarUsuario);
    // }

    public boolean deactivateUser(Long id) {
        try {
            User user = userRepository.findById(id).get();
            user.setActivo(false);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
