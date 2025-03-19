package com.uv.api_expedientes.Registro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uv.api_expedientes.Registro.dto.RegistroDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/registros")
public class RegistroController {
    @Autowired
    RegistroService registroService;

    @GetMapping("/ver")
    public ArrayList<RegistroDTO> obtenerRegistros() {
        return registroService.obtenerRegistros();
    }

    // @GetMapping(path = "/{id}")
    // public Optional<Registro> obtenerPorId(@PathVariable("id") Long id) {
    // return this.registroService.obtenerPorId(id);
    // }

    @GetMapping("/exportar")
    public ResponseEntity<byte[]> generatePdf() throws IOException {
        return registroService.generatePdf();
    }
}
