
public class HCSolver {
	private CandidateSolution sol;
	private static final int TEMPERATURE = 25;
	int count, index;
	
	public HCSolver(CandidateSolution s){
		count  = 0;
		index = TEMPERATURE;
		this.sol = s;
		while (index!= 0){
			count++;
			if (sol.tweakHC()){
				index = TEMPERATURE;
			}
			else {
				index--;
			}
		}
		System.out.println(count);
	}
}