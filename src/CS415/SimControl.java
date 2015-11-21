package CS415;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimControl extends JFrame{

	private static final String PLAY = "Play", PAUSE = "Pause", STEP = "Step", 
			RESET = "Reset", SAVE = "Save", EXIT = "Exit";
	
	Display display;
	JButton pauseB, playB, stepB, rateB, resetB, saveB, exitB;
	JLabel rateL;
	JComboBox<Integer> rateCB;
	JTextArea messageTA;
	JPanel controlP;
	JScrollPane messageP;
	ActionListener buttonH;
	boolean running = false;
	Boolean activeTimer;
	Simulation sim;
	int displayWidth, displayHeight, buttonWidth = 90;
	long rate;
	SwingWorker worker = new CATimer();;
	
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
		resetB = new JButton(RESET);
		resetB.addActionListener(buttonH);
		saveB = new JButton(SAVE);
		saveB.addActionListener(buttonH);
		exitB = new JButton(EXIT);
		exitB.addActionListener(buttonH);
		
		//JComboBox
		rateL = new JLabel("Rate (FPS):");
		Integer[] rates = {1,2,3,4,5,10,20};
		rateCB = new JComboBox<Integer>(rates);
		rateCB.addActionListener(buttonH);

		rate = new Long((Integer)rateCB.getSelectedItem());
		
		//JTextArea
		messageTA = new JTextArea();
		messageTA.setRows(10);
		displayStats();
		messageP = new JScrollPane(messageTA);
		
		//JPanel
		controlP = new JPanel();
		controlP.setLayout(new GridLayout(8,1,10,10));
		controlP.add(pauseB);
		controlP.add(playB);
		controlP.add(stepB);
		controlP.add(rateL);
		controlP.add(rateCB);
		controlP.add(resetB);
		controlP.add(saveB);
		controlP.add(exitB);
		
		setTitle("Grid Display");
        //setSize(650, 800);
		setSize(displayWidth + buttonWidth, displayHeight + 200);
		setResizable(false);
        
		//borderlayout
		setLayout(new BorderLayout(10, 10));
        add(display, BorderLayout.CENTER);
        add(controlP, BorderLayout.EAST);
        add(messageP, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	private void play(){
		pauseB.setEnabled(true);
		playB.setEnabled(false);
		worker = new CATimer();
		worker.execute();
	}
	
	private void pause() {
		worker.cancel(true);
		playB.setEnabled(true);
		pauseB.setEnabled(false);
	}
	
	private void displayStats(){
		messageTA.setText(String.format(
						  "Frames per second: %d%n"
						+ "Generation: %d%n"
						+ "Population: %d", 
						rate, sim.getGeneration(), sim.getPopulation()));
	}

	// action listener for buttons
	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			String value = e.getActionCommand();
			switch (value) {
			case PLAY:
				play();
				break;
			case PAUSE:
				pause();
				break;
			case RESET:
				pause();
				sim.reset();
				display.draw(sim.getCurrentState());
				displayStats();
				break;
			case SAVE:
				break;
			case EXIT:
				System.exit(0);
				break;
			case STEP:
				// pause first
				pause();
				sim.step();
				display.draw(sim.getCurrentState());
				displayStats();
				break;
			default:
				if (!playB.isEnabled()){
					pause();
					play();
				}
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
			rate = new Long((Integer)rateCB.getSelectedItem());
			long time = 1000/rate;
			
			while (running){
				Thread.sleep(time);
				sim.step();
				display.draw(sim.getCurrentState());
				displayStats();
			}
			return null;
		}


	}
	
}
