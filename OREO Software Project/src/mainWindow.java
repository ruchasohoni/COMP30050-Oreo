import java.awt.*;
import java.awt.event.*;
import java.io.PrintWriter;
import javax.swing.*;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;

public class mainWindow {

	private JFrame frmOreoSoftwareProject;
	private JTextArea textPane = new JTextArea();
	private PreferenceTable p;
	private CandidateSolution temp, bestSolution;
	private final JFileChooser fileChooser = new JFileChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow window = new mainWindow();
					window.frmOreoSoftwareProject.setVisible(true);
				} catch (Exception e) {
					System.exit(0);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Tab-separated values","tsv");
		fileChooser.setFileFilter(filter);

		frmOreoSoftwareProject = new JFrame();
		frmOreoSoftwareProject.setResizable(false);
		frmOreoSoftwareProject.setTitle("OREO Software Project");
		frmOreoSoftwareProject.setBounds(100, 100, 599, 500);
		frmOreoSoftwareProject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOreoSoftwareProject.getContentPane().setLayout(null);
		frmOreoSoftwareProject.setIconImage(new ImageIcon("./icon.png").getImage());

		try{
			p = new PreferenceTable("./Project allocation data.tsv");
			temp = new CandidateSolution(p);
			bestSolution = new CandidateSolution(temp, p);
		}catch(Exception e) {
			chooseFile();
		}

		textPane.setLineWrap(true);
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textPane.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setBounds(193, 12, 388, 447);
		frmOreoSoftwareProject.getContentPane().add(scrollPane);

		// INTRO MESSAGE
		print("==--==--==--==--==--==--==--==--==--==--==");
		print("   Welcome to the OREO project assignment solver!");
		print("   -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
		print(" Default values of SA expect a runtime of  ~4 seconds");
		print(" Default values of GA expect a runtime of ~45 seconds");
		print("   -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
		print("   Use the options on the left to find a solution!");
		print("==--==--==--==--==--==--==--==--==--==--==\n");

		JTextField temperatureField = new JTextField();
		temperatureField.setHorizontalAlignment(SwingConstants.CENTER);
		temperatureField.setToolTipText("The temperature for the Boltzmann algorithm");
		temperatureField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				temperatureField.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) { }
		});
		temperatureField.setBounds(35, 93, 120, 20);
		frmOreoSoftwareProject.getContentPane().add(temperatureField);

		JTextField coolingField = new JTextField();
		coolingField.setToolTipText("The cooling facter per solution");
		coolingField.setHorizontalAlignment(SwingConstants.CENTER);
		coolingField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				coolingField.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) { }
		});
		coolingField.setBounds(35, 145, 120, 20);
		frmOreoSoftwareProject.getContentPane().add(coolingField);

		JTextField populationField = new JTextField();
		populationField.setHorizontalAlignment(SwingConstants.CENTER);
		populationField.setToolTipText("The overall size of the population of solutions");
		populationField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				populationField.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) { }
		});
		populationField.setBounds(35, 197, 120, 20);
		frmOreoSoftwareProject.getContentPane().add(populationField);

		JTextField generationField = new JTextField();
		generationField.setHorizontalAlignment(SwingConstants.CENTER);
		generationField.setToolTipText("The number of generations to work with");
		generationField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				generationField.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) { }
		});
		generationField.setBounds(35, 249, 120, 20);
		frmOreoSoftwareProject.getContentPane().add(generationField);

		JTextField percentageField = new JTextField();
		percentageField.setHorizontalAlignment(SwingConstants.CENTER);
		percentageField.setToolTipText("The top n% of solutions that can mate");
		percentageField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				percentageField.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) { }
		});
		percentageField.setBounds(35, 301, 120, 20);
		frmOreoSoftwareProject.getContentPane().add(percentageField);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		comboBox.addItem("Simulated Annealing");
		comboBox.addItem("Genetic Algorithm");
		comboBox.setMaximumRowCount(2);
		comboBox.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem() == "Simulated Annealing"){
					temperatureField.setText("300");
					coolingField.setText("0.999");
					populationField.setText("");
					generationField.setText("");
					percentageField.setText("");
					temperatureField.setEditable(true);
					coolingField.setEditable(true);
					populationField.setEditable(false);
					generationField.setEditable(false);
					percentageField.setEditable(false);
				} else {
					temperatureField.setText("");
					coolingField.setText("");
					populationField.setText("12000");
					generationField.setText("90");
					percentageField.setText("25");
					temperatureField.setEditable(false);
					coolingField.setEditable(false);
					populationField.setEditable(true);
					generationField.setEditable(true);
					percentageField.setEditable(true);
				}
			}
		});
		comboBox.setBounds(20, 36, 150, 25);
		comboBox.setSelectedItem("Simulated Annealing");
		frmOreoSoftwareProject.getContentPane().add(comboBox);

		JLabel lblSelectTheType = new JLabel("Select the algorithm to use");
		lblSelectTheType.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectTheType.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lblSelectTheType.setBounds(12, 9, 166, 18);
		frmOreoSoftwareProject.getContentPane().add(lblSelectTheType);

		JLabel lblTemperaturega = new JLabel("Temperature");
		lblTemperaturega.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperaturega.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		lblTemperaturega.setBounds(12, 70, 166, 14);
		frmOreoSoftwareProject.getContentPane().add(lblTemperaturega);

		JLabel lblDecrimentFactor = new JLabel("Cooling factor");
		lblDecrimentFactor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDecrimentFactor.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		lblDecrimentFactor.setBounds(12, 122, 166, 14);
		frmOreoSoftwareProject.getContentPane().add(lblDecrimentFactor);

		JLabel lblPopulationSizega = new JLabel("Population Size");
		lblPopulationSizega.setHorizontalAlignment(SwingConstants.CENTER);
		lblPopulationSizega.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		lblPopulationSizega.setBounds(12, 174, 166, 14);
		frmOreoSoftwareProject.getContentPane().add(lblPopulationSizega);

		JLabel lblGenerationsga = new JLabel("Number of Generations");
		lblGenerationsga.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenerationsga.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		lblGenerationsga.setBounds(12, 226, 166, 14);
		frmOreoSoftwareProject.getContentPane().add(lblGenerationsga);

		JLabel lblTopPercentga = new JLabel("Top Percent");
		lblTopPercentga.setHorizontalAlignment(SwingConstants.CENTER);
		lblTopPercentga.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		lblTopPercentga.setBounds(12, 278, 166, 14);
		frmOreoSoftwareProject.getContentPane().add(lblTopPercentga);

		// ------ THIS IS WHAT HAPPENS WHEN "GO" IS PRESSED ------ //
		// ------ I'M PUTTING COMMENTS IN TO FIND IT EASILY ------ //

		JButton btnGo = new JButton("GO!");
		btnGo.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				print("Results for "+comboBox.getSelectedItem()+"...");
				if (comboBox.getSelectedItem() == "Simulated Annealing"){ // SA
					doSA(temperatureField, coolingField);
				}else { // Genetic
					doGA(populationField, generationField, percentageField);
				}
			}
		});
		btnGo.setBounds(60, 330, 70, 25);
		frmOreoSoftwareProject.getContentPane().add(btnGo);

		JButton button = new JButton("CLEAR");
		button.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPane.setText("");
				temperatureField.setText("");
				coolingField.setText("");
				populationField.setText("");
				generationField.setText("");
				percentageField.setText("");
			}
		});
		button.setBounds(60, 364, 70, 25);
		frmOreoSoftwareProject.getContentPane().add(button);

		JButton fileSave = new JButton("Save Best Solution");
		fileSave.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		fileSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveFile();
			}
		});				
		fileSave.setBounds(25, 398, 140, 25);
		frmOreoSoftwareProject.getContentPane().add(fileSave);

		JButton fileButton = new JButton("Open New File");
		fileButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		fileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooseFile();
			}
		});				
		fileButton.setBounds(25, 432, 140, 25);
		frmOreoSoftwareProject.getContentPane().add(fileButton);

	}

	private void chooseFile(){
		int result = fileChooser.showOpenDialog(frmOreoSoftwareProject);
		if(result == 0)
			if(fileChooser.getSelectedFile().getName().endsWith("tsv")) {
				try {
					p = new PreferenceTable(fileChooser.getSelectedFile().getAbsolutePath());
					fileChooser.setCurrentDirectory(fileChooser.getSelectedFile());
					temp = new CandidateSolution(p);
					bestSolution = new CandidateSolution(temp, p);
					print("File chosen: " + fileChooser.getSelectedFile().getAbsolutePath());
				} catch (Exception e) {
					fileChooser.cancelSelection();
					print("Invalid file type!");
				}

			}
		if(p == null){
			System.exit(0);
		}
	}

	private void doSA(JTextField temperatureField, JTextField coolingField) {
		try {
			if(Double.parseDouble(coolingField.getText()) >= 1
					|| Double.parseDouble(coolingField.getText()) < 0){
				print("Cooling factor must be between 0 and 1!");
				throw new Exception();
			}
			int resultI = (int)(temp.getSatisfaction()*10000);
			float resultF = (float)resultI/100;
			long startTime = System.currentTimeMillis();
			SASolver sa = new SASolver();
			sa.solve(temp, Double.parseDouble(temperatureField.getText()),Double.parseDouble(coolingField.getText()));
			long endTime = System.currentTimeMillis();
			print(("Start satisfaction... " + resultF + "%"));
			resultI = (int)(temp.getSatisfaction()*10000);
			resultF = (float)resultI/100;
			print(("New satisfaction... " + resultF + "%"));
			float diff = (float)(endTime-startTime)/1000;
			print("Completed in "+diff+" seconds\n-- -- -- --");
			if (temp.getSatisfaction() >= bestSolution.getSatisfaction()){
				bestSolution = new CandidateSolution(temp, p);
			}
		}catch(Exception e)	{ print("Invalid values!"); }
	}

	private void doGA(JTextField populationField,JTextField generationField,JTextField percentageField) {
		try{
			if(Integer.parseInt(generationField.getText()) < 1
					|| Integer.parseInt(percentageField.getText()) < 1
					|| Integer.parseInt(percentageField.getText()) > 99
					|| Integer.parseInt(generationField.getText()) < 1){
				print("Invalid values, make sure population is greater than 1"
						+ " and the percentage is between 1 and 100");
				throw new Exception("Inputs out of bounds.");
			}
			long startTime = System.currentTimeMillis();
			ArrayList<CandidateSolution> population = new ArrayList<CandidateSolution>();

			for(int i = 0; i < Integer.parseInt(populationField.getText()); i++){
				population.add(new CandidateSolution(p));
			}

			GASolver g = new GASolver();
			CandidateSolution best = g.getSolution(population,
					Integer.parseInt(generationField.getText()),
					Integer.parseInt(percentageField.getText()));
			long endTime = System.currentTimeMillis();
			float diff = (float)(endTime-startTime)/1000;
			int result = (int)(best.getSatisfaction()*10000);
			float resultF = (float)result/100;
			print("Overall satisfaction: " + resultF + "%");
			print("Completed in "+diff+" seconds\n-- -- -- --");
			if (best.getSatisfaction() >= bestSolution.getSatisfaction()){
				bestSolution = new CandidateSolution(best, p);
			}
		}catch(Exception e) { print("Invalid values!\n"); } 
	}

	private void print(String s){
		textPane.append(s+"\n");
	}

	private void saveFile(){
		int choice = fileChooser.showSaveDialog(frmOreoSoftwareProject);
		if(choice == JFileChooser.APPROVE_OPTION) {
			try{
				int resultI = (int)(bestSolution.getSatisfaction()*10000);
				float resultF = (float)resultI/100;
				print(("Saving best solution with satisfaction of " + resultF + "%..."));
				String fileName = fileChooser.getSelectedFile().getAbsolutePath();
				if(!fileName.endsWith(".tsv"))
					fileName += "_" + System.currentTimeMillis() + ".tsv";
				else
					fileName = fileName.substring(0, fileName.length()-4)
					+ "_" + System.currentTimeMillis() + ".tsv";
				PrintWriter savedFile = new PrintWriter(fileName);
				savedFile.write(bestSolution.toString());
				savedFile.close();
				print("File saved successfully!\n" + fileName);
			}catch(Exception e){
				print("Cannot save file: " +e);
			}
		}
	}
}
