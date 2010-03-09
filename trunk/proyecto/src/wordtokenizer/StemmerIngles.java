package wordtokenizer;
import java.util.ArrayList;
import java.util.List;

public class StemmerIngles extends Stemmer{


	private List<String> validLiEnding = new ArrayList<String>();
	private List<String> doubles = new ArrayList<String>();
	private List<String>  grupo0 = new ArrayList<String>();
	private List<String>  grupo1a = new ArrayList<String>();
	private List<String>  grupo1b_1 = new ArrayList<String>();
	private List<String> grupo1b_2 = new ArrayList<String>();
	private List<String>  grupo2 = new ArrayList<String>();
	private List<String>  grupo3 = new ArrayList<String>();
	private List<String>  grupo4 = new ArrayList<String>();


	public StemmerIngles(){

		vocales.add("a");
		vocales.add("e");
		vocales.add("i");
		vocales.add("o");
		vocales.add("u");
		vocales.add("y");

		// Valid li-ending
		validLiEnding.add("c");
		validLiEnding.add("d");
		validLiEnding.add("e");
		validLiEnding.add("g");
		validLiEnding.add("h");
		validLiEnding.add("k");
		validLiEnding.add("m");
		validLiEnding.add("n");
		validLiEnding.add("r");
		validLiEnding.add("t");

		//doubles
		doubles.add("bb");
		doubles.add("dd");
		doubles.add("ff");
		doubles.add("gg");
		doubles.add("mm");
		doubles.add("nn");
		doubles.add("pp");
		doubles.add("rr");
		doubles.add("tt");

		//grupo 0
		grupo0.add("'");
		grupo0.add("'s");
		grupo0.add("'s'");

		//grupo 1a
		grupo1a.add("sses");
		grupo1a.add("ied");
		grupo1a.add("ies");
		grupo1a.add("s");
		grupo1a.add("ss");
		grupo1a.add("us");

		//grupo 1b_1
		grupo1b_1.add("eed");
		grupo1b_1.add("eedly");

		//grupo1b_2
		grupo1b_2.add("ed");
		grupo1b_2.add("edly");
		grupo1b_2.add("ing");
		grupo1b_2.add("ingly");

		//grupo2
		grupo2.add("tional");
		grupo2.add("enci");
		grupo2.add("anci");
		grupo2.add("abli");
		grupo2.add("entli");
		grupo2.add("izer");
		grupo2.add("ization");
		grupo2.add("ational");
		grupo2.add("ation");
		grupo2.add("ator");

		grupo2.add("alism");
		grupo2.add("aliti");
		grupo2.add("alli");

		grupo2.add("fulness");
		grupo2.add("ousli");
		grupo2.add("ousness");
		grupo2.add("iveness");
		grupo2.add("iviti");

		grupo2.add("biliti");
		grupo2.add("bli");

		grupo2.add("ogi");
		grupo2.add("fulli");
		grupo2.add("lessli");
		grupo2.add("li");

		//grupo3
		grupo3.add("alize");
		grupo3.add("icate");
		grupo3.add("iciti");
		grupo3.add("ical");
		grupo3.add("ful");
		grupo3.add("ness");
		grupo3.add("ative");

		//grupo4
		grupo4.add("al");
		grupo4.add("ance");
		grupo4.add("ence");
		grupo4.add("er");
		grupo4.add("ic");
		grupo4.add("able");
		grupo4.add("ible");
		grupo4.add("ant");
		grupo4.add("ement");
		grupo4.add("ment");
		grupo4.add("ent");
		grupo4.add("ism");
		grupo4.add("ate");
		grupo4.add("iti");
		grupo4.add("ous");
		grupo4.add("ive");
		grupo4.add("ize");
		grupo4.add("ion");
	}

	public int cantVocales(String palabra){
		int i = 0;
		char c;
		int cont = 0;
		while (i<palabra.length()){
			c = palabra.charAt(i);
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o'|| c == 'u')
				cont = cont + 1;
			i = i + 1;
		}
		return cont;
	}

	public boolean shortSyllabe(String palabra){
		String primera = "";
		String segunda = "";
		String tercera = "";
		//String sufijo = "";
		if (palabra.length() == 2){
			primera = palabra.substring(0,palabra.length()-1);
			segunda = palabra.substring(1);
			if (vocales.contains(primera) && !vocales.contains(segunda))
				return true;
			return false;
		}

		if (palabra.length()>=3){
			//sufijo = palabra.substring(palabra.length()-3, palabra.length());
			primera = palabra.substring(palabra.length()-3,palabra.length()-2);
			segunda = palabra.substring(palabra.length()-2,palabra.length()-1);
			tercera = palabra.substring(palabra.length()-1,palabra.length());

			if (vocales.contains(segunda) && !vocales.contains(primera) && !vocales.contains(tercera) &&
				tercera.compareTo("w")!=0 && tercera.compareTo("x")!=0 && tercera.compareTo("y")!=0)
				return true;
			else
				return false;
		}
		return false;

	}

	public boolean shortWord (String palabra){
		String sufijo = "";
		String prefijo = "";

		if (palabra.length()<=3)
			return shortSyllabe(palabra);
		else
		{
			prefijo = palabra.substring(0,palabra.length()-3);
			sufijo = palabra.substring(palabra.length()-3, palabra.length());
			if (cantVocales(prefijo)==0 && shortSyllabe(sufijo))
				return true;
		}
		return false;
	}


	//Paso 0
	public String paso0(String palabra){

		int suf = 3;
		String sufijo = "";
		boolean salir = false;

		if (palabra.length()>2){
			while (!salir && suf >= 1){
				if (palabra.length()>=suf){
					sufijo = palabra.substring(palabra.length()-suf,palabra.length());
					if (grupo0.contains(sufijo)){
						return palabra.substring(0,palabra.length()-suf);
					}
				}
				suf = suf - 1;
			}
		}
		return palabra;
	}

	//Paso 1a
	public String paso1a(String palabra){

		int suf = 4;
		String sufijo = "";
		boolean salir = false;

		if (palabra.length()>2){
			while (!salir && suf >= 1){
				if (palabra.length()>=suf){
					sufijo = palabra.substring(palabra.length()-suf,palabra.length());
					if (grupo1a.contains(sufijo)){

						if (sufijo.compareTo("sses")==0)
							return palabra.substring(0,palabra.length()-2);

						if (sufijo.compareTo("us")==0 || sufijo.compareTo("ss")==0)
							return palabra;

						if (sufijo.compareTo("s")==0){
							if(!vocales.contains(palabra.substring(palabra.length()-2,palabra.length()-1)))
								return palabra.substring(0,palabra.length()-1);
							else{
								if (this.cantVocales(palabra)>1)
									return palabra.substring(0,palabra.length()-1);
								else
									return palabra;
							}
						}
						if ((sufijo.compareTo("ies")==0 || sufijo.compareTo("ied")==0)&&
							palabra.length()==4){
							return palabra.substring(0,1)+"ie";
						}
						else{
							return palabra.substring(0,palabra.length()-3)+"i";
						}
					}
				}
				suf = suf - 1;
			}
		}
		return palabra;
	}

	//Paso 1b
	public String paso1b(String palabra){

		int suf = 5;
		String sufijo = "";
		boolean salir = false;

		String R1 = this.R1(palabra);

		if (palabra.length() > 2){
			while (!salir && suf >= 1){
				if (R1.length() >= suf){
					sufijo = R1.substring(R1.length()-suf,R1.length());
					if (grupo1b_1.contains(sufijo)){
						return palabra.substring(0,palabra.length()-suf);
					}
				}
				suf = suf - 1;
			}

			suf = 5;
			String precedencia = "";
			String terminacion = "";
			String ult = "";
			String antUlt = "";

			while (!salir && suf >= 1){
				if (palabra.length() >= suf){
					sufijo = palabra.substring(palabra.length()-suf,palabra.length());
					if (grupo1b_2.contains(sufijo)){
						precedencia = palabra.substring(0,palabra.length()-suf);
						if (cantVocales(precedencia)>0){

							if (shortWord(precedencia))
								return palabra.substring(0,palabra.length()-suf)+"e";

							//at bl iz
							terminacion = palabra.substring(palabra.length()-suf-2,palabra.length()-suf);
							if (terminacion.compareTo("at")==0 || terminacion.compareTo("bl")==0 || terminacion.compareTo("iz")==0)
								return palabra.substring(0,palabra.length()-suf)+"e";

							//si termina con double, ejemplo hopp
							ult = palabra.substring(palabra.length()-suf-1,palabra.length()-suf);
							antUlt = palabra.substring(palabra.length()-suf-2,palabra.length()-suf-1);
							antUlt = antUlt+ult;
							if (doubles.contains(antUlt))
								return palabra.substring(0,palabra.length()-suf-1);

							//Si no es ninguno de los anterirores
							return palabra.substring(0,palabra.length()-suf);
						}
					}
				}
				suf = suf - 1;
			}
		}
		return palabra;
	}

	//paso 1c
	public String paso1c(String palabra){

		//int suf = 3;
		String sufijo = "";
		String anterior = "";
		//boolean salir = false;

		if (palabra.length()>2){
			sufijo = palabra.substring(palabra.length()-1,palabra.length());
			anterior = palabra.substring(palabra.length()-2,palabra.length()-1);
			if (sufijo.compareTo("y")==0 && !vocales.contains(anterior))
				return palabra.substring(0,palabra.length()-1)+"i";
		}
		return palabra;
	}

	//paso 2
	public String paso2(String palabra){

		int suf = 7;
		String sufijo = "";
		String R1 = R1(palabra);
		String anterior = "";

		boolean salir = false;


		if (R1.length()>1){
			while (!salir && suf >= 1){
				if (R1.length()>=suf){
					sufijo = R1.substring(R1.length()-suf,R1.length());
					if (grupo2.contains(sufijo)){
						if (sufijo.compareTo("tional")==0)
							return palabra.substring(0,palabra.length()-suf)+"tion";
						if (sufijo.compareTo("enci")==0)
							return palabra.substring(0,palabra.length()-suf)+"ence";
						if (sufijo.compareTo("anci")==0)
							return palabra.substring(0,palabra.length()-suf)+"ance";
						if (sufijo.compareTo("abli")==0)
							return palabra.substring(0,palabra.length()-suf)+"able";
						if (sufijo.compareTo("entli")==0)
							return palabra.substring(0,palabra.length()-suf)+"ent";
						if (sufijo.compareTo("izer")==0 || sufijo.compareTo("ization")==0)
							return palabra.substring(0,palabra.length()-suf)+"ize";
						if (sufijo.compareTo("ational")==0 || sufijo.compareTo("ation")==0 || sufijo.compareTo("ator")==0)
							return palabra.substring(0,palabra.length()-suf)+"ate";
						if (sufijo.compareTo("alism")==0 || sufijo.compareTo("aliti")==0 || sufijo.compareTo("alli")==0)
							return palabra.substring(0,palabra.length()-suf)+"al";
						if (sufijo.compareTo("fulness")==0)
							return palabra.substring(0,palabra.length()-suf)+"ful";
						if (sufijo.compareTo("ousli")==0 || sufijo.compareTo("ousness")==0)
							return palabra.substring(0,palabra.length()-suf)+"ous";
						if (sufijo.compareTo("iveness")==0 || sufijo.compareTo("iviti")==0)
							return palabra.substring(0,palabra.length()-suf)+"ive";
						if (sufijo.compareTo("biliti")==0 || sufijo.compareTo("bli")==0)
							return palabra.substring(0,palabra.length()-suf)+"ble";
						if (sufijo.compareTo("fulli")==0)
							return palabra.substring(0,palabra.length()-suf)+"ful";
						if (sufijo.compareTo("lessli")==0)
							return palabra.substring(0,palabra.length()-suf)+"less";
						if (sufijo.compareTo("ogi")==0){
							anterior = R1.substring(R1.length()-suf-1, R1.length()-suf);
							if (anterior.compareTo("l")==0)
								return palabra.substring(0,palabra.length()-suf)+"og";
						}
						if (sufijo.compareTo("li")==0){
							anterior = R1.substring(R1.length()-suf-1, R1.length()-suf);
							if (validLiEnding.contains(anterior))
								return palabra.substring(0,palabra.length()-suf);
						}
					}
				}
				suf = suf - 1;
			}
		}
		return palabra;
	}

	//paso 3
	public String paso3(String palabra){

		int suf = 5;
		String sufijo = "";
		String R1 = R1(palabra);
		//String anterior = "";
		String R2 = R2(palabra);

		boolean salir = false;


		if (R1.length()>2){
			while (!salir && suf >= 3){
				if (R1.length()>=suf){
					sufijo = R1.substring(R1.length()-suf,R1.length());
					if (grupo3.contains(sufijo)){
						if (sufijo.compareTo("alize")==0)
							return palabra.substring(0,palabra.length()-suf)+"al";
						if (sufijo.compareTo("icate")==0 || sufijo.compareTo("iciti")==0 || sufijo.compareTo("ical")==0)
							return palabra.substring(0,palabra.length()-suf)+"ic";
						if (sufijo.compareTo("ful")==0 || sufijo.compareTo("ness")==0)
							return palabra.substring(0,palabra.length()-suf);
					}
				}
				suf = suf - 1;
			}
		}
		if (R2.length()>4){
			sufijo = R2.substring(R2.length()-5,R2.length());
			if (sufijo.compareTo("ative")==0)
				return palabra.substring(0,palabra.length()-5);
		}
		return palabra;
	}

	//Paso 4
	public String paso4(String palabra){

		int suf = 5;
		String sufijo = "";
		String anterior = "";
		String R2 = R2(palabra);
		boolean salir = false;

		if (R2.length()>1){
			while (!salir && suf >= 2){
				if (R2.length()>=suf){
					sufijo = R2.substring(R2.length()-suf,R2.length());
					if (grupo4.contains(sufijo)){
						if (sufijo.compareTo("ion")==0){
							anterior = (palabra.substring(palabra.length()-4,palabra.length()-suf));
							if (anterior.compareTo("s")==0 || anterior.compareTo("t")==0)
								return palabra.substring(0,palabra.length()-suf);
						}
						return palabra.substring(0,palabra.length()-suf);
					}
				}
				suf = suf - 1;
			}
		}
		return palabra;
	}

	//paso 5
	public String paso5(String palabra){

		//int suf = 5;
		String sufijoR1 = "";
		String sufijoR2 = "";
		String prefijoR1 = "";
		String anterior = "";
		String R1 = R1(palabra);
		String R2 = R2(palabra);

		if (R2.length()>=1){
			sufijoR2 = R2.substring(R2.length()-1);
			if (sufijoR2.compareTo("e")==0)
				return palabra.substring(0,palabra.length()-1);
			anterior = palabra.substring(palabra.length()-2,palabra.length()-1);
			if (sufijoR2.compareTo("l")== 0 && anterior.compareTo("l")==0)
				return palabra.substring(0,palabra.length()-1);
		}

		if (R1.length()>=1)
			sufijoR1 = R1.substring(R1.length()-1);

		if (sufijoR1.compareTo("e")==0){
			anterior = palabra.substring(0,palabra.length()-1);

			if (anterior.length()< 2)
				return palabra;

			/*if (palabra.length()==3)
				prefijoR1 = R1.substring(0,palabra.length()-1);*/

			if (palabra.length() >=4)
				prefijoR1 = palabra.substring(palabra.length()-4,palabra.length()-1);

			if (!shortSyllabe(prefijoR1))
				return palabra.substring(0,palabra.length()-1);
			return palabra;
		}
		return palabra;
	}



	public String stemmer (String cadena){//
		//boolean modifico = false;
		//boolean verbo = false;
		String resultado = "";
		String palabra = cadena;
		// Paso 1

		resultado = this.paso0(palabra);
		if (palabra != resultado){
			//modifico = true;
			palabra = resultado;
		}

		resultado = this.paso1a(palabra);
		if (palabra != resultado){
			//modifico = true;
			palabra = resultado;
		}


		resultado = this.paso1b(palabra);
		if (palabra != resultado){
			//modifico = true;
			palabra = resultado;
		}

		resultado = this.paso1c(palabra);
		if (palabra != resultado){
			//modifico = true;
			palabra = resultado;
		}

		resultado = this.paso2(palabra);
		if (palabra != resultado){
			//modifico = true;
			palabra = resultado;
		}

		resultado = this.paso3(palabra);
		if (palabra != resultado){
			//modifico = true;
			palabra = resultado;
		}

		resultado = this.paso4(palabra);
		if (palabra != resultado){
			//modifico = true;
			palabra = resultado;
		}

		resultado = this.paso5(palabra);
		if (palabra != resultado){
			//modifico = true;
			palabra = resultado;
		}

		//System.out.println("Raiz : "+resultado);
		return resultado;
	}
}
