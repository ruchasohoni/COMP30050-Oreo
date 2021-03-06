
public class SASolver {
	private static double temperature;
	private CandidateSolution sol;
	int count;
	
	public void solve(CandidateSolution s, double t,double cooling){
		temperature = t;
		count = 0;
		this.sol = s;
//		System.out.println("Old: " + sol.getEnergy());
//		System.out.println("Old: " + (int)(sol.getSatisfaction()*100));
		while (temperature > 0.001){
			count++;
			sol.tweakSA((double) temperature);
			temperature*=cooling;
		}
//		System.out.println("New: " + sol.getEnergy());
//		System.out.println("New: " + (int)(sol.getSatisfaction()*100));
	}
}
