package wordtokenizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class StopWordsAnalizer {
    List<String> stopWords;

    public StopWordsAnalizer() {
        
    	stopWords = new ArrayList<String>();
		File f = new File("resources//stopWordsList.txt");
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
      

    public void addWord(String word) {
        stopWords.add(word);
    }

    public void removeWord(String word) {
        stopWords.remove(word);
    }

    public void cleanStopWords() {
        stopWords.clear();
    }
    
    public List<String> getStopWords() {
    	return this.stopWords;
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
