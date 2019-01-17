package org.sbelei.booksvis.nlp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.*;

import org.junit.jupiter.api.Test;

public class ResponseParserTest {

    @Test
    void testParseResponse() throws Exception {
        String conluInput =
                "# newdoc\r\n" +
                "# newpar\r\n" +
                "# sent_id = 1\r\n" +
                "# text = У Сергійка було дев'ять яблук, а в Іринки 4.\r\n" +
                "1	У	у	ADP	Spsg	Case=Gen	2	case	_	_\r\n" +
                "2	Сергійка	Сергійко	NOUN	Ncmsgy	Animacy=Anim|Case=Gen|Gender=Masc|Number=Sing	3	obl	_	_\r\n" +
                "3	було	бути	VERB	Vapis-sn	Aspect=Imp|Gender=Neut|Mood=Ind|Number=Sing|Tense=Past|VerbForm=Fin	0	root	_	_\r\n" +
                "4	дев'ять	дев’ять	NUM	Mlc-n	Case=Nom|NumType=Card	5	nummod:gov	_	_\r\n" +
                "5	яблук	яблуко	NOUN	Ncnpgn	Animacy=Inan|Case=Gen|Gender=Neut|Number=Plur	3	obj	_	SpaceAfter=No\r\n" +
                "6	,	,	PUNCT	U	_	9	punct	_	_\r\n" +
                "7	а	а	CCONJ	Ccs	_	9	cc	_	_\r\n" +
                "8	в	в	ADP	Spsg	Case=Gen	9	case	_	_\r\n" +
                "9	Іринки	Іринка	NOUN	Ncfsgy	Animacy=Anim|Case=Gen|Gender=Fem|Number=Sing	3	conj	_	_\r\n" +
                "10	4	4	NUM	Mlc-n	Case=Nom|NumType=Card|Uninflect=Yes	9	flat:title	_	SpaceAfter=No\r\n" +
                "11	.	.	PUNCT	U	_	3	punct	_	SpacesAfter=\n";


        Map<String, Word> result = Pattern.compile("\r?\n")
            .splitAsStream(conluInput)
            .filter(p -> !p.startsWith("#")) //ignore comments
            .map(line ->
                Pattern.compile("\t").splitAsStream(line)
                .collect(toCollection(LinkedList<String>::new)))
            .filter(list -> list.get(3).equals("NUM") || list.get(3).equals("NOUN"))
            .collect(toMap(t -> t.get(2), t -> new Word(t),(v1, v2)-> v2, LinkedHashMap<String, Word>::new));

        result.values().forEach(System.out::println);
    }




}
