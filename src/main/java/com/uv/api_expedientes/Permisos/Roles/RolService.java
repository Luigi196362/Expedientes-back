package com.uv.api_expedientes.Permisos.Roles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uv.api_expedientes.Pacientes.Paciente;
import com.uv.api_expedientes.Permisos.Permiso;
import com.uv.api_expedientes.Permisos.PermisoRepository;
import com.uv.api_expedientes.Permisos.Acciones.Accion;
import com.uv.api_expedientes.Permisos.Acciones.AccionRepository;
import com.uv.api_expedientes.Permisos.PermisoDto.ActualizarPermisosDTO;
import com.uv.api_expedientes.Permisos.PermisoDto.ActualizarPermisosDTO.PermisoDTO;
import com.uv.api_expedientes.Permisos.Recursos.Recurso;
import com.uv.api_expedientes.Permisos.Recursos.RecursoRepository;
import com.uv.api_expedientes.Permisos.Roles.dto.RolNamesResponseDTO;
import com.uv.api_expedientes.Permisos.Roles.dto.RolRequestDTO;
import com.uv.api_expedientes.Permisos.Roles.dto.RolResponseDTO;

import jakarta.transaction.Transactional;

@Service
public class RolService {

    @Autowired
    RolRepository rolRepository;
    @Autowired
    PermisoRepository permisoRepository;
    @Autowired
    AccionRepository accionRepository;
    @Autowired
    RecursoRepository recursoRepository;

    public List<RolResponseDTO> obtenerRoles() {
        List<Rol> roles = (List<Rol>) rolRepository.findAll();
        List<RolResponseDTO> rolesDTO = new ArrayList<>();

        for (Rol rol : roles) {
            List<PermisoDTO> permisosDTO = permisoRepository.findByRolId(rol.getId())
                    .stream()
                    .collect(Collectors.groupingBy(
                            p -> p.getRecurso().getId(),
                            Collectors.mapping(p -> p.getAccion().getId(), Collectors.toList())))
                    .entrySet()
                    .stream()
                    .map(entry -> new PermisoDTO(entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList());

            RolResponseDTO rolDTO = new RolResponseDTO(rol.getId(), rol.getNombre(), rol.getDescripcion(), permisosDTO);
            rolesDTO.add(rolDTO);
        }

        return rolesDTO;
    }

    public List<RolNamesResponseDTO> obtenerNombresRoles() {
        List<Rol> roles = (List<Rol>) rolRepository.findAll();

        return roles.stream()
                .map(rol -> new RolNamesResponseDTO(rol.getId(), rol.getNombre()))
                .collect(Collectors.toList());
    }

    public String guardarRol(RolRequestDTO rolRequestDTO) {
        try {
            // Crear un nuevo rol
            Rol nuevoRol = Rol.builder()
                    .nombre(rolRequestDTO.getNombre())
                    .descripcion(rolRequestDTO.getDescripcion())
                    .build();

            // Guardar el rol
            rolRepository.save(nuevoRol);

            // Asignar los permisos al rol recién creado
            if (rolRequestDTO.getPermisos() != null) {
                for (RolRequestDTO.PermisoDTO permisoDTO : rolRequestDTO.getPermisos()) {
                    Recurso recurso = recursoRepository.findById(permisoDTO.getRecursoId()).orElse(null);
                    if (recurso == null) {
                        throw new IllegalArgumentException(
                                "Recurso no encontrado con ID: " + permisoDTO.getRecursoId());
                    }

                    for (Long accionId : permisoDTO.getAccionesIds()) {
                        Accion accion = accionRepository.findById(accionId).orElse(null);
                        if (accion == null) {
                            throw new IllegalArgumentException("Acción no encontrada con ID: " + accionId);
                        }

                        Permiso nuevoPermiso = new Permiso(nuevoRol, recurso, accion);
                        permisoRepository.save(nuevoPermiso);
                    }
                }
            }

            return "Rol y permisos creados correctamente";
        } catch (Exception e) {
            return "Error al crear el rol y permisos: " + e.getMessage();
        }
    }

    @Transactional
    public String actualizarRol(long id, RolRequestDTO rolRequestDTO) {
        try {
            Rol actualizarRol = rolRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

            // Actualizar nombre y descripción del rol
            Optional.ofNullable(rolRequestDTO.getNombre()).ifPresent(actualizarRol::setNombre);
            Optional.ofNullable(rolRequestDTO.getDescripcion()).ifPresent(actualizarRol::setDescripcion);

            // Eliminar permisos previos
            permisoRepository.deleteByRolId(id);

            // Asignar nuevos permisos
            if (rolRequestDTO.getPermisos() != null) {
                for (RolRequestDTO.PermisoDTO permisoDTO : rolRequestDTO.getPermisos()) {
                    Recurso recurso = recursoRepository.findById(permisoDTO.getRecursoId()).orElse(null);
                    if (recurso == null) {
                        throw new IllegalArgumentException(
                                "Recurso no encontrado con ID: " + permisoDTO.getRecursoId());
                    }

                    for (Long accionId : permisoDTO.getAccionesIds()) {
                        Accion accion = accionRepository.findById(accionId).orElse(null);
                        if (accion == null) {
                            throw new IllegalArgumentException("Acción no encontrada con ID: " + accionId);
                        }

                        Permiso nuevoPermiso = new Permiso(actualizarRol, recurso, accion);
                        permisoRepository.save(nuevoPermiso);
                    }
                }
            }

            return "Rol y permisos actualizados correctamente";
        } catch (Exception e) {
            return "Error al actualizar el rol y permisos: " + e.getMessage();
        }
    }

    public Optional<RolResponseDTO> obtenerPorId(Long id) {
        return rolRepository.findById(id)
                .map(rol -> {
                    List<PermisoDTO> permisosDTO = permisoRepository.findByRolId(rol.getId())
                            .stream()
                            .collect(Collectors.groupingBy(
                                    p -> p.getRecurso().getId(),
                                    Collectors.mapping(p -> p.getAccion().getId(), Collectors.toList())))
                            .entrySet()
                            .stream()
                            .map(entry -> new PermisoDTO(entry.getKey(), entry.getValue()))
                            .collect(Collectors.toList());

                    return Optional
                            .of(new RolResponseDTO(rol.getId(), rol.getNombre(), rol.getDescripcion(), permisosDTO));
                }).orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    }

}
