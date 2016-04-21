
public class Test {

	public static void main(String[] args) {
		PreferenceTable p = new PreferenceTable("Project allocation data.tsv");
		for(int i=0 ; i < 5 ; i++){
			CandidateSolution temp = new CandidateSolution(p);
			System.out.println("Current energy... " + temp.getEnergy());
			long startTime = System.currentTimeMillis();
			new SASolver(temp, 30);
			long endTime = System.currentTimeMillis();
			System.out.println("New energy... " + temp.getEnergy());
			float diff = (float)(endTime-startTime)/1000;
			System.out.printf("Time = %.4f seconds\n-- -- -- --\n", diff);
		}
	}
}
