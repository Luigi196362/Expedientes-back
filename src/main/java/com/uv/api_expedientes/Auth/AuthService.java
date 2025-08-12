package com.uv.api_expedientes.Auth;

import java.util.Date;

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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final AuthenticationManager authenticationManager;
        private final UserRepository userRepository;
        private final RolRepository rolRepository;
        private final PasswordEncoder passwordEncoder;

        public String login(LoginDto loginDto) {

                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                                                loginDto.getPassword()));

                UserDetails user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow();

                String token = "Este es un token simulado"; // jwtService.getToken(user);
                // String token = jwtService.getToken(user);

                return token;
        }

        public Void register(RegisterUserDto registerUserDto) {

                Rol rol = rolRepository.findById(registerUserDto.getRolId())
                                .orElseThrow(() -> new RuntimeException(
                                                "Rol no encontrado con el ID: " + registerUserDto.getRolId()));

                User user = User.builder()
                                .matricula(registerUserDto.getMatricula())
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
                return null;
        }

        // private final JwtService jwtService;

        // public AuthResponse login(LoginRequest request) {
        // authenticationManager.authenticate(
        // new UsernamePasswordAuthenticationToken(request.getUsername(),
        // request.getPassword()));
        // UserDetails user =
        // userRepository.findByUsername(request.getUsername()).orElseThrow();
        // String token = jwtService.getToken(user);

        // return AuthResponse.builder()
        // .token(token)
        // .build();

        // }

}
