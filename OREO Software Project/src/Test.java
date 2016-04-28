import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		PreferenceTable p = new PreferenceTable("Project allocation data.tsv");
		for(int i=0 ; i < 1 ; i++){
			CandidateSolution temp = new CandidateSolution(p);
			System.out.println("Current energy... " + temp.getEnergy());
			long startTime = System.currentTimeMillis();
			long endTime = System.currentTimeMillis();
			System.out.println("New energy... " + temp.getEnergy());
			float diff = (float)(endTime-startTime)/1000;
			System.out.printf("Time = %.4f seconds\n-- -- -- --\n", diff);
			
			testGA(12000);
		}
	}
	
	private static void testGA(int N){
		System.out.println("**************\nGA Solver Test\n");
		//create population
		ArrayList<CandidateSolution> population;
		PreferenceTable p = new PreferenceTable("Project allocation data.tsv");
		population = new ArrayList<CandidateSolution>();
		for(int i = 0; i < N; i++){
			population.add(new CandidateSolution(p));
		}
		
		long startTime = System.currentTimeMillis();
		GASolver ga = new GASolver();
		System.out.println(ga.getSolution(population, 90, 25).getFitness());
		long endTime = System.currentTimeMillis();
		float diff = (float)(endTime-startTime)/1000;
		System.out.printf("Time = %.4f seconds\n**************\n", diff);
	}
}
