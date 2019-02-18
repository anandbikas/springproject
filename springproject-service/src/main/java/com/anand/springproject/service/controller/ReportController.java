/**
 * @author Bikas Anand
 */
package com.anand.springproject.service.controller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

@RestController
public class ReportController {

    private String[] orderCsvHeaders={"ID", "Name", "Brand", "Price"};

    /**
     *
     * @return
     * @throws IOException
     */
    @GetMapping("/order_report")
    public ResponseEntity<byte[]> downloadOrderReport() throws Exception {
        final String reportFileName = "order_report.csv";
        final String reportFileNameType = "text/csv";

        byte[] csvFileByteArray = getReportCSVAsByteArray();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + reportFileName + "\"")
                .contentType(MediaType.parseMediaType(reportFileNameType))
                .body(csvFileByteArray);
    }

    /**
     *
     * @return
     * @throws IOException
     */
    private byte[] getReportCSVAsByteArray() throws IOException {


        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader(orderCsvHeaders))
        ) {
            csvPrinter.printRecord("1", "Display Monitor", "HP", "10000");
            csvPrinter.printRecord("2", "Keyboard", "Logitech", "900");
            csvPrinter.printRecord(Arrays.asList("3", "Speaker", "Bose", "8000"));

            csvPrinter.flush();
            return out.toByteArray();
        }
    }
}