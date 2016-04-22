import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class mainWindow {

	private JFrame frmOreoSoftwareProject;
	private double temperature = 500;

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

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Simulated Annealing");
		comboBox.addItem("Genetic Algorithm");
		comboBox.setMaximumRowCount(2);
		comboBox.setBounds(18, 36, 150, 20);
		frmOreoSoftwareProject.getContentPane().add(comboBox);

		JLabel lblSelectTheType = new JLabel("Select the type of algorithm to use");
		lblSelectTheType.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectTheType.setLabelFor(comboBox);
		lblSelectTheType.setBounds(10, 11, 166, 14);
		frmOreoSoftwareProject.getContentPane().add(lblSelectTheType);

		JTextArea textPane = new JTextArea();
		textPane.setEditable(false);
		textPane.setBounds(186, 11, 248, 249);
		frmOreoSoftwareProject.getContentPane().add(textPane);

		JButton btnGo = new JButton("GO!");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem() == "Simulated Annealing"){
					textPane.append(("Current energy... " + temp.getEnergy()));
					long startTime = System.currentTimeMillis();
					SASolver sa = new SASolver();
					sa.solve(temp,temperature);
					long endTime = System.currentTimeMillis();
					textPane.append(("\nNew energy... " + temp.getEnergy()));
					float diff = (float)(endTime-startTime)/1000;
					textPane.append("\nTime = "+diff+" seconds\n-- -- -- --\n");
				}else {
					// Genetic
				}
			}
		});
		btnGo.setBounds(49, 67, 89, 23);
		frmOreoSoftwareProject.getContentPane().add(btnGo);
		
		JButton button = new JButton("CLEAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPane.setText("");
			}
		});
		button.setBounds(18, 239, 81, 22);
		frmOreoSoftwareProject.getContentPane().add(button);

	}
}
