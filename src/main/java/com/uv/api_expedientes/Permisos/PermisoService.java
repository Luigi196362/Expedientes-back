// package com.uv.api_expedientes.Permisos;

// import java.util.Optional;

// import org.springframework.stereotype.Service;

// import com.uv.api_expedientes.Permisos.Acciones.Accion;
// import com.uv.api_expedientes.Permisos.Acciones.AccionRepository;
// import com.uv.api_expedientes.Permisos.PermisoDto.ActualizarPermisosDTO;
// import com.uv.api_expedientes.Permisos.Recursos.Recurso;
// import com.uv.api_expedientes.Permisos.Recursos.RecursoRepository;
// import com.uv.api_expedientes.Permisos.Roles.Rol;
// import com.uv.api_expedientes.Permisos.Roles.RolRepository;

// import jakarta.transaction.Transactional;
// import lombok.AllArgsConstructor;

// @Service
// @AllArgsConstructor
// public class PermisoService {

// private final PermisoRepository permisoRepository;
// private final RolRepository rolRepository;
// private final RecursoRepository recursoRepository;
// private final AccionRepository accionRepository;

// @Transactional
// public boolean editarPermisos(ActualizarPermisosDTO request) {
// Optional<Rol> rolOpt = rolRepository.findById(request.getRolId());

// if (rolOpt.isEmpty()) {
// throw new IllegalArgumentException("Rol no encontrado con ID: " +
// request.getRolId());
// }

// Rol rol = rolOpt.get();
// permisoRepository.deleteByRolId(request.getRolId()); // Elimina permisos
// previos

// for (ActualizarPermisosDTO.PermisoDTO permisoDTO : request.getPermisos()) {
// Recurso recurso =
// recursoRepository.findById(permisoDTO.getRecursoId()).orElse(null);
// if (recurso == null) {
// throw new IllegalArgumentException("Recurso no encontrado con ID: " +
// permisoDTO.getRecursoId());
// }

// for (Long accionId : permisoDTO.getAccionesIds()) {
// Accion accion = accionRepository.findById(accionId).orElse(null);
// if (accion == null) {
// throw new IllegalArgumentException("Acción no encontrada con ID: " +
// accionId);
// }

// Permiso nuevoPermiso = new Permiso(rol, recurso, accion);
// permisoRepository.save(nuevoPermiso);
// }
// }
// return true;
// }
// }
