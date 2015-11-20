package CS415;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimControl extends JFrame{

	private static final String PLAY = "Play", PAUSE = "Pause", STEP = "Step", 
			RATE = "Apply Rate", RESET = "Reset", SAVE = "Save", EXIT = "Exit";
	Display display;
	JButton pauseB, playB, stepB, rateB, resetB, saveB, exitB;
	JComboBox rateCB;
	JTextArea messageTA;
	JPanel controlP;
	ActionListener buttonH;
	boolean running = false;
	Simulation sim;
	
	public SimControl(Simulation sim){
		this.sim = sim;
		
		display = new Display(sim.getCurrentState());
		
		//JButtons
		buttonH = new ButtonHandler();
		pauseB = new JButton(PAUSE);
		pauseB.addActionListener(buttonH);
		pauseB.setEnabled(false);
		playB = new JButton(PLAY);
		playB.addActionListener(buttonH);
		stepB = new JButton(STEP);
		stepB.addActionListener(buttonH);
		rateB = new JButton(RATE);
		rateB.addActionListener(buttonH);
		resetB = new JButton(RESET);
		resetB.addActionListener(buttonH);
		saveB = new JButton(SAVE);
		saveB.addActionListener(buttonH);
		exitB = new JButton(EXIT);
		exitB.addActionListener(buttonH);
		
		//JComboBox
		String[] rates = {"1", "2", "3", "4"};
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
				String value = e.getActionCommand();
				switch (value){
				case PLAY:
					pauseB.setEnabled(true);
					playB.setEnabled(false);
					messageTA.setText("Play pressed\n");
					play();
					break;
				case PAUSE:
					playB.setEnabled(true);
					pauseB.setEnabled(false);
					running = false;
					messageTA.setText("Pause pressed");
					break;
				case RATE:
					messageTA.setText("Rate pressed");
					break;
				case RESET:
					messageTA.setText("Reset pressed");
					break;
				case SAVE:
					messageTA.setText("Save pressed");
					break;
				case EXIT:
					messageTA.setText("Exit pressed");
					System.exit(0);
					break;
				case STEP:
					//pause first
					playB.setEnabled(true);
					pauseB.setEnabled(false);
					messageTA.setText("Step pressed");
					sim.step();
					display = new Display(sim.getCurrentState());
					messageTA.append("\nGeneration: " + sim.getGeneration());
					break;
				default:
						System.out.println("no matching button pressed");
				}
				
			}
		}

		private void play() {
			//step simulation according to rate
			long reference = System.currentTimeMillis();
			
		}
	
}
