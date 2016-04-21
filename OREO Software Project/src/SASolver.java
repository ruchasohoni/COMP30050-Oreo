
public class SASolver {
	private int heat;
	private CandidateSolution sol;
	int count;
	
	public SASolver(CandidateSolution s, int h){
		count = 0;
		this.heat = h;
		this.sol = s;
//		System.out.println("Initial energy: " +s.getEnergy());
		while (heat!= 0){
			count++;
//			System.out.println(s.getEnergy());
			if (sol.tweak()){
				heat = h;
			}
			else {
				heat = heat - 1;
			}
			//System.out.println(heat);
		}
		System.out.println(count);
	}
}