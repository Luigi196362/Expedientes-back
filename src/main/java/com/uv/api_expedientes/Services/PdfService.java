package com.uv.api_expedientes.Services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.uv.api_expedientes.Pacientes.dtos.SatisticsPacienteDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PdfService {

    private final TemplateEngine templateEngine;

    public byte[] generarEstadisticasPdf(SatisticsPacienteDto stats, Date startDate, Date endDate) throws IOException {
        Context context = new Context();
        context.setVariable("stats", stats);

        String dateRangeText;
        if (startDate != null && endDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dateRangeText = "Rango: " + sdf.format(startDate) + " - " + sdf.format(endDate);
        } else {
            dateRangeText = "Reporte Histórico Completo";
        }
        context.setVariable("dateRange", dateRangeText);

        SimpleDateFormat sdfGen = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        context.setVariable("generationDate", sdfGen.format(new Date()));

        String html = templateEngine.process("reporte-pacientes/pdf_stats", context);

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(html, getClass().getResource("/templates/reporte-pacientes/").toString());
            builder.toStream(os);
            builder.run();
            return os.toByteArray();
        } catch (Exception e) {
            throw new IOException("Error creating PDF", e);
        }
    }
}
