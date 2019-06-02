package org.sbelei.booksvis.tranlate;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MetaTranslator {

    public static final String WORD_DELIMITER = ", ";
    private Logger LOG = Logger.getLogger(MetaTranslator.class.getCanonicalName());

    private static final String url = "https://www.m-translate.it/translate";
    private static final String TRANSLATE_PATTERN = "\"translate\":\"([a-zA-Z,! ']+)\"";
    private int batchSize = 30;

    public MetaTranslator() {
    }

    public MetaTranslator(int batchSize) {
        this.batchSize = batchSize;
    }

    public List<String> translate(List<String> words) {
        List<String> results = new ArrayList<>();

        try {
            for (String word : words) {
                Document result = createConnection(word).post();
                String translatedResult = parseResponse(result);
                results.add(translatedResult);
            }
        } catch (IOException e) {
            LOG.log(Level.WARNING, "can't retrieve data from google", e.getMessage());
        }
        return results;
    }


    public List<String> translateBatch(List<String> words) {
        List<String> batchedWords = batchWordsForTranslation(words);
        List<String> translateBatches = translate(batchedWords);

        return translateBatches.stream()
                .flatMap(b -> Stream.of(b.split(WORD_DELIMITER)))
                .map(el->el.replaceAll(",",""))
                .collect(Collectors.toList());
    }

    private List<String> batchWordsForTranslation(List<String> words) {
        List<String> batchedWords = new ArrayList<>();
        int currentBatchSize = 0;
        StringBuilder batch = new StringBuilder();

        for (String word : words) {
            batch.append(word);
            batch.append(WORD_DELIMITER);
            currentBatchSize++;
            if (currentBatchSize >= batchSize) {
                currentBatchSize = 0;
                batchedWords.add(batch.toString());
                batch.setLength(0);
            }
        }
        if(batch.length() != 0) {
            batchedWords.add(batch.toString());
        }
        return batchedWords;
    }

    private String parseResponse(Document result) {
        Element body = result.select("body").first();
        String text = body.text();
        return getTranslatedResponse(text);
    }

    private String getTranslatedResponse(String text) {
        String translatedResult = "";
        Pattern pattern = Pattern.compile(TRANSLATE_PATTERN);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            translatedResult = matcher.group(1);
        }
        return translatedResult;
    }

    private Connection createConnection(String word) {
        return Jsoup.connect(url)
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36 OPR/60.0.3255.109")
                .data("translate_to", "en")
                .data("translate_from", "uk")
                .data("text", word)
                .header("Accept", "application/json, text/javascript, */*; q=0.01")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("DNT", "1")
                .header("Origin", "https://www.m-translate.com.ua")
                .header("Referer", "https://www.m-translate.com.ua/perekladach/english/uk-en")
                .ignoreContentType(true);
    }
}

