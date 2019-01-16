package org.sbelei.booksvis;

import java.util.logging.Logger;

import cz.cuni.mff.ufal.udpipe.Model;
import cz.cuni.mff.ufal.udpipe.Pipeline;
import cz.cuni.mff.ufal.udpipe.ProcessingError;
import cz.cuni.mff.ufal.udpipe.udpipe_java;

public class UdpipeFacade {

    private Logger LOG = Logger.getLogger(UdpipeFacade.class.getCanonicalName());

    private final String libraryPath = "c:\\projects\\books_visualizer\\dll\\udpipe_java.dll";
    private final String modelPath = "c:\\projects\\books_visualizer\\dll\\ukrainian-iu-ud-2.3-181115.udpipe";

    private Model model;
    private Pipeline pipeline;

    private ProcessingError error;

    public UdpipeFacade() {
        udpipe_java.setLibraryPath(libraryPath);
        if (!loadModel()) {
            throw new InstantiationError("Cannot load model from file '" + modelPath + "'");
        }
        pipeline =  new Pipeline(model, "tokenize",//input_format(tokenize|conllu|horizontal|vertical)
                Pipeline.getDEFAULT(), Pipeline.getDEFAULT(),
                "conllu" //conllu
                );
        error = new ProcessingError();

    }

    private boolean loadModel() {
        LOG.info("Loading model: ");
        model = Model.load(modelPath);
        if (model == null) {
            return false;
        }
        return true;
    }

    //TODO SB:  Revise this
    public ProcessingError getError() {
        return error;
    }

    public String process(String text) {
        String result =  pipeline.process(text, error);
        if (error.occurred()) {
            throw new IllegalArgumentException("Cannot read input CoNLL-U: " + error.getMessage());
        }
        return result;
    }
}
