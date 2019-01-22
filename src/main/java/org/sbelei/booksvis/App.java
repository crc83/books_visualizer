package org.sbelei.booksvis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.sbelei.booksvis.nlp.UdpipeFacade;
import org.sbelei.booksvis.pdf.PdfboxFacade;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main(String[] args) {
        PdfboxFacade pdfBox = new PdfboxFacade();
        String rawOutput = pdfBox.parsePdf( "./dll/Bogdanovych_Mat_2ukr_2017.pdf");
        rawOutput = rawOutput.replaceAll("\r\n\\d+\\s*\r\n", "");
        rawOutput = rawOutput.replaceAll("­\r\n", "");
        rawOutput = rawOutput.replaceAll("-\r\n", "");
        rawOutput = rawOutput.replaceAll(" \r\n", " ");

        try {
            new PrintStream("./out/raw_text.txt").println(rawOutput);
//            rawOutput = rawOutput.replaceAll("\\d+[°*]?.\\s", "<TASK>");
//            new PrintStream("./out/raw_text2.txt").println(rawOutput);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(rawOutput);

        UdpipeFacade udpipe = new UdpipeFacade();

        String processed = udpipe.process(rawOutput);

        try {
            new PrintStream("./out/processed.txt").println(processed);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.print(processed);
      }
}
