package com.uv.api_expedientes.Auth;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uv.api_expedientes.Permisos.Roles.Rol;
import com.uv.api_expedientes.Permisos.Roles.RolRepository;
import com.uv.api_expedientes.Users.User;
import com.uv.api_expedientes.Users.UserRepository;
import com.uv.api_expedientes.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final UserRepository userRepository;
        private final JwtService jwtService;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;
        private final RolRepository rolRepository;

        public AuthResponse login(LoginRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
                UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
                String token = jwtService.getToken(user);

                return AuthResponse.builder()
                                .token(token)
                                .build();

        }

        public void register(RegisterRequest request) {

                Rol rol = rolRepository.findById(request.getRol())
                                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

                User usuario = User.builder()
                                .username(request.getUsername())
                                .telefono(request.getTelefono())
                                .facultad(request.getFacultad())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .fecha_creacion(new Date())
                                .activo(true)
                                .rol(rol)
                                .build();

                userRepository.save(usuario);
        }

}
