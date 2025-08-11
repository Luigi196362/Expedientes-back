package com.uv.api_expedientes.AccessControl.Roles;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uv.api_expedientes.AccessControl.Roles.dtos.IdRolDto;

import lombok.RequiredArgsConstructor;

import com.uv.api_expedientes.AccessControl.Roles.dtos.AllRolesDto;
import com.uv.api_expedientes.AccessControl.Roles.dtos.CreateRolDto;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository rolRepository;

    public List<AllRolesDto> getAllRoles() {

        Iterable<Rol> roles = rolRepository.findAll();

        List<AllRolesDto> allRolesDtos = new java.util.ArrayList<>();
        for (Rol rol : roles) {
            AllRolesDto allRolesDto = AllRolesDto.builder()
                    .id(rol.getId())
                    .nombre(rol.getNombre())
                    .descripcion(rol.getDescripcion())
                    .fecha_creacion(rol.getFecha_creacion())
                    .build();
            allRolesDtos.add(allRolesDto);
        }

        return allRolesDtos;
    }

    public IdRolDto getRolById(Integer id) {

        Rol rol = rolRepository.findById(id).orElseThrow(() -> new RuntimeException("Rol not found with id: " + id));

        IdRolDto idRol = IdRolDto.builder()
                .id(rol.getId())
                .nombre(rol.getNombre())
                .descripcion(rol.getDescripcion())
                .fecha_creacion(rol.getFecha_creacion())
                .build();
        // Falta obtener los permisos
        return idRol;
    }

    public Void createRol(CreateRolDto createRolDto) {
        Rol rol = Rol.builder()
                .nombre(createRolDto.getNombre())
                .descripcion(createRolDto.getDescripcion())
                .activo(true)
                .fecha_creacion(new Date())
                .build();
        rolRepository.save(rol);
        // Falta guardar los permisos
        return null;

    }

    public Void deleteRol(Integer id) {
        Rol rol = rolRepository.findById(id).orElseThrow(() -> new RuntimeException("Rol not found with id: " + id));
        rol.setActivo(false);
        rolRepository.save(rol);
        return null;
    }

    public Void updateRol(Integer id, CreateRolDto createRolDto) {
        Rol rol = rolRepository.findById(id).orElseThrow(() -> new RuntimeException("Rol not found with id: " + id));
        rol.setNombre(createRolDto.getNombre());
        rol.setDescripcion(createRolDto.getDescripcion());
        rolRepository.save(rol);
        // Falta actualizar los permisos
        return null;
    }
}
