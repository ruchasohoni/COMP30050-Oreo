import java.util.*;

public class GASolver {
	ArrayList<CandidateSolution> population;
	
	public GASolver(){
	}

	/**
	 * @param popul: ArrayList of random solutions
	 * @param generations: Number of generations until stop
	 * @param percent: Percent of population selected for breeding
	 */
	public CandidateSolution getSolution(ArrayList<CandidateSolution> popul, int generations, int percent){
		population = popul;
		sortPopulation();
		for(int i = 0; i < generations; i++){
			newGeneration(percent);
		}

		CandidateSolution best = null;
		int maxFit = -100000;
		for (CandidateSolution sol : population) {
			if(sol.getFitness() > maxFit){
				maxFit = sol.getFitness();
				best = sol;
			}
		}
		return best;
	}

	/**
	 * Sorts population in descending order of energy with highest energy solution first in the list
	 */
	private void sortPopulation(){
		Collections.sort(population, new Comparator<CandidateSolution>(){
			public int compare(CandidateSolution s1, CandidateSolution s2){
				if(s1.energy == s2.energy) return 0;
				return s1.energy > s2.energy ? -1 : 1;
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
		for(int i = N-num; i < N; i = i + 2){
			// For each generation of mating, parents have two children
			population.add(mate(
					population.get(i).getAssignments(),
					population.get(i+1).getAssignments()
			));
			population.add(mate(
					population.get(i).getAssignments(),
					population.get(i+1).getAssignments()
			));
		}
		
		sortPopulation();
		//Cull
		for(int i = 0; i < num; i++){
			population.remove(i);
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
