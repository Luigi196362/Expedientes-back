package com.uv.api_expedientes.Registro;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uv.api_expedientes.Registro.dto.RegistroDTO;

@Service
public class RegistroService {

    @Autowired
    RegistroRepository registroRepository;

    public ArrayList<RegistroDTO> obtenerRegistros() {

        List<Registro> registrosListFromDB = registroRepository.findAll();
        ArrayList<RegistroDTO> registrosList = new ArrayList<>();

        for (Registro registro : registrosListFromDB) {

            RegistroDTO registros = new RegistroDTO();

            registros.setUsuario(registro.getUsuario().getUsername());
            registros.setPaciente(registro.getPaciente().getNombre());
            registros.setFecha_creacion(registro.getFecha_creacion());
            registros.setTipo_registro(registro.getTipoRegistro());
            // registros.setIdRegistro(1L);

            registrosList.add(registros);
        }

        return registrosList;

    }

    public ResponseEntity<byte[]> generatePdf() throws IOException {
        // Obtener la lista de registros
        ArrayList<RegistroDTO> registrosList = obtenerRegistros();

        // Cálculos adicionales
        int cantidadRegistros = registrosList.size();
        Map<String, Long> medicoMasRegistros = registrosList.stream()
                .collect(Collectors.groupingBy(RegistroDTO::getUsuario, Collectors.counting()));
        String medicoConMasRegistros = medicoMasRegistros.entrySet().stream()
                .max(Map.Entry.comparingByValue()).get().getKey();

        // Crear documento PDF
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Ruta de la imagen (usar ruta absoluta por pruebas)
        File logoFile = new File("src/main/resources/assets/img/logo.png");
        if (!logoFile.exists()) {
            throw new IOException("Logo no encontrado: " + logoFile.getAbsolutePath());
        }
        PDImageXObject logo = PDImageXObject.createFromFile(logoFile.getAbsolutePath(), document);

        // Agregar contenido
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.drawImage(logo, 30, 700, 64, 48); // Posición (50, 750) y tamaño (100x100) del logo

        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);

        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(new java.util.Date());
        contentStream.newLineAtOffset(470, 740);
        contentStream.showText("Fecha: " + formattedDate);

        // Título
        contentStream.newLineAtOffset(-350, -20);

        contentStream.showText("Reporte de Registros Medicos - UniversidadVeracruzana");

        contentStream.newLineAtOffset(-70, -70);

        // Información adicional
        contentStream.showText("Cantidad total de registros: " + cantidadRegistros);
        contentStream.newLineAtOffset(0, -30);
        contentStream.showText("Médico con más registros: " + medicoConMasRegistros);
        contentStream.newLineAtOffset(0, -40);

        // Detalles de los registros
        contentStream.showText("Detalles de los registros: ");
        contentStream.newLineAtOffset(0, -25);
        for (RegistroDTO registro : registrosList) {
            contentStream.showText("Paciente: " + registro.getPaciente() + " - Usuario: "
                    + registro.getUsuario() +
                    " - Fecha: " + registro.getFecha_creacion());
            contentStream.newLineAtOffset(0, -20);
        }

        // Cerrar contenido de texto
        contentStream.endText();
        contentStream.close();

        // Guardar el documento PDF
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.save(outputStream);
        document.close();

        // Convertir el documento a byte[]
        byte[] pdfBytes = outputStream.toByteArray();

        // Configurar cabeceras HTTP para descargar el PDF
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Reporte_completo_" + formattedDate + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

}
