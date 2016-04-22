import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;

public class mainWindow {

	private JFrame frmOreoSoftwareProject;
	private JTextArea textPane = new JTextArea();

	private PreferenceTable p = new PreferenceTable("Project allocation data.tsv");
	private CandidateSolution temp = new CandidateSolution(p);

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
					e.printStackTrace();
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
		frmOreoSoftwareProject = new JFrame();
		frmOreoSoftwareProject.setResizable(false);
		frmOreoSoftwareProject.setTitle("OREO Software Project");
		frmOreoSoftwareProject.setBounds(100, 100, 450, 300);
		frmOreoSoftwareProject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOreoSoftwareProject.getContentPane().setLayout(null);
		frmOreoSoftwareProject.setIconImage(new ImageIcon("icon.png").getImage());

		textPane.setEditable(false);

		JTextField temperatureField = new JTextField();
		temperatureField.setHorizontalAlignment(SwingConstants.CENTER);
		temperatureField.setToolTipText("The temperature for the Boltzmann algorithm");
		temperatureField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				temperatureField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		temperatureField.setBounds(33, 80, 120, 20);
		frmOreoSoftwareProject.getContentPane().add(temperatureField);

		JTextField populationField = new JTextField();
		populationField.setHorizontalAlignment(SwingConstants.CENTER);
		populationField.setToolTipText("The overall size of the population of solutions");
		populationField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				populationField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		populationField.setBounds(33, 130, 120, 20);
		frmOreoSoftwareProject.getContentPane().add(populationField);

		JTextField percentageField = new JTextField();
		percentageField.setHorizontalAlignment(SwingConstants.CENTER);
		percentageField.setToolTipText("The top n% of solutions that can mate");
		percentageField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				percentageField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		percentageField.setBounds(33, 180, 120, 20);
		frmOreoSoftwareProject.getContentPane().add(percentageField);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Simulated Annealing");
		comboBox.addItem("Genetic Algorithm");
		comboBox.setMaximumRowCount(2);
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        if(comboBox.getSelectedItem() == "Simulated Annealing"){
		        	temperatureField.setText("300");
		        	populationField.setText("");
		        	percentageField.setText("");
		        	temperatureField.setEditable(true);
		        	populationField.setEditable(false);
		        	percentageField.setEditable(false);
		        } else {
		        	temperatureField.setText("");
		        	populationField.setText("1000");
		        	percentageField.setText("10");
		        	temperatureField.setEditable(false);
		        	populationField.setEditable(true);
		        	percentageField.setEditable(true);
		        }
		    }
		});
		comboBox.setBounds(18, 30, 150, 20);
		comboBox.setSelectedItem("Simulated Annealing");
		frmOreoSoftwareProject.getContentPane().add(comboBox);

		JLabel lblSelectTheType = new JLabel("Select the algorithm to use");
		lblSelectTheType.setBounds(10, 8, 166, 14);
		frmOreoSoftwareProject.getContentPane().add(lblSelectTheType);

		JLabel lblTemperaturega = new JLabel("Temperature");
		lblTemperaturega.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperaturega.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblTemperaturega.setBounds(10, 58, 166, 14);
		frmOreoSoftwareProject.getContentPane().add(lblTemperaturega);

		JLabel lblPopulationSizega = new JLabel("Population Size");
		lblPopulationSizega.setHorizontalAlignment(SwingConstants.CENTER);
		lblPopulationSizega.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPopulationSizega.setBounds(10, 108, 166, 14);
		frmOreoSoftwareProject.getContentPane().add(lblPopulationSizega);

		JLabel lblTopPercentga = new JLabel("Top Percent");
		lblTopPercentga.setHorizontalAlignment(SwingConstants.CENTER);
		lblTopPercentga.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblTopPercentga.setBounds(10, 158, 166, 14);
		frmOreoSoftwareProject.getContentPane().add(lblTopPercentga);

		JButton btnGo = new JButton("GO!");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem() == "Simulated Annealing"){ // SA
					try {
						int startEn = temp.getEnergy();
						long startTime = System.currentTimeMillis();
						SASolver sa = new SASolver();
						sa.solve(temp, Double.parseDouble(temperatureField.getText()));
						long endTime = System.currentTimeMillis();
						print(("Start energy... " + startEn));
						print(("New energy... " + temp.getEnergy()));
						float diff = (float)(endTime-startTime)/1000;
						print("Time = "+diff+" seconds\n-- -- -- --");
					}catch(Exception e)	{ print("Invalid temperature value!"); }

				}else { // Genetic
					try{
						GASolver g = new GASolver(Integer.parseInt(populationField.getText().toString()));
					}catch(Exception e) { print("Invalid values!"); }
				}
			}
		});
		btnGo.setBounds(49, 208, 89, 23);
		frmOreoSoftwareProject.getContentPane().add(btnGo);

		JButton button = new JButton("CLEAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPane.setText("");
				temperatureField.setText("");
				populationField.setText("");
				percentageField.setText("");
			}
		});
		button.setBounds(53, 239, 81, 22);
		frmOreoSoftwareProject.getContentPane().add(button);

		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setBounds(186, 11, 248, 249);
		frmOreoSoftwareProject.getContentPane().add(scrollPane);

	}


	private void print(String s){
		textPane.append(s+"\n");
	}
}
