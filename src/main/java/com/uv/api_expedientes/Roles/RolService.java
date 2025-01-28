package com.uv.api_expedientes.Roles;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public ArrayList<Rol> obtenerRoles() {
        return (ArrayList<Rol>) rolRepository.findByActivoTrue();
    }

    public Rol guardarRol(Rol rol) {
        Rol nuevoRol = new Rol();
        nuevoRol.setNombre(rol.getNombre());
        nuevoRol.setDescripcion(rol.getDescripcion());
        nuevoRol.setAdmin(rol.isAdmin());
        nuevoRol.setActivo(true);
        return rolRepository.save(nuevoRol);
    }

    public Rol actualizarRol(long id, Rol rol) {
        Rol actualizarRol = rolRepository.findById(id).get();
        actualizarRol.setNombre(rol.getNombre());
        actualizarRol.setDescripcion(rol.getDescripcion());
        actualizarRol.setAdmin(rol.isAdmin());
        return rolRepository.save(actualizarRol);
    }

    public Optional<Rol> obtenerPorId(Long id) {
        return rolRepository.findById(id);
    }

    public boolean desactivar(Long id) {
        try {
            Rol rol = rolRepository.findById(id).get();
            rol.setActivo(false);
            rolRepository.save(rol);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
