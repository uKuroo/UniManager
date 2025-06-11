package br.edu.ifba.inf008.uniManager.infra.exports;

import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class BasicITextTest{

    public void export(String eventId) {
        String dest = "files/reports/report_"+eventId+".pdf"; // Output PDF file path

        try {
            // Create PdfWriter
            PdfWriter writer = new PdfWriter(dest);

            // Create PdfDocument
            PdfDocument pdf = new PdfDocument(writer);

            // Create Document
            Document document = new Document(pdf);

            // Add content to the document
            document.add(new Paragraph("Hello, iText!"));

            // Close the document
            document.close();

            System.out.println("PDF created successfully: " + dest);

        } catch (IOException e) {
            System.err.println("Error creating PDF: " + e.getMessage());
        }
    }
}

