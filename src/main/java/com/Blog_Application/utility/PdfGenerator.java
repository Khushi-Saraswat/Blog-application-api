package com.Blog_Application.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class PdfGenerator {
    public static byte[] generatePdf(String title, String content) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            
            PDFont fontBold;
            PDFont fontRegular;

            try (InputStream fontStream = PdfGenerator.class.getResourceAsStream("/fonts/FreeSerifBold.ttf");
                 InputStream fontStreamRegular = PdfGenerator.class.getResourceAsStream("/fonts/FreeSerif.ttf")) {

                if (fontStream == null || fontStreamRegular == null) {
                    throw new IOException("Font files not found!");
                }

                fontBold = PDType0Font.load(document, fontStream);
                fontRegular = PDType0Font.load(document, fontStreamRegular);
            }

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // ✅ Set bold font for the title
                contentStream.setFont(fontBold, 16);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Blog Title: " + title);
                contentStream.endText();

                // ✅ Set regular font for the content
                contentStream.setFont(fontRegular, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 680);
                contentStream.showText("Content: " + content);
                contentStream.endText();
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            return outputStream.toByteArray();
        }
    }
}
