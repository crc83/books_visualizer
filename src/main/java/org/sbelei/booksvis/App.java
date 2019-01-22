package org.sbelei.booksvis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.sbelei.booksvis.nlp.ResponseParser;
import org.sbelei.booksvis.nlp.UdpipeFacade;
import org.sbelei.booksvis.nlp.Word;
import org.sbelei.booksvis.pdf.PdfboxFacade;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main(String[] args) {
/*
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

        save("./out/processed.txt", processed);
        System.out.print(processed);
*/
        String processed = readFile("./out/processed.txt", StandardCharsets.UTF_8);
        ResponseParser parser = new ResponseParser();
        Map<String, Word> model = parser.parseModel(processed);

        String allWords ="";
        for (String word :  model.keySet()) {
            allWords += word +"\r\n";
        }
        save("./out/words.txt",allWords);
      }

    private static String readFile(String path, Charset charset) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @SuppressWarnings("resource")
    private static void save(String fileName, String processed) {
        try {
            new PrintStream(fileName).println(processed);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
