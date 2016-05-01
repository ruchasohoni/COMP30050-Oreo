import java.util.*;


public class StudentEntry {

	private String name; 
	private List<String> preferences = new ArrayList<String>();
	private boolean preassigned = false;
	private int numberStatedPreferences;
	private Random RND = new Random();

	public StudentEntry(String name){
		this.name = name;
	}
	
	public StudentEntry(StudentEntry e1){
		name = e1.getStudentName();
		preferences = new ArrayList<String>(e1.getOrderedPreferences());
		preassigned = e1.hasPreassignedProject();
		numberStatedPreferences = e1.getNumberOfStatedPreferences();
	}

	public String getStudentName(){
		return name;
	}
	
	public int getIndexOfAssignment(String pname){
		return preferences.indexOf(pname.intern());
	}

	public List<String> getOrderedPreferences(){
		return preferences;
	}

	/**will select a preference at random from their list of ordered preferences*/
	public String getRandomPreference(){
		return preferences.get(RND.nextInt(preferences.size()));
	}
	
	public String getTopPreference(){
		return preferences.get(0);
	}

	/**Checks if a string is already in their list of preferences*/
	public boolean hasPreference(String preference){
		if (preferences.contains(preference)){
			return true;
		}
		else return false;
	}

	/**takes a list passed in from the preferenceTable class containing all the preferences stated
	 in the text file. also sets the number of stated preferences so that the list of preferences
	 can be added to, but the stated number is only set  once (when this method is called).
	 */
	public void setPreferences(List<String> preferences){
		this.preferences = preferences;
		numberStatedPreferences = preferences.size();
	}

	/**the new project can be added to the list without the number of stated preferences (initialised 
	 to the original list) changing. Checks that the preference isn't already there.
	 */
	public void addProject(String pname){
		pname = pname.intern();
		if (!hasPreference(pname)){
			preferences.add(pname);
		}
	}

	/**as in my program the preferences are set first, I didn't send in the preassigned preference (if 
	 there is one) to this method. instead, I passed in the second entry in the line vector (whether
	 or not they have a preassigned project). so the student is noted as having a preassigned project
	 if they only have one preference, and they have a "Yes" under the Prearranged Preference 1 column
	*/
	public void preassignProject(String pname){
		if (preferences.size() == 1 && pname.equals("Yes")){
			preassigned = true;
		}
	}

	public boolean hasPreassignedProject(){
		return preassigned;
	}

	public int getNumberOfStatedPreferences(){
		return numberStatedPreferences;
	}

	public int getNumberPreferences(){
		return preferences.size();
	}
	
	/**
	 * Returns how far into the ordered list of a candidate's preferences their actual assignment is placed. Will return 0
	 * if their assignment is their first preference, for example.
	 */
	public int getRanking(String assignment){
		assignment = assignment.intern();
		for (int index = 0; index < preferences.size(); index++){
			if (preferences.get(index).equals(assignment.intern()))
				return index;
		}
		return -1;
	}

//	public String toString(){
//		String student = "";
//		student += "Name: " + name + "\nPreassigned project: " + preassigned + "\n";
//		student += "Number of stated preferences: " + numberStatedPreferences + "\n";
//		student += "Number of preferences : " + getNumberPreferences() + "\n";
//		student += "Preferences: " + preferences;
//		return student;
//	}
}