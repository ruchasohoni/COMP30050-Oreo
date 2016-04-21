
public class SASolver {
	private static int temperature;
	private CandidateSolution sol;
	int count;
	
	public SASolver(CandidateSolution s, int t){
		temperature = t;
		count = 0;
		this.sol = s;
		while (temperature > 50){
			count++;
			sol.tweakSA((double) temperature);
		//	System.out.println(temperature);
			temperature*=.98;
		}
		System.out.println(count);
	}
}
