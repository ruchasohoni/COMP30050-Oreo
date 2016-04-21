import java.util.*;

public class CandidateSolution {

	private PreferenceTable pref;
	private List<CandidateAssignment> assignments;
	private Random RND = new Random();
	private static final int PENALTY = 1000;
	public int tweakCount, totalChange;

	/**
	 * This class stores a collection of individual assignments. Solutions can be compared by how desirable they are.
	 * In the ideal solution, every student will be assigned their number one preference and there will be no duplicates.
	 * The energy and fitness of a solution can be calculated and used to represent the solutions efficiency.
	 */
	public CandidateSolution(PreferenceTable pref){
		this.pref = pref;
		tweakCount = totalChange = 0;
		assignments = new ArrayList<CandidateAssignment>();
		fillAssignments();
	}

	/**
	 * Goes through the list of all student entries, and creates a CandidateAssignment object for each student. As each
	 * candidate's assignment is chosen randomly from their list of preferences when the object is created, each solution
	 * is randomized. Each CandidateSolution object will store a list of all the assignments locally.
	 */
	private  void fillAssignments(){
		List<StudentEntry> students = pref.getAllStudentEntries();
		for (StudentEntry student : students) {
			CandidateAssignment assignment = new CandidateAssignment(student);
			assignments.add(assignment);
		}
	}
	/**
	 * Returns the assignment object for a given student name. As  the StudentEntry is stored locally in each assignment
	 * object, that means all the data stored in the StudentEntry is easily accessible using the accessor methods from the 
	 * class.
	 */
	public CandidateAssignment getAssignmentFor(String name){
		for (CandidateAssignment assignment : assignments){
			if (name.equals(assignment.getStudentName())){
				return assignment;
			}
		}
		return null;
	}


	/**
	 * Calculates the energy (how undesirable) a solution is. Firstly, it sums the individual energy of all the assignments
	 * making up the solution. It also sees how many repeat assignments there are (candidates with the same assignment
	 * as another candidate). For every duplicate assignment, it will add a penalty (set at 1000) to the energy.
	 * Therefore, solutions with a low number of duplicate assignments that contains candidates who are given one of their
	 * top preferences will have a lower energy, meaning it is more desirable.
	 */
	public int getEnergy(){
		int total = 0;
		Hashtable<String, CandidateAssignment> occurrences = new Hashtable<String, CandidateAssignment>();
		for (CandidateAssignment a : assignments){
			total += a.getEnergy();
			if(occurrences.containsKey(a.getAssignment().intern())){
				total += PENALTY;
			}
			else occurrences.put(a.getAssignment().intern(), a);
		}
		return total;
	}

	/**
	 * The fitness is the inverse of the energy of a solution. Where energy represents how undesirable a solution is,
	 * the fitness represents how desirable and 'good' the solution is. The lower the energy, the higher the fitness,
	 * the better the solution is.
	 */
	public int getFitness(){
		return getEnergy() * -1;
	}

	public CandidateAssignment getRandomAssignment(){
		return assignments.get(RND.nextInt(assignments.size()));
	}

	public boolean tweakHC(){
		int oldEnergy = getEnergy();
		CandidateAssignment randomAssignment = getRandomAssignment();
		randomAssignment.randomizeAssignment();
		int newEnergy = getEnergy();
		tweakCount++;
		 if (oldEnergy < newEnergy){
			randomAssignment.undoChange();
			return false;
		 } 
		 else return true;
	}	 
	
	public boolean tweakSA(double T){
		int oldEnergy = getEnergy();
		CandidateAssignment randomAssignment = getRandomAssignment();
		randomAssignment.randomizeAssignment();
		int newEnergy = getEnergy();
		tweakCount++;
		if (oldEnergy < newEnergy){
			if(boltzmann(Math.abs((oldEnergy - newEnergy)), T)){
				randomAssignment.undoChange();
				return false;
			}
		} 
		return true;
	}


	private boolean boltzmann(double difference, double T) {
		double rand = Math.random();
//		System.out.println(1/Math.pow(Math.E, difference/T) + "\t" + rand);
		if(rand < 1/Math.pow(Math.E, difference/T)){
			System.out.println("true");
			return true;
		}
		System.out.println("false");
		return false;
	}
}
