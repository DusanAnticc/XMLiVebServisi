package com.spring.rest.xmlproj.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFTransformer {

    public static final String INPUT_FILE = "../data/transform_result/html/potvrda.html";

    public void generatePDFfromHTML(String htmlLocation) throws IOException, DocumentException {
        Document document = new Document();

        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("../data/transform_result/pdf/potvrda.pdf"));

        document.open();

        XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, new FileInputStream(htmlLocation));

        document.close();
    }


    public static void main(String[] args) throws IOException, DocumentException {
        PDFTransformer pdfTransformer = new PDFTransformer();

        pdfTransformer.generatePDFfromHTML(INPUT_FILE);
    }
}
