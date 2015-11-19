package CS415;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimControl extends JFrame{

	Display display;
	JButton pauseB, playB, stepB, rateB, resetB, saveB, exitB;
	JComboBox rateCB;
	JTextArea messageTA;
	JPanel controlP;
	
	public SimControl(/*Simulation sim*/){
		
		//get current grid from simulation
		//Grid current = sim.getCurrentGrid();
		
		//create display from grid
		//display = new Display(current);
		display = new Display();
		
		//get iterations
		
		//JButtons
		pauseB = new JButton("Pause");
		playB = new JButton("Play");
		stepB = new JButton("Step");
		rateB = new JButton("Apply Rate");
		resetB = new JButton("Reset");
		saveB = new JButton("Save");
		exitB = new JButton("Exit");
		
		//JComboBox
		String[] rates = {"2.0", "1.0", "0.5", "0.25"};
		rateCB = new JComboBox<String>(rates);
		
		//JScrollPane
		
		//JTextArea
		messageTA = new JTextArea();
		messageTA.setRows(10);
		
		//JPanel
		controlP = new JPanel();
		controlP.setLayout(new GridLayout(8,1));
		controlP.add(pauseB);
		controlP.add(playB);
		controlP.add(stepB);
		controlP.add(rateCB);
		controlP.add(rateB);
		controlP.add(resetB);
		controlP.add(saveB);
		controlP.add(exitB);
		
		setTitle("Grid Display");
        setSize(630, 700);
        setLayout(new BorderLayout());
        add(display, BorderLayout.CENTER);
        add(controlP, BorderLayout.EAST);
        add(messageTA, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	// action listener for buttons
		class ButtonHandler implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == exitB){
					System.exit(0);
				}
				
			}
		}
	
}
