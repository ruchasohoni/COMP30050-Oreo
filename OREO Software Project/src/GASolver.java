import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GASolver {
	int N = 1000;
	int PERCENT = 25;
	
	public void GSolver(){
		ArrayList<CandidateSolution> population;
		PreferenceTable p = new PreferenceTable("Project allocation data.tsv");
		population = new ArrayList<CandidateSolution>();
		for(int i = 0; i < N; i++){
			population.add(new CandidateSolution(p));
		}
	}
	
	public void sortPopulation(ArrayList<CandidateSolution> population){
//		System.out.println("Before");
//		for (CandidateSolution s : population) {
//			System.out.println(s.getFitness());
//		}
		Collections.sort(population, new Comparator<CandidateSolution>(){
		     public int compare(CandidateSolution s1, CandidateSolution s2){
		         if(s1.getEnergy() == s2.getEnergy())
		             return 0;
		         return s1.getEnergy() < s2.getEnergy() ? -1 : 1;
		     }
		});
//		System.out.println("After");
//		for (CandidateSolution sol : population) {
//			System.out.println(sol.getFitness());
//		}
	}
}
