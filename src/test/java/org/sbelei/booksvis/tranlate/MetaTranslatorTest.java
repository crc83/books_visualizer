package org.sbelei.booksvis.tranlate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class MetaTranslatorTest {

    @Test
    void translate() {
        MetaTranslator translator = new MetaTranslator();
        List<String> translatedWords= translator.translate(Arrays.asList("Собака", "дім"));

        List<String> expectedWords = Arrays.asList("Dog","home");

        Assertions.assertIterableEquals(expectedWords,translatedWords);
    }
}