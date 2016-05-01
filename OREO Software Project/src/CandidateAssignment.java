
public class CandidateAssignment {

	private StudentEntry student;
	private String assignment;
	private String lastAssignment;
	
	/**
	 * A StudentEntry object is used to create a CandidateAssignment. This allows all the information on a student (their name,
	 * their list of preferences etc.) to be accessed in this class. Their last assignment is initialised as null as there
	 * is no stored previous assignment for them to revert to. A candidates initial assignment is chosen randomly from the 
	 * students list of preferences.
	 */
	public CandidateAssignment(StudentEntry student){
		this.student = student;
		lastAssignment = null;
		assignment = student.getRandomPreference();
	}
	
	public CandidateAssignment(CandidateAssignment a){
		this.student = new StudentEntry(a.getStudent());
		lastAssignment = a.getLastAssignment();
		assignment = a.getAssignment();
	}

	public StudentEntry getStudent(){
		return student;
	}

	public String getAssignment(){
		return assignment;
	}

	/**
	 * This method calculates the energy (how undesirable) an assignment is. It uses the formula (ranking+1)^2. It shows
	 * how unhappy a student is with their assignment, according to their ordered list of preferences.
	 */
	public int getEnergy(){
		return (int) Math.pow(student.getRanking(assignment)+1, 2);
	}
	
	private double getIndexOfAssignment() {
		return student.getIndexOfAssignment(assignment);
	}

	/**
	 * When a students assignment is changed, their last assignment is stored locally in case the change needs to be reversed.
	 */
	public String getLastAssignment(){
		return lastAssignment;
	}
	
	public double getSatisfaction(){
		double index = getIndexOfAssignment();
		return 1.00 - (index*(1.00/11.00));
	}

	public String getStudentName(){
		return student.getStudentName();
	}

	/**
	 * Retrieves another random assignment from the students list of preferences. Their last assignment is stored locally.
	 */
	public void randomizeAssignment(){
		lastAssignment = assignment;
		assignment = student.getRandomPreference();
	}

	/**
	 * As long as their last assignment is stored (i.e. there is a change to undo), a students current and last assignment
	 * can be swapped, effectively undoing the last change.
	 */
	public void undoChange(){
		if (lastAssignment!= null){
			String temp;
			temp = assignment;
			assignment = lastAssignment;
			lastAssignment = temp;
		}
		else {
			System.out.println("Cannot undo change, as there is no saved last assignment for this assignment.");
		}
	}
}
