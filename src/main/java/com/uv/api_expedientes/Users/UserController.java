
package com.uv.api_expedientes.Users;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uv.api_expedientes.Auth.RegisterRequest;
import com.uv.api_expedientes.Users.dto.UserResponseDto;

@RestController

@RequestMapping("/usuario")
public class UserController {

    @Autowired
    UserService usuarioService;

    @GetMapping()
    public ArrayList<UserResponseDto> getUsers() {
        return usuarioService.getUsers();
    }

    // @GetMapping(path = "/{id}")
    // public Optional<Usuario> obtenerPorId(@PathVariable("id") Long id) {
    // return this.usuarioService.obtenerPorId(id);
    // }

    // @PutMapping(path = "/{id}")
    // public Usuario actualizarUsuario(@PathVariable("id") long id, @RequestBody
    // RegisterRequest usuarioDTO) {
    // return this.usuarioService.actualizarUsuario(id, usuarioDTO);
    // }

    @DeleteMapping(path = "/{id}")
    public String deactivateUser(@PathVariable("id") long id) {
        boolean ok = this.usuarioService.deactivateUser(id);
        if (ok) {
            return "Se elimino el usuario " + id;
        } else {
            return "No se pudo eliminar el usuario " + id;
        }
    }
}
