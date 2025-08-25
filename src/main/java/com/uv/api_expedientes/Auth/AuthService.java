package com.uv.api_expedientes.Auth;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uv.api_expedientes.AccessControl.Roles.Rol;
import com.uv.api_expedientes.AccessControl.Roles.RolRepository;
import com.uv.api_expedientes.Auth.dtos.LoginDto;
import com.uv.api_expedientes.Auth.dtos.RegisterUserDto;
import com.uv.api_expedientes.Users.User;
import com.uv.api_expedientes.Users.UserRepository;
import com.uv.api_expedientes.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;
        private final UserRepository userRepository;
        private final RolRepository rolRepository;
        private final PasswordEncoder passwordEncoder;

        public String login(LoginDto loginDto) {
                System.out.println("Login attempt for matricula: " + loginDto.getMatricula());
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(loginDto.getMatricula(),
                                                loginDto.getPassword()));
                System.out.println("Authentication successful for matricula: " + loginDto.getMatricula());
                UserDetails user = userRepository.findByMatricula(loginDto.getMatricula()).orElseThrow();
                System.out.println(user.getUsername());
                String token = jwtService.getToken(user);

                return token;
        }

        public String register(RegisterUserDto registerUserDto) {

                Rol rol = rolRepository.findById(registerUserDto.getRolId())
                                .orElseThrow(() -> new RuntimeException(
                                                "Rol no encontrado con el ID: " + registerUserDto.getRolId()));

                String matricula = MatriculaGenerator.generarMatricula(registerUserDto.getUsername());

                User user = User.builder()
                                .matricula(matricula)
                                .username(registerUserDto.getUsername())
                                .curp(registerUserDto.getCurp())
                                .rfc(registerUserDto.getRfc())
                                .cedulaProfesional(registerUserDto.getCedulaProfesional())
                                .especialidad(registerUserDto.getEspecialidad())
                                .password(passwordEncoder.encode(registerUserDto.getPassword()))
                                .telefono(registerUserDto.getTelefono())
                                .facultad(registerUserDto.getFacultad())
                                .activo(true)
                                .fecha_creacion(new Date())
                                .rol(rol)
                                .build();

                userRepository.save(user);
                return matricula;
        }

        public class MatriculaGenerator {

                private static final String PREFIJO = "UV";

                public static String generarMatricula(String username) {

                        int year = Calendar.getInstance().get(Calendar.YEAR);

                        String letras = username.length() >= 3
                                        ? username.substring(0, 3).toUpperCase()
                                        : String.format("%-3s", username).replace(' ', 'X').toUpperCase();

                        String hash = UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase();

                        return String.format("%s%d%s%s", PREFIJO, year, letras, hash);
                }
        }
}
