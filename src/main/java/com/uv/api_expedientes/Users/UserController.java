
package com.uv.api_expedientes.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uv.api_expedientes.Auth.AuthService;
import com.uv.api_expedientes.Auth.RegisterRequest;
import com.uv.api_expedientes.Users.dto.UserEditDto;
import com.uv.api_expedientes.Users.dto.UserResponseDto;

@RestController

@RequestMapping("/api/usuarios")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @GetMapping("/ver")
    public ArrayList<UserResponseDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/ver/{id}")
    public Optional<UserResponseDto> obtenerPorId(@PathVariable("id") Long id) {
        return this.userService.obtenerPorId(id);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Map<String, String>> actualizarUsuario(@PathVariable("id") long id,
            @RequestBody UserEditDto userEditDto) {
        this.userService.actualizarUsuario(id, userEditDto);

        // Devolver JSON en lugar de String
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Actualización exitosa");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request) {
        authService.register(request);

        // Devolver JSON en lugar de String
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Registro exitoso");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, String>> deactivateUser(@PathVariable("id") long id) {
        this.userService.deactivateUser(id);
        // Devolver JSON en lugar de String
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Registro exitoso");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/exportar")
    public String exportar() {
        return userService.exportar();
    }
}
