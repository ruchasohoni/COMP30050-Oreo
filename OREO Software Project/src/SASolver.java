
public class SASolver {
	private int temp;
	private CandidateSolution sol;
	int count;
	
	public SASolver(CandidateSolution s, int h){
		count = 0;
		this.temp = h;
		this.sol = s;
		while (temp!= 0){
			count++;
			if (sol.tweak((double)temp)){
				temp = h;
			}
			else {
				temp = temp - 1;
			}
		}
		System.out.println(count);
	}
}
