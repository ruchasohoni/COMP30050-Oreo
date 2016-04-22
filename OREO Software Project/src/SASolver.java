
public class SASolver {
	private static double temperature;
	private CandidateSolution sol;
	int count;
	
	public void solve(CandidateSolution s, double t){
		temperature = t;
		count = 0;
		this.sol = s;
		while (temperature > 0.001){
			count++;
			sol.tweakSA((double) temperature);
			temperature*=0.999;
		}
	}
}
