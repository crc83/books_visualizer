package org.sbelei.booksvis.pdf;

import java.io.File;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * This class parses PDF
 * @author sbelei
 *
 */
public class PdfboxFacade {

    public String parsePdf(String fileName) {
        String parsedText = "";

        PDFParser parser = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        PDFTextStripper pdfStripper;


        File file = new File(fileName);
        try {
            parser = new PDFParser(new RandomAccessBufferedFileInputStream(file));
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc);
            System.out.println(parsedText);

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (cosDoc != null)
                    cosDoc.close();
                if (pdDoc != null)
                    pdDoc.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
        return parsedText;
    }
}
