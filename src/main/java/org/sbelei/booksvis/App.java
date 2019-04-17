package org.sbelei.booksvis;

import static java.util.stream.Collectors.toCollection;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.sbelei.booksvis.download.ImageDownlader;
import org.sbelei.booksvis.nlp.ResponseParser;
import org.sbelei.booksvis.nlp.UdpipeFacade;
import org.sbelei.booksvis.nlp.Word;
import org.sbelei.booksvis.pdf.PdfboxFacade;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        readPdfAndParseItToText();
//        readParsedTextAndFilterNouns();
//        readWordsFromFileGoogleImagesAndDownloadThem();
    }

    private static void readPdfAndParseItToText() {
        PdfboxFacade pdfBox = new PdfboxFacade();
        String rawOutput = pdfBox.parsePdf("./dll/Bogdanovych_Mat_2ukr_2017.pdf");
        try (PrintStream writer = new PrintStream("./out/raw_pdf.txt")) {
            writer.println(rawOutput);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        //rawOutput = filterPdfNoice(rawOutput);

        try {
            new PrintStream("./out/raw_text.txt").println(rawOutput);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(rawOutput);

        UdpipeFacade udpipe = new UdpipeFacade();

        String processed = udpipe.process(rawOutput);

        save("./out/processed.txt", processed);
        System.out.print(processed);
    }

    String filterPdfNoice(String rawOutput) {
        rawOutput = rawOutput.replaceAll("\r\n\\d+\\s*\r\n", "");
        rawOutput = rawOutput.replaceAll("­\r\n", "");
        rawOutput = rawOutput.replaceAll("-\r\n", "");
        rawOutput = rawOutput.replaceAll(" \r\n", " ");
        return rawOutput;
    }

    private static void readParsedTextAndFilterNouns() {
        String processed = readFile("./out/processed.txt", StandardCharsets.UTF_8);
        ResponseParser parser = new ResponseParser();
        Map<String, Word> model = parser.parseModel(processed);

        String allWords = "";
        for (String word : model.keySet()) {
            allWords += word + "\r\n";
        }
        save("./out/words.txt", allWords);
    }

    private static void readWordsFromFileGoogleImagesAndDownloadThem() {
        /*
        String wordsEdited = readFile("./out/words_edited.txt", StandardCharsets.UTF_8);
        LinkedList<LinkedList<String>> wordsEditedCollection = Pattern.compile("\r?\n").splitAsStream(wordsEdited)
                .filter(p -> !p.startsWith("#")) // ignore comments
                .filter(p -> !p.isBlank())
                .map(line -> Pattern.compile("&").splitAsStream(line).collect(toCollection(LinkedList<String>::new)))
                .collect(toCollection(LinkedList<LinkedList<String>>::new));

        ImageDownlader downloader = new ImageDownlader();
        int i = 0;
        for (LinkedList<String> wordsList : wordsEditedCollection) {
            downloader.download(wordsList.getLast());
            if (i++ > 7) {
                break;
                //due to request limit of 100 requests
            }
        }
        */
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
