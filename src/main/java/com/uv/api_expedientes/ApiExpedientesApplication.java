package com.uv.api_expedientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class ApiExpedientesApplication {
	public static void main(String[] args) {
		String dbPath = System.getProperty("user.home") + File.separator + ".expedientes";
		File dbDir = new File(dbPath);
		if (!dbDir.exists()) {
			if (dbDir.mkdirs()) {
				System.out.println("Carpeta creada antes de iniciar Spring: " + dbPath);
			} else {
				System.err.println("No se pudo crear la carpeta: " + dbPath);
			}
		}

		SpringApplication.run(ApiExpedientesApplication.class, args);
	}
}
