import java.util.*;

public class GASolver {
	ArrayList<CandidateSolution> population;

	/**
	 * @param popul: ArrayList of random solutions
	 * @param generations: Number of generations until stop
	 * @param percent: Percent of population selected for breeding
	 */
	public GASolver(){
		
	}

	public CandidateSolution getSolution(ArrayList<CandidateSolution> popul, int generations, int percent){
		population = popul;

		//initial best solution
		CandidateSolution best = null;
		int maxFit = -100000;
		for (CandidateSolution sol : population) {
			if(sol.getFitness() > maxFit){
				maxFit = sol.getFitness();
				best = sol;
			}
		}

		for(int i = 0; i < generations; i++){
			sortPopulation();
			newGeneration(percent);
		}

		//final best solution
		maxFit = -100000;
		for (CandidateSolution sol : population) {
			if(sol.getFitness() > maxFit){
				maxFit = sol.getFitness();
				best = sol;
			}
		}
		return best;
	}

	/**
	 * Sorts population in ascending order by fitness with best fitness solution first in the list
	 */
	private void sortPopulation(){
		Collections.sort(population, new Comparator<CandidateSolution>(){
			public int compare(CandidateSolution s1, CandidateSolution s2){
				if(s1.energy == s2.energy)
					return 0;
				return s1.energy < s2.energy ? -1 : 1;
			}
		});
	}

	/**
	 * Adds new solutions to the population, replacing that number of bad solutions
	 */
	private void newGeneration(int percent){
		int N = population.size();
		int num = (int)(N * ((float)percent / 100)); 
		if(num%2==1){
			num--;
		}
		ArrayList<CandidateSolution> parents = new ArrayList<CandidateSolution>();
		for(int i = 0; i < num; i++){
			parents.add(population.get(i));
		}
		//		System.out.println("List of potential mates: ");
		//		for (CandidateSolution c : parents) {
		//			System.out.println(c.getFitness());
		//		}
		for(int i=0; i < num; i = i+2) {
			// For each generation of mating, parents have two children
			population.add(mate(parents.get(i).getAssignments(), parents.get(i+1).getAssignments()));
			population.add(mate(parents.get(i).getAssignments(), parents.get(i+1).getAssignments()));
		}
		sortPopulation();
		//Cull
		for(int i = N+num; i > N; i--){
			population.remove(i-1);
		}
	}

	private CandidateSolution mate(List<CandidateAssignment> mom, List<CandidateAssignment> dad){
		CandidateSolution child = new CandidateSolution();
		List<CandidateAssignment> assignments = new ArrayList<CandidateAssignment>();
		for(int i=0; i < mom.size(); i++){
			double rand = Math.random();
			if(rand < 0.5){
				assignments.add((mom.get(i)));
			}
			else {
				assignments.add((dad.get(i)));
			}
		}
		child.setAssignments(assignments);
		return child;
	}
}
