package input;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.EarlyAspect;
import entities.EarlyAspectInterface;
import entities.EarlyAspectPair;
import entities.EarlyAspectPairInterface;
import entities.Relation;


public class Adapter {

	/**2236161231
	 * @param args
	 */
	public static void main(String[] args) {

		
		List<EarlyAspectPairInterface> pairs = new ArrayList<EarlyAspectPairInterface>();
		
		File f = new File("output.txt");
		FileReader fr;
		try {
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String eachLine;
			String result;
			eachLine = br.readLine();
			while (eachLine != null) {
			
				 String[] arreglo = eachLine.split(" ");
				 if ("Aspecto".equals(arreglo[0])) { 
					 String id = arreglo[1].split("º")[2];
					 eachLine = br.readLine();
					 eachLine = br.readLine();
					 eachLine = br.readLine();
					 arreglo = eachLine.split(" ");
					 int i=1;
					 while (arreglo[0].equals("Cuenta")) {
						 
						 eachLine = br.readLine();
						 arreglo = eachLine.split(" ");
						 i++;	 
					 }
					 eachLine = br.readLine();
					 eachLine = br.readLine();
					 arreglo = eachLine.split(" ");
					 i=1;
					 while (arreglo[0].equals("Verbo")) {
						 String verb = arreglo[1];
						 String object = arreglo[5];
						 EarlyAspectPair eap = new EarlyAspectPair(object, verb);
						 pairs.add(eap);
						 EarlyAspect ea = new EarlyAspect();
						 ea.setId(Integer.valueOf(id));
						 ea.setName("name");
						 ea.setPairs(pairs);
						
						 List<EarlyAspectInterface> earlyAspects = new ArrayList<EarlyAspectInterface>();
						 earlyAspects.add(ea);
			
						 List<Integer> useCaseIds = new ArrayList<Integer>();			 
						 Relation r = new Relation();
						 r.setEarlyAspectId(1);
						 r.setUseCaseIds(useCaseIds);
						 List<Relation> relations = new ArrayList<Relation>();
				 
				 } 
				eachLine = br.readLine();
			}

			 Project p = new Project();
		//	 p.setEarlyAspects(earlyAspects);
			 p.setName("nn");
			// p.setRelations(relations);
			
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
