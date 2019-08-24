package org.sbelei.booksvis.tranlate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MetaTranslatorTest {

    @Test
    void translate() {
        MetaTranslator translator = new MetaTranslator();
        List<String> translatedWords = translator.translate(Arrays.asList("Собака", "дім"));

        List<String> expectedWords = Arrays.asList("Dog", "home");

        Assertions.assertIterableEquals(expectedWords, translatedWords);
    }

    @Test
    void translateBatchDefault() {
        MetaTranslator translator = new MetaTranslator();
        List<String> translatedWords = translator.translateBatch(Arrays.asList("Собака", "дім"));

        List<String> expectedWords = Arrays.asList("Dog", "house");

        Assertions.assertIterableEquals(expectedWords, translatedWords);
    }

    @Test
    void translateBatch5() {
        MetaTranslator translator = new MetaTranslator(5);
        List<String> translatedWords = translator.translateBatch(Arrays.asList("Собака", "дім", "корова", "квітка", "мушля", "сонце", "дзеркало"));

        assertEquals(7, translatedWords.size());
    }
}