
public class Test {

	public static void main(String[] args) {
		PreferenceTable p = new PreferenceTable("Project allocation data.tsv");
		CandidateSolution s = new CandidateSolution(p);
		System.out.println("Current energy... " + s.getEnergy());
		SASolver solver = new SASolver(s, 25);
		System.out.println("New energy... " + s.getEnergy());
	}
}
