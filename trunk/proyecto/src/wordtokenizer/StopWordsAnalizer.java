package wordtokenizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Class StopWordsAnalizer.
 * 
 * @author Sebastián Villanueva
 * 
 */

public class StopWordsAnalizer {
    
    List<String> stopWords;

    /**
     * Instantiates a new stop words analizer.
     */
    public StopWordsAnalizer(String fileName) {
        
    	stopWords = new ArrayList<String>();
		File f = new File(fileName);
		FileReader fr;
		try {
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String eachLine;
			eachLine = br.readLine();
			while (eachLine != null) {
				stopWords.add(eachLine);
				eachLine = br.readLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
      

    /**
     * Adds the word.
     *
     * @param word the word
     */
    public void addWord(String word) {
        stopWords.add(word);
    }

    /**
     * Removes the word.
     *
     * @param word the word
     */
    public void removeWord(String word) {
        stopWords.remove(word);
    }

    /**
     * Clean stop words.
     */
    public void cleanStopWords() {
        stopWords.clear();
    }
    
    /**
     * Gets the stop words.
     *
     * @return the stop words
     */
    public List<String> getStopWords() {
    	return this.stopWords;
    }

    /**
     * Analize a list of words.
     *
     * @param list 
     * @return the list
     */
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

    /**
     * Analize.
     *
     * @param list the list
     * @return the list
     */
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

    /**
     * Checks if is stop word.
     *
     * @param word 
     * @return true, if is stop word
     */
    public boolean isStopWord(String word) {
        return stopWords.contains(word);
    }

}
