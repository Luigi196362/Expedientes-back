package com.uv.api_expedientes.Roles;

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
@RequestMapping("/rol")
public class RolController {

    @Autowired
    RolService rolService;

    @GetMapping()
    public ArrayList<Rol> obtenerRoles() {
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

    @DeleteMapping(path = "/{id}")
    public String eleminarRol(@PathVariable("id") long id) {
        boolean ok = this.rolService.desactivar(id);
        if (ok) {
            return "Se elimino el rol " + id;
        } else {
            return "No se pudo eliminar el rol " + id;
        }
    }
}
