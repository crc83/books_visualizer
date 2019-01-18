package org.sbelei.booksvis.nlp;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toMap;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Pattern;

public class ResponseParser {

    public Map<String, Word> parseModel(String conluuInput) {
        Map<String, Word> result = Pattern.compile("\r?\n")
                .splitAsStream(conluuInput)
                .filter(p -> !p.startsWith("#")) //ignore comments
                .map(line ->
                    Pattern.compile("\t").splitAsStream(line)
                    .collect(toCollection(LinkedList<String>::new)))
                .filter(list -> list.get(3).equals("NUM") || list.get(3).equals("NOUN"))
                .collect(toMap(t -> t.get(2), t -> new Word(t),(v1, v2)-> v2, LinkedHashMap<String, Word>::new));

        return result;
    }
}
