package CS415;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CAMenuControl extends JFrame {
	
	JButton randomB, loadFileB, helpB, exitB;
	JComboBox populationCB, rulesetCB, filenameCB;
	JTextField pathTF;
	JLabel randomL, loadL, populationL, rulesetL, pathL;
	JSeparator sep1, sep2;
	JPanel buttonP;
	ActionListener handler = new ButtonHandler();
	FileManager fm = FileManager.getInstance();
	
	public CAMenuControl(RuleSet[] rules, String[] files){
		
		super();
		
		//buttons
		randomB = new JButton("Create Simulation");
		randomB.addActionListener(handler);
		loadFileB = new JButton("Load Simulation");
		loadFileB.addActionListener(handler);
		helpB = new JButton("Help");
		helpB.addActionListener(handler);
		exitB = new JButton("Exit");
		exitB.addActionListener(handler);
		
		//comboboxes
		Integer[] populations = {10,20,30,40,50,60,70,80,90};
		populationCB = new JComboBox<Integer>(populations);
		rulesetCB = new JComboBox<RuleSet>(rules);
		filenameCB = new JComboBox<String>(files);
		
		//labels
		Font title = new Font("Ariel", Font.BOLD, 14);
		randomL = new JLabel("Create a simulation:", JLabel.CENTER);
		randomL.setFont(title);
		populationL = new JLabel("Percent populated: ", JLabel.RIGHT);
		rulesetL = new JLabel("Ruleset to use: ", JLabel.RIGHT);
		
		loadL = new JLabel("Load simulation from xml file:", JLabel.CENTER);
		loadL.setFont(title);
		pathL = new JLabel("Select file: ", JLabel.RIGHT);
		
		sep1 = new JSeparator(JSeparator.HORIZONTAL);
		sep1.setPreferredSize(new Dimension(50,5));
		sep1.setBackground(Color.DARK_GRAY);
		
		sep2 = new JSeparator(JSeparator.HORIZONTAL);
		sep2.setPreferredSize(new Dimension(50,5));
		sep2.setBackground(Color.DARK_GRAY);
		
		buttonP = new JPanel();
		buttonP.setLayout(new GridLayout(1,2,1,1));
		buttonP.add(helpB);
		buttonP.add(exitB);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		//row1
		c.gridwidth = 2;
		add(randomL, c);
		//row2
		c.gridwidth = 1;
		c.gridy = 1;
		c.gridx = 0;
		add(rulesetL, c);
		c.gridx = 1;
		add(rulesetCB,c);
		//row3
		c.gridwidth = 1;
		c.gridy++;
		c.gridx = 0;
		add(populationL,c);
		c.gridx++;
		add(populationCB,c);
		c.gridy++;
		c.gridx = 1;
		add(randomB,c);
		//sep
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 2;
		add(sep1,c);
		//row4
		c.gridwidth = 2;
		c.gridy++;
		c.gridx = 0;
		add(loadL,c);
		//row5
		c.gridwidth = 1;
		c.gridy++;
		c.gridx = 0;
		add(pathL,c);
		c.gridx = 1;
		add(filenameCB,c);
		c.gridy++;
		c.gridx = 1;
		add(loadFileB,c);
		//sep
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 3;
		add(sep2,c);
		//row6
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 3;
		add(buttonP,c);
		
		
		
		
		setSize(500,300);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	
	class ButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == randomB){
				Simulation sim = new Simulation((RuleSet)rulesetCB.getSelectedItem(), 50, 50, (int)populationCB.getSelectedItem()); 
				CAApplication.control = new SimControl(sim);
				setVisible(false);
			}
			else if (e.getSource() == loadFileB){
				Simulation sim = fm.loadXML(filenameCB.getSelectedItem().toString());
				new SimControl(sim);
				setVisible(false);
			}
			else if (e.getSource() == exitB){
				JOptionPane op = new JOptionPane();
				op.showMessageDialog(null, "Your application is awesome, and ran perfectly!");
				System.exit(0);
			}
			else if (e.getSource() == rulesetCB){
				if (rulesetCB.getSelectedItem().getClass().getName().equals("CS415.ConwaysGameOfLife")){
					populationCB.setEnabled(false);
				} else {
					populationCB.setEnabled(true);
				}
			}
			
		}
		
	}

}
