
public class HCSolver {
	private int heat;
	private CandidateSolution sol;
	int count;
	
	public HCSolver(CandidateSolution s, int h){
		count = 0;
		this.heat = h;
		this.sol = s;
		while (heat!= 0){
			count++;
			if (sol.tweak(h)){
				heat = h;
			}
			else {
				heat = heat - 1;
			}
		}
		System.out.println(count);
	}
}