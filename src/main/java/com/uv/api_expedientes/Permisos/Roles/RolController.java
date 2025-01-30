package com.uv.api_expedientes.Permisos.Roles;

import java.util.ArrayList;
import java.util.List;
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

import com.uv.api_expedientes.Permisos.Roles.dto.RolResponseDTO;

@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    RolService rolService;

    @GetMapping()
    public List<RolResponseDTO> obtenerRoles() {
        return rolService.obtenerRoles();
    }

    @PostMapping()
    public Rol guardarRol(@RequestBody Rol rol) {
        return this.rolService.guardarRol(rol);
    }

    @GetMapping(path = "/{id}")
    public Optional<Rol> obtenerPorId(@PathVariable("id") Long id) {
        return this.rolService.obtenerPorId(id);
    }

    @PutMapping(path = "/{id}")
    public Rol actualizarRol(@PathVariable("id") long id, @RequestBody Rol rol) {
        return this.rolService.actualizarRol(id, rol);
    }

}
