package algorithms;

import java.util.List;

import ontology.QualityAttributeBelongable;

import entities.EarlyAspect;
import entities.QualityAttributeTheme;
import entities.RichedWord;

public class OntologyAlgorithm implements Algorithm{

	private QualityAttributeBelongable qabelongable;
	
	@Override
	public QualityAttributeTheme getQualityAttributeTheme(
			List<RichedWord> words, EarlyAspect earlyAspect) {
		// TODO Auto-generated method stub
		return null;
	}

	public QualityAttributeBelongable getQabelongable() {
		return qabelongable;
	}

	public void setQabelongable(QualityAttributeBelongable qabelongable) {
		this.qabelongable = qabelongable;
	}

	
}
