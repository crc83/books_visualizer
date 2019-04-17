package org.sbelei.booksvis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void filterPdfNoiceNoPageNumbers() {
        String givenInputText = "5 + 2 + 2   10 – 2 – 2    7 – 2 – 1 \r\n" +
                "Числа можна додавати і віднімати частинами.\r\n" +
                "?\r\n" +
                "5\r\n" +
                "14. Поясни обчислення кожного виразу.";
        String actual = (new App()).filterPdfNoice(givenInputText);
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(
                        "5 + 2 + 2   10 – 2 – 2    7 – 2 – 1 Числа можна додавати і віднімати частинами.\r\n" +
                        "?14. Поясни обчислення кожного виразу.", actual)
        );
    }
}