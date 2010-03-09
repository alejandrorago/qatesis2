package input;

import java.io.FileInputStream;


import org.apache.poi.poifs.eventfilesystem.POIFSReader;

import entities.UseCase;

public class WordReader {

	private int numChars;
	private int numWords;
	private int numPages;
	private String title;
	private String autor;
	private String comments;
	private String text;
	private String fileName;

	public WordReader(String fileName) {
		this.fileName = fileName;
	}

	public void analize() throws Exception {
		POIFSReader r1 = new POIFSReader();

		FileInputStream fis = null;

		// Registramos el listener para despu�s analizar la informaci�n.
		StandardReaderListener stdReader = new StandardReaderListener();
		stdReader.setDatos(this);
		r1.registerListener(stdReader, "\005SummaryInformation");
		try {
			// Forzamos la lectura del documento.
			fis = new FileInputStream(this.fileName);
			r1.read(fis);
		} catch (Exception e) {
			System.out.println("Error->" + e.toString());
		} finally {
			if (fis != null)
				fis.close();
		}

	}

	/**
	 * @return the numChars
	 */
	public int getNumChars() {
		return numChars;
	}

	/**
	 * @param numChars the numChars to set
	 */
	public void setNumChars(int numChars) {
		this.numChars = numChars;
	}

	/**
	 * @return the numWords
	 */
	public int getNumWords() {
		return numWords;
	}

	/**
	 * @param numWords the numWords to set
	 */
	public void setNumWords(int numWords) {
		this.numWords = numWords;
	}

	/**
	 * @return the numPages
	 */
	public int getNumPages() {
		return numPages;
	}

	/**
	 * @param numPages the numPages to set
	 */
	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the autor
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * @param autor the autor to set
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public UseCase getUsecase() {
		
	 UseCase uc = new UseCase();

	  
	 return uc;
		
		
	}


}
