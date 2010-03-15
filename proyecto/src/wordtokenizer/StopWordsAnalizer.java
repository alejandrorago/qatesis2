package wordtokenizer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class StopWordsAnalizer {
    List<String> stopWords;

    public StopWordsAnalizer() {
        stopWords = new ArrayList<String>();
        stopWords.add("a");
        stopWords.add("an");
        stopWords.add("at");
        stopWords.add("be");
        stopWords.add("for");
        stopWords.add("he");
        stopWords.add("is");
        stopWords.add("she");
        stopWords.add("the");

    }

    public void addWord(String word) {
        stopWords.add(word);
    }

    public void removeWord(String word) {
        stopWords.remove(word);
    }

    public void cleanStopWords() {
        stopWords.clear();
    }

    public List<String> analize(List<String> list) {
        List<String> resultado = new ArrayList<String>();

        for (Iterator<String> i = list.iterator(); i.hasNext();) {
            String palabra = (String) i.next();

            if (!this.isStopWord(palabra)) {
                resultado.add(palabra);
            }
        }

        return resultado;
    }

    public List<String> analize(String[] list) {
        List<String> resultado = new ArrayList<String>();

        for (int i = 0; i < list.length; i++) {
            String palabra = list[i];

            if (!this.isStopWord(palabra)) {
                resultado.add(palabra);
            }
        }

        return resultado;
    }

    public boolean isStopWord(String word) {
        return stopWords.contains(word);
    }
}
