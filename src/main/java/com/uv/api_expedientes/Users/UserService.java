
package com.uv.api_expedientes.Users;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uv.api_expedientes.Permisos.Roles.Rol;
import com.uv.api_expedientes.Permisos.Roles.RolRepository;
import com.uv.api_expedientes.Users.dto.UserEditDto;
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
                    user.getId(),
                    user.getUsername(),
                    user.getTelefono(),
                    user.getFacultad(),
                    user.getFecha_creacion(),
                    rolNombre);

            usersResponse.add(userResponseDto);
        }

        return usersResponse;
    }

    public Optional<UserResponseDto> obtenerPorId(Long id) {
        return userRepository.findById(id).map(user -> {
            if (!user.isActivo()) {
                throw new RuntimeException("Usuario no activo");
            }
            String rolNombre = user.getRol().getNombre();
            return new UserResponseDto(
                    user.getId(),
                    user.getUsername(),
                    user.getTelefono(),
                    user.getFacultad(),
                    user.getFecha_creacion(),
                    rolNombre);
        }).or(() -> {
            throw new RuntimeException("Usuario no encontrado");
        });
    }

    public String actualizarUsuario(long id, UserEditDto userEditDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (!user.isActivo()) {
            throw new RuntimeException("Usuario no activo");
        }

        Optional.ofNullable(userEditDto.getUsername()).ifPresent(user::setUsername);
        Optional.ofNullable(userEditDto.getTelefono()).ifPresent(user::setTelefono);
        Optional.ofNullable(userEditDto.getFacultad()).ifPresent(user::setFacultad);
        Optional.ofNullable(userEditDto.getPassword()).ifPresent(user::setPassword);
        Optional.ofNullable(userEditDto.getPassword())
                .ifPresent(password -> user.setPassword(passwordEncoder.encode(password)));

        if (userEditDto.getRol() != null) {
            Rol rol = rolRepository.findById(userEditDto.getRol())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            user.setRol(rol);
        }

        userRepository.save(user);
        return "Usuario editado correctamente";
    }

    public String deactivateUser(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            if (!user.isActivo()) {
                return "Usuario ya está desactivado";
            }
            user.setActivo(false);
            userRepository.save(user);
            return "Se eliminó el usuario";
        } catch (Exception e) {
            throw new RuntimeException("No se pudo eliminar el usuario");
        }
    }

    public String exportar() {
        return "Pdf generado correctamente";
    }

}
