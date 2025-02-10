package com.uv.api_expedientes.Permisos.Roles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uv.api_expedientes.Permisos.Roles.dto.RolNamesResponseDTO;
import com.uv.api_expedientes.Permisos.Roles.dto.RolRequestDTO;
import com.uv.api_expedientes.Permisos.Roles.dto.RolResponseDTO;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    RolService rolService;

    @GetMapping("/ver")
    public List<RolResponseDTO> obtenerRoles() {
        return rolService.obtenerRoles();
    }

    @GetMapping("/ver/nombres")
    public List<RolNamesResponseDTO> obtenerNombresRoles() {
        return rolService.obtenerNombresRoles();
    }

    @PostMapping("/crear")
    public ResponseEntity<Map<String, String>> guardarRol(@RequestBody RolRequestDTO rolRequestDTO) {
        this.rolService.guardarRol(rolRequestDTO);

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Rol creado exitosamente");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/ver/{id}")
    public Optional<RolResponseDTO> obtenerPorId(@PathVariable("id") Long id) {
        return this.rolService.obtenerPorId(id);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Map<String, String>> actualizarRol(@PathVariable("id") long id,
            @RequestBody RolRequestDTO rolRequestDTO) {
        this.rolService.actualizarRol(id, rolRequestDTO);

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Rol editado exitosamente");

        return ResponseEntity.ok(response);
    }

}
