import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PreferenceTable  { 
	/**loadContentFromFile reads and parses the text file appropriately,
	and then updates this private instance variable.
	the getTable method then makes this vector available (and stops it from being edited)*/

	private Vector<Vector<String>> table = new Vector<Vector<String>>();
	private List<StudentEntry> studentList = new ArrayList<StudentEntry>();
	private	Hashtable<String, StudentEntry> studentLookup =	new	Hashtable<String, StudentEntry>();	
	private Random RND = new Random();

	public PreferenceTable() {
	}

	/**I used a relative file path rather than absolute, as this way
	 it doesn't matter where the program/file is as long as they are in the right directory*/
	public PreferenceTable(String fileName) {
		String file = new String("./" + fileName); 
		table = loadContentFromFile(file);	
		loadStudents();
	}	

	public Vector<Vector<String>> getTable(){
		return table;
	}

	public List<StudentEntry> getAllStudentEntries(){
		return studentList;
	}

	/**Iterates through the list of all students, and adds random preferences until they have reached the max*/
	public void fillPreferencesOfAll(int maxPrefs) { 
		for (StudentEntry student : studentList) {
			if (!student.hasPreassignedProject()){
				while (student.getNumberPreferences() < maxPrefs){
					student.addProject(getRandomPreference());
				}
			}
		}
	}

	/**Uses random number generator to retrieve a random student from the list of all student entries*/
	public StudentEntry getRandomStudent(){
		int random = RND.nextInt(studentList.size());
		return studentList.get(random);
	}

	/**Retrieves a random student, and then takes a random preference from their list of preferences*/
	public String getRandomPreference(){
		StudentEntry randomStudent = getRandomStudent();
		return randomStudent.getRandomPreference();
	}

	public StudentEntry lookUpStudent(String sname){
		return studentLookup.get(sname);
	}

	/**I used a FileInputStream and BufferedReader to load and then read from the file,
	line by line. 
	The program will go through the file line by line, break each line up according
	to where the tabs are, and then add this tab delimited line to a row vector.
	Once a full line is parsed, this new vector is added to the full table.
	The program will also attempt to catch any IO exceptions (such as file not found),
	so the program can handle it without an error.*/
	private Vector<Vector<String>> loadContentFromFile(String path) {
		StringTokenizer	tokens;
		FileInputStream stream;
		BufferedReader	input;
		String line;
		String cell;
		Vector<Vector<String>> table = new Vector<Vector<String>>();
		try  {
			stream = new FileInputStream(path);
			input = new BufferedReader(new InputStreamReader(stream));
			while ((line = input.readLine()) != null) {
				Vector<String> row = new Vector<String>();
				tokens	=	new StringTokenizer(line,"\t");
				while (tokens.hasMoreTokens()) {
					cell = tokens.nextToken();
					row.add(cell);
				}
				table.add(row);
			}
			input.close();
			stream.close();
		} catch (IOException e) {
			System.out.println("Exception thrown  :" + e);
		}
		return table;
	}

	/**this method uses the table created from the constructor to go through each line one by one
	and separate the line vector into the relevant pieces of information, such as the student's
	name and whether or not they have a preassigned project, and then what their ordered
	list of preferences are.
	the fully populated studentEntry object is now sent to the list of all students, as well as
	a hashmap (along with the key of the student's name) to allow students to be looked up more easily.*/
	private void loadStudents(){
		for (int i = 1; i < table.size(); i++){
			Vector<String> line = table.get(i);
			String name = line.get(0);
			StudentEntry student = new StudentEntry(name);
			List<String> preferences = new ArrayList<String>(); 
			for (int cell = 2; cell < line.size(); cell++){
				preferences.add(line.get(cell));
			}
			student.setPreferences(preferences);
			student.preassignProject(line.get(1));
			studentList.add(student);
			studentLookup.put(name, student);
		}
	}



}




