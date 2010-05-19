package filters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import entities.RichedWord;

public class FilterWeight extends Filter {

	private String fileName;
	
	public FilterWeight(String weightFile) {
		this.fileName=weightFile;
	}

	public List<RichedWord> filter(List<RichedWord> list) {

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(fileName));
		} catch (IOException e) {
		}
		List<RichedWord> result = new ArrayList<RichedWord>();
		for (RichedWord rw : list) {
			String peso = properties.getProperty((String) rw.getAttribute(RichedWord.SECTION));
			rw.setAttribute(RichedWord.WEIGHT, peso==null ? new Integer(1) : Integer.parseInt(peso));
			result.add(rw);
		}
		return result;
	}
}
