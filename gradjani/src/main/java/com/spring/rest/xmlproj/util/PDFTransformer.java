package com.spring.rest.xmlproj.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PDFTransformer {
    @Value("${configPath}")
    private String configPath;

    public static final String INPUT_FILE = "C:/Users/Dusan/Desktop/XMLiVebServisi/data/transform_result/html/potvrda.html";

    public void generatePDFfromHTML(String htmlLocation) throws IOException, DocumentException {
        File htmlFile = new File(htmlLocation);
        String htmlFileName = htmlFile.getName().split("\\.")[0];

        String outputLocation = "C:/Users/Dusan/Desktop/XMLiVebServisi/data/transform_result/pdf/"+htmlFileName+".pdf";

        File pdfFile = new File(outputLocation);

        
        System.out.println("Created pdf file:"+pdfFile.createNewFile());

        Document document = new Document();

        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(outputLocation));

        document.open();

        XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, new FileInputStream(htmlLocation));

        document.close();
    }


    public static void main(String[] args) throws IOException, DocumentException {
        PDFTransformer pdfTransformer = new PDFTransformer();

        pdfTransformer.generatePDFfromHTML(INPUT_FILE);
    }
}
