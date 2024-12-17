package com.uv.api_expedientes;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Desactivar CORS para todas las rutas
        registry.addMapping("/**")
                .allowedOrigins("*")  // Permite todos los orígenes
                .allowedMethods("*")  // Permite todos los métodos (GET, POST, PUT, etc.)
                .allowedHeaders("*"); // Permite todos los encabezados
    }
}
