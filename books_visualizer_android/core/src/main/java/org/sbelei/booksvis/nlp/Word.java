package org.sbelei.booksvis.nlp;

import java.util.List;

public class Word {

    private String word;
    private String type;
    private boolean isPlural = false;

    public Word(List<String> t) {
        word = t.get(2);
        type = t.get(3);
        isPlural = t.get(5).contains("Number=Plur");
    }

    public String getWord() {
        return word;
    }

    public String getType() {
        return type;
    }

    public boolean isPlural() {
        return isPlural;
    }

    @Override
    public String toString() {
        return "Word [word=" + word + ", type=" + type + ", isPlural=" + isPlural + "]";
    }


}
