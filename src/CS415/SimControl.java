package CS415;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimControl extends JFrame{

	private static final String PLAY = "Play", PAUSE = "Pause", STEP = "Step", 
			RATE = "Apply Rate", RESET = "Reset", SAVE = "Save", EXIT = "Exit";
	private static final boolean DEBUG = false;
	Display display;
	JButton pauseB, playB, stepB, rateB, resetB, saveB, exitB;
	JComboBox rateCB;
	JTextArea messageTA;
	JPanel controlP;
	JScrollPane messageP;
	ActionListener buttonH;
	boolean running = false;
	Simulation sim;
	int displayWidth, displayHeight, buttonWidth = 120;
	
	public SimControl(Simulation sim){
		this.sim = sim;
		
		display = new Display(sim.getCurrentState());
		displayWidth = display.getWidth()+10;
		displayHeight = display.getHeight()+10;
		
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
		messageTA.setText(
				String.format(
						"Generation: %d%nPopulation: %d", 
						sim.getGeneration(), sim.getPopulation()
						)
				);		
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
        //setSize(650, 710);
		setSize(displayWidth + buttonWidth, displayHeight + 200);
        setLayout(new BorderLayout(10, 10));
        add(display, BorderLayout.CENTER);
        add(controlP, BorderLayout.EAST);
        add(messageTA, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	private void play(){
		new CATimer().execute();;
	}

	// action listener for buttons
	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String value = e.getActionCommand();
			switch (value) {
			case PLAY:
				pauseB.setEnabled(true);
				playB.setEnabled(false);
				if (DEBUG) {
					messageTA.setText("Play pressed\n");
				}
				play();
				break;
			case PAUSE:
				playB.setEnabled(true);
				pauseB.setEnabled(false);
				running = false;
				if (DEBUG) {
					messageTA.setText("Pause pressed");
				}
				break;
			case RATE:
				play();
				if (DEBUG) {
					messageTA.setText("Rate pressed");
				}
				break;
			case RESET:
				sim.reset();
				display.draw(sim.getCurrentState());
				updateMessage(String.format("Generation: %d%nPopulation: %d", sim.getGeneration(), sim.getPopulation()));
				if (DEBUG) {
					messageTA.setText("Reset pressed");
				}
				break;
			case SAVE:
				if (DEBUG) {
					messageTA.setText("Save pressed");
				}
				break;
			case EXIT:
				if (DEBUG) {
					messageTA.setText("Exit pressed");
				}
				System.exit(0);
				break;
			case STEP:
				// pause first
				playB.setEnabled(true);
				pauseB.setEnabled(false);
				running = false;
				if (DEBUG) {
					messageTA.setText("Step pressed");
				}
				sim.step();
				display.draw(sim.getCurrentState());
				messageTA.setText(
						String.format("Generation: %d%nPopulation: %d", sim.getGeneration(), sim.getPopulation()));

				break;
			default:
				System.out.println("no matching button pressed");
			}

		}
	}

	protected void updateMessage(String message) {
		messageTA.setText(message);
	}
		
	class CATimer extends SwingWorker {

		@Override
		protected Object doInBackground() throws Exception {
			running = true;
			long current, previous = System.nanoTime();
			long time = Long.parseLong(rateCB.getSelectedItem().toString());
			while (running){
				current = System.nanoTime();
				if ((current - previous)/1000000000 > time){
					sim.step();
					display.draw(sim.getCurrentState());
					updateMessage(String.format(
									"Generation: %d%nPopulation: %d", 
									sim.getGeneration(), sim.getPopulation()
									));
					System.out.println("Running");
					previous = current;
				}
			}
			return null;
		}


	}
	
}
