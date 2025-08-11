package com.uv.api_expedientes.Users;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uv.api_expedientes.Users.dtos.AllUsersDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<AllUsersDto> getUsers() {
        List<User> users = userRepository.findByActivoTrue();

        if (users.isEmpty()) {
            throw new RuntimeException("No hay usuarios activos");
        }

        List<AllUsersDto> allUsersDto = users.stream()
                .map(user -> new AllUsersDto(
                        user.getMatricula(),
                        user.getUsername(),
                        user.getTelefono(),
                        user.getFacultad(),
                        user.getEspecialidad(),
                        user.getRol() != null ? user.getRol().getNombre() : "No hay rol asignado"))
                .toList();

        return allUsersDto;
    }

    // public User getUserById(Integer id) {
    // return userRepository.findById(id)
    // .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " +
    // id));
    // }

    public String deactivateUser(Integer id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            if (!user.isActivo()) {
                return "Usuario ya está desactivado";
            }
            user.setActivo(false);
            userRepository.save(user);
            return "Se desactivo el usuario";
        } catch (Exception e) {
            throw new RuntimeException("No se pudo desactivar el usuario" + e.getMessage());
        }
    }

    public String reactivateUser(Integer id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            if (user.isActivo()) {
                return "Usuario ya está activo";
            }
            user.setActivo(true);
            userRepository.save(user);
            return "Se reactivo el usuario";
        } catch (Exception e) {
            throw new RuntimeException("No se pudo reactivar el usuario" + e.getMessage());
        }
    }

    // public String exportar() {
    // return "Pdf generado correctamente";
    // }
}
