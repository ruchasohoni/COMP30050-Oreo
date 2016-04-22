
public class SASolver {
	private static double temperature;
	private CandidateSolution sol;
	int count;
	
	public SASolver(CandidateSolution s, double t){
		temperature = t;
		count = 0;
		this.sol = s;
		int solEn = s.getEnergy();
		while (temperature > 0.001){
			count++;
			sol.tweakSA((double) temperature);
		//	System.out.println(temperature);
			temperature*=0.999;
		}
	}
}
