package org.sbelei.booksvis;

import java.util.Scanner;

import cz.cuni.mff.ufal.udpipe.Model;
import cz.cuni.mff.ufal.udpipe.Pipeline;
import cz.cuni.mff.ufal.udpipe.ProcessingError;
import cz.cuni.mff.ufal.udpipe.udpipe_java;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main(String[] args) {
        // lets just make life simpler by loading defaults
        udpipe_java.setLibraryPath("c:\\projects\\books_visualizer\\dll\\udpipe_java.dll");
        String[] myArgs = new String[] {
                "UTF-8",
                "tokenize",
                "c:\\Users\\sbelei\\Downloads\\ukrainian-iu-ud-2.3-181115.udpipe"
        };

        if (myArgs.length < 3) {
          System.err.println("Usage: RunUDPipe input_format(tokenize|conllu|horizontal|vertical) output_format(conllu) model_file");
          System.exit(1);
        }

        System.err.print("Loading model: ");
        Model model = Model.load(myArgs[2]);
        if (model == null) {
          System.err.println("Cannot load model from file '" + myArgs[2] + "'");
          System.exit(1);
        }
        System.err.println("done");


        Pipeline pipeline = new Pipeline(model, "tokenize", Pipeline.getDEFAULT(), Pipeline.getDEFAULT(), "conllu");
        ProcessingError error = new ProcessingError();
//        Scanner reader = new Scanner(System.in);

        // Read while input
//        StringBuilder textBuilder = new StringBuilder();
//        while (reader.hasNextLine()) {
//          textBuilder.append(reader.nextLine());
//          textBuilder.append('\n');
//        }

        // Tokenize and tag
        String text = "Привіт. Як справи?";
        String processed = pipeline.process(text, error);

        if (error.occurred()) {
          System.err.println("Cannot read input CoNLL-U: " + error.getMessage());
          System.exit(1);
        }
        System.out.print(processed);
      }
}
