package org.sbelei.booksvis;

import java.io.File;

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
        /*

            UdpipeFacade udpipe = new UdpipeFacade();

           // String text = "У Сергійка було дев'ять яблук, а в Іринки 4.";
            String processed = udpipe.process(parsedText);

            System.out.print(processed);
        */
        PdfboxFacade pdfBox = new PdfboxFacade();
        String rawOutput = pdfBox.parsePdf( "./dll/Bogdanovych_Mat_2ukr_2017.pdf");
        rawOutput = rawOutput.replaceAll("­\r\n", "");
        rawOutput = rawOutput.replaceAll(" \r\n", " ");
        System.out.println(rawOutput);
      }
}
