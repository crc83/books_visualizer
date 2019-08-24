package org.sbelei.booksvis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void filterPdfNoPageNumbers() {
        String givenInputText = "5 + 2 + 2   10 – 2 – 2    7 – 2 – 1 \r\n" +
                "Числа можна додавати і віднімати частинами.\r\n" +
                "?\r\n" +
                "5\r\n" + //this is page number. it should be removed
                "14. Поясни обчислення кожного виразу.";
        String actual = (new App()).filterPdfNoice(givenInputText);
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(
                        "5 + 2 + 2   10 – 2 – 2    7 – 2 – 1 Числа можна додавати і віднімати частинами.\r\n" +
                        "?14. Поясни обчислення кожного виразу.", actual)
        );
    }

    @Test
    void filterPdfNoHyphentation() {
        String givenInputText = "20°. У парку посадили 6 кленів, каштанів — на 4 біль\u00AD\r\n" +
                "ше, а акацій — на 2 менше, ніж кленів. Скільки каштанів \r\n" +
                "посадили в парку? Скільки акацій?\r\n" +
                "21°. Допоможи зайчику та білочці обчислити вирази.";
        String actual = (new App()).filterPdfNoice(givenInputText);
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(
                        "20°. У парку посадили 6 кленів, каштанів — на 4 більше, а акацій — на 2 менше, ніж кленів. Скільки каштанів посадили в парку? Скільки акацій?\r\n" +
                                "21°. Допоможи зайчику та білочці обчислити вирази.", actual)
        );
    }

    @Test
    void filterPdfNoHyphenV2() { //another symbol
        String givenInputText = "З кожного виразу на множення двох різних множ-\r\n" +
                "ників можна скласти два вирази на ділення.";
        String actual = (new App()).filterPdfNoice(givenInputText);
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(
                        "З кожного виразу на множення двох різних множників можна скласти два вирази на ділення.", actual)
        );
    }

    @Test
    void filterPdfConcatSentenceInOneLine() {
        String givenInputText = "472. На трьох однакових ділянках посіяли просо, овес \r\n" +
                "і кукурудзу. Восени зібрали 10 кг проса, 23 кг вівса, \r\n" +
                "а  кукурудзи — на 9 кг більше, ніж проса й вівса разом. \r\n" +
                "Скільки кілограмів кукурудзи зібрали?\r\n" +
                " Овес Просо";
        String actual = (new App()).filterPdfNoice(givenInputText);
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(
                        "472. На трьох однакових ділянках посіяли просо, овес і кукурудзу. Восени зібрали 10 кг проса, 23 кг вівса, а  кукурудзи — на 9 кг більше, ніж проса й вівса разом. Скільки кілограмів кукурудзи зібрали?\r\n" +
                                " Овес Просо", actual)
        );
    }
}