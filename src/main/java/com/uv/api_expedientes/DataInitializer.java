package com.uv.api_expedientes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.uv.api_expedientes.Permisos.Permiso;
import com.uv.api_expedientes.Permisos.PermisoRepository;
import com.uv.api_expedientes.Permisos.Acciones.Accion;
import com.uv.api_expedientes.Permisos.Acciones.AccionRepository;
import com.uv.api_expedientes.Permisos.Recursos.Recurso;
import com.uv.api_expedientes.Permisos.Recursos.RecursoRepository;
import com.uv.api_expedientes.Permisos.Roles.Rol;
import com.uv.api_expedientes.Permisos.Roles.RolRepository;
import com.uv.api_expedientes.Users.User;
import com.uv.api_expedientes.Users.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AccionRepository accionRepository;
    private final RolRepository rolRepository;
    private final RecursoRepository recursoRepository;
    private final PermisoRepository permisoRepository;
    private final UserRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor que inyecta el repositorio de Acciones
    public DataInitializer(AccionRepository accionRepository, RolRepository rolRepository,
            RecursoRepository recursoRepository, PermisoRepository permisoRepository,
            UserRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {
        this.accionRepository = accionRepository;
        this.rolRepository = rolRepository;
        this.recursoRepository = recursoRepository;
        this.permisoRepository = permisoRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        // Crear acciones básicas con sus respectivas descripciones
        List<Accion> accionesBasicas = List.of(
                new Accion("Crear", "Permite crear"),
                new Accion("Editar", "Permite editar"),
                new Accion("Eliminar", "Permite eliminar"),
                new Accion("Ver", "Permite ver"));

        for (Accion accion : accionesBasicas) {
            if (accionRepository.findByNombre(accion.getNombre()).isEmpty()) {
                accionRepository.save(accion);
                System.out.println("Acción creada: " + accion.getNombre());
            }
        }

        // Crear Roles básicos con sus respectivas descripciones
        List<Rol> rolesBasicos = List.of(
                new Rol("Admin", "Tiene acceso a todo el sistema"),
                new Rol("Usuario", "Acceso limitado"),
                new Rol("Médico", "Gestiona pacientes y expedientes"));

        for (Rol rol : rolesBasicos) {
            if (rolRepository.findByNombre(rol.getNombre()).isEmpty()) {
                rolRepository.save(rol);
                System.out.println("Rol creado: " + rol.getNombre());
            }
        }

        // Crear Recursos básicos con sus respectivas descripciones
        List<Recurso> recursosBasiscos = List.of(
                new Recurso("Pacientes", "Recurso de paciente"),
                new Recurso("Usuarios", "Recurso de paciente"),
                new Recurso("Registros", "Recurso de paciente"));

        for (Recurso recurso : recursosBasiscos) {
            if (recursoRepository.findByNombre(recurso.getNombre()).isEmpty()) {
                recursoRepository.save(recurso);
                System.out.println("Recurso creado: " + recurso.getNombre());
            }
        }
        /*
         * // Crear usuario Administrador
         * Usuario usuario = new Usuario();
         * 
         * usuario.setNombre("Admin");
         * usuario.setTelefono("(272) 123-4567");
         * usuario.setFacultad("test");
         * usuario.setActivo(true);
         * usuario.setPassword(passwordEncoder.encode("admin"));
         * usuario.setFecha_creacion(new Date());
         * Rol rolUsuario = rolRepository.findByNombre("Admin")
         * .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
         * usuario.setRol(rolUsuario);
         * 
         * if (usuarioRepository.findByNombre(usuario.getNombre()).isEmpty()) {
         * 
         * usuarioRepository.save(usuario);
         * 
         * System.out.println("Usuario " + usuario.getNombre() + " creado ");
         * }
         */
        // Añadir permisos a Administrador

        // Obtener todos los recursos y acciones
        List<Recurso> todosRecursos = (ArrayList<Recurso>) recursoRepository.findAll();
        List<Accion> todasAcciones = (ArrayList<Accion>) accionRepository.findAll();

        // Obtener el rol con ID 1L (Admin)
        Optional<Rol> rolOptional = rolRepository.findById(1L);

        if (rolOptional.isPresent()) {
            Rol rol = rolOptional.get();

            for (Recurso recurso : todosRecursos) {
                for (Accion accion : todasAcciones) {
                    Optional<Permiso> permisoExistente = permisoRepository.findByRolIdAndRecursoIdAndAccionId(
                            rol.getId(), recurso.getId(), accion.getId());

                    if (permisoExistente.isEmpty()) {
                        // Crear un nuevo permiso con los objetos obtenidos
                        Permiso nuevoPermiso = new Permiso();
                        nuevoPermiso.setRol(rol);
                        nuevoPermiso.setRecurso(recurso);
                        nuevoPermiso.setAccion(accion);

                        // Guardar el nuevo permiso
                        permisoRepository.save(nuevoPermiso);

                        System.out.println("Permiso creado para el rol Admin: Recurso " +
                                recurso.getNombre() +
                                " - Acción " + accion.getNombre());
                    }
                }
            }
        } else {
            System.out.println("No se encontró el rol con ID 1.");
        }

    }
}
