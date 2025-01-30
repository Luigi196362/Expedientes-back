package com.uv.api_expedientes.Permisos.Roles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uv.api_expedientes.Permisos.Permiso;
import com.uv.api_expedientes.Permisos.PermisoRepository;
import com.uv.api_expedientes.Permisos.Roles.dto.RolResponseDTO;

@Service
public class RolService {

    @Autowired
    RolRepository rolRepository;
    @Autowired
    PermisoRepository permisoRepository;

    public List<RolResponseDTO> obtenerRoles() { // Devolvemos List<RolResponseDTO> en lugar de ArrayList<Rol>
        List<Rol> roles = (ArrayList<Rol>) rolRepository.findAll(); // findAll() ya devuelve List<Rol>
        List<RolResponseDTO> rolesDTO = new ArrayList<>();

        for (Rol rol : roles) {
            Map<String, List<String>> recursos = new HashMap<>();

            // Obtener permisos del rol correctamente
            List<Permiso> permisos = permisoRepository.findByRolId(rol.getId());

            for (Permiso permiso : permisos) {
                String nombreRecurso = permiso.getRecurso().getNombre();
                String nombreAccion = permiso.getAccion().getNombre();

                // Si el recurso no existe en el mapa, lo agregamos
                recursos.computeIfAbsent(nombreRecurso, k -> new ArrayList<>()).add(nombreAccion);
            }

            RolResponseDTO rolDTO = new RolResponseDTO(rol.getNombre(), rol.getDescripcion(), recursos);
            rolesDTO.add(rolDTO);
        }

        return rolesDTO;
    }

    public Rol guardarRol(Rol rol) {
        Rol nuevoRol = new Rol();
        nuevoRol.setNombre(rol.getNombre());
        nuevoRol.setDescripcion(rol.getDescripcion());
        return rolRepository.save(nuevoRol);
    }

    public Rol actualizarRol(long id, Rol rol) {
        Rol actualizarRol = rolRepository.findById(id).get();
        actualizarRol.setNombre(rol.getNombre());
        actualizarRol.setDescripcion(rol.getDescripcion());
        return rolRepository.save(actualizarRol);
    }

    public Optional<Rol> obtenerPorId(Long id) {
        return rolRepository.findById(id);
    }

}
