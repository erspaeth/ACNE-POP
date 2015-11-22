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
	ActionListener handler = new ButtonHandler();
	FileManager fm = FileManager.getInstance();
	
	public CAMenuControl(RuleSet[] rules, String[] files){
		
		super();
		
		//buttons
		randomB = new JButton("random");
		randomB.addActionListener(handler);
		loadFileB = new JButton("loadfile");
		loadFileB.addActionListener(handler);
		helpB = new JButton("help");
		helpB.addActionListener(handler);
		exitB = new JButton("exit");
		exitB.addActionListener(handler);
		
		//comboboxes
		Integer[] populations = {10,20,30,40,50,60,70,80,90};
		populationCB = new JComboBox<Integer>(populations);
		rulesetCB = new JComboBox<RuleSet>(rules);
		filenameCB = new JComboBox<String>(files);
		
		//labels
		randomL = new JLabel("Randomly populated simulation");
		populationL = new JLabel("Percent populated");
		rulesetL = new JLabel("Ruleset to use");
		
		loadL = new JLabel("Load a saved simulation");
		pathL = new JLabel("Select file");
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		//row1
		c.gridwidth = 3;
		add(randomL, c);
		//row2
		c.gridwidth = 1;
		c.gridy = 1;
		c.gridx = 0;
		add(populationL, c);
		c.gridx = 1;
		add(populationCB,c);
		c.gridx = 2;
		c.gridheight = 2;
		add(randomB,c);
		c.gridheight = 1;
		//row3
		c.gridy++;
		c.gridx = 0;
		add(rulesetL,c);
		c.gridx++;
		add(rulesetCB,c);
		//row4
		c.gridwidth = 3;
		c.gridy = 3;
		c.gridx = 0;
		add(loadL,c);
		//row5
		c.gridwidth = 1;
		c.gridy = 4;
		c.gridx = 0;
		add(pathL,c);
		c.gridx = 1;
		add(filenameCB,c);
		c.gridx = 2;
		add(loadFileB,c);
		//row6 empty
		//row7
		c.gridy = 6;
		c.gridx = 0;
		add(helpB,c);
		c.gridx = 2;
		add(exitB,c);
		
		
		
		
		setSize(500,200);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	
	class ButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == randomB){
				Simulation sim = new Simulation((RuleSet)rulesetCB.getSelectedItem(), 50, 50, (int)populationCB.getSelectedItem()); 
				new SimControl(sim);
			}
			else if (e.getSource() == loadFileB){
				Simulation sim = fm.loadXML(filenameCB.getSelectedItem().toString());
				new SimControl(sim);
			}
			
		}
		
	}

}
