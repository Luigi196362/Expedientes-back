// package com.uv.api_expedientes.Permisos;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import com.uv.api_expedientes.Permisos.PermisoDto.ActualizarPermisosDTO;

// import lombok.AllArgsConstructor;

// @RestController
// @AllArgsConstructor
// @RequestMapping("/api/permisos")
// public class PermisoController {

// private final PermisoService permisoService;

// @PutMapping("/editar")
// public ResponseEntity<String> editarPermisos(@RequestBody
// ActualizarPermisosDTO request) {
// boolean editado = permisoService.editarPermisos(request);
// if (editado) {
// return ResponseEntity.ok("Permisos editados correctamente");
// }
// return ResponseEntity.badRequest().body("Error al editar los permisos");
// }
// }
