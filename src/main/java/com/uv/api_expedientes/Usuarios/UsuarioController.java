package com.uv.api_expedientes.Usuarios;

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

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ArrayList<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @PostMapping()
    public Usuario guardarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return this.usuarioService.guardarUsuario(usuarioDTO);
    }

    @GetMapping(path = "/{id}")
    public Optional<Usuario> obtenerPorId(@PathVariable("id") Long id) {
        return this.usuarioService.obtenerPorId(id);
    }

    @PutMapping(path = "/{id}")
    public Usuario actualizarUsuario(@PathVariable("id") long id, @RequestBody UsuarioDTO usuarioDTO) {
        return this.usuarioService.actualizarUsuario(id, usuarioDTO);
    }

    @DeleteMapping(path = "/{id}")
    public String eleminarRol(@PathVariable("id") long id) {
        boolean ok = this.usuarioService.desactivar(id);
        if (ok) {
            return "Se elimino el usuario " + id;
        } else {
            return "No se pudo eliminar el usuario " + id;
        }
    }
}
