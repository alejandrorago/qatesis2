package input;


import java.io.FileInputStream;

import org.apache.poi.hpsf.PropertySetFactory;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderEvent;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderListener;



public class StandardReaderListener implements POIFSReaderListener {

    private WordReader datos = null;

    public void setDatos(WordReader datos) {
        this.datos = datos;
    }

    public void processPOIFSReaderEvent(POIFSReaderEvent event) {

    	// Clase que almacena las características estándar de un documento.
    	SummaryInformation si = null;
    	try {
    	    si = (SummaryInformation)PropertySetFactory.create(event.getStream());
    	} catch (Exception ex) {
    	    throw new RuntimeException ("Property set stream \"" +
    	        event.getPath() + event.getName() + "\": " + ex);
    	}
    	/* Recogemos los datos que nos interesan y los almacenamos en la clase AnalizadorWord.*/

    	datos.setTitle(si.getTitle());
    	datos.setAutor(si.getAuthor());
    	datos.setComments(si.getComments());
    	datos.setNumChars(si.getCharCount());
    	datos.setNumWords(si.getWordCount());
    	datos.setNumPages(si.getPageCount());

    	        /* Usamos la clase WordExtractor para obtener el texto del documento */
    			WordExtractor we = null;
    	        try {
    	            FileInputStream fis = new FileInputStream(datos.getFileName());
    	            we = new WordExtractor(fis);
    	            fis.close();
    	        } catch (Exception e1) {
    	            System.out.println("Error->"+e1.toString());
    	        }
    	        datos.setText(we.getText());
    	        datos.setParagraphs(we.getParagraphText());
    	        }
            }	