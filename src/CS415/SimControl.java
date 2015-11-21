package CS415;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;

public class SimControl extends JFrame{

	private static final String PLAY = "Play", PAUSE = "Pause", STEP = "Step", 
			RESET = "Reset", SAVE = "Save", EXIT = "Exit";
	
	Display display;
	JButton pauseB, playB, stepB, rateB, resetB, saveB, exitB;
	JLabel rateL, fpsL, generationL, populationL;
	JTextField fpsTF, generationTF,populationTF;
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
		displayWidth = display.getWidth()+20;
		displayHeight = display.getHeight()+20;
		display.setPreferredSize(new Dimension(displayWidth,displayHeight));
		
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
		rateL = new JLabel("Rate (FPS): ", JLabel.CENTER);
		Integer[] rates = {1,2,3,4,5,10,20};
		Arrays.sort(rates, Collections.reverseOrder());
		rateCB = new JComboBox<Integer>(rates);
		rateCB.addActionListener(buttonH);

		rate = new Long((Integer)rateCB.getSelectedItem());
		
		//JLabels
		fpsL = new JLabel("FPS", JLabel.CENTER);
		generationL = new JLabel("Generation", JLabel.CENTER);
		populationL = new JLabel("Population", JLabel.CENTER);
		
		//JTextFields
		fpsTF = new JTextField(rateCB.getSelectedItem().toString(), JTextField.CENTER);
		fpsTF.setEditable(false);
		generationTF = new JTextField("" + sim.getGeneration(), JTextField.CENTER);
		generationTF.setEditable(false);
		populationTF = new JTextField("" + sim.getPopulation(), JTextField.CENTER);
		populationTF.setEditable(false);
		
		//JTextArea
		messageTA = new JTextArea();
		messageTA.setRows(10);
		displayStats();
		messageP = new JScrollPane(messageTA);
		
		//JPanel
		controlP = new JPanel();
		controlP.setLayout(new GridLayout(1,5,1,1));
		controlP.add(playB);
		controlP.add(pauseB);
		controlP.add(stepB);
		controlP.add(rateL);
		controlP.add(rateCB);
		//controlP.add(resetB);
		//controlP.add(saveB);
		//controlP.add(exitB);
		
		setTitle("Grid Display");
        //setSize(650, 800);
		setSize(displayWidth, displayHeight + 250);
		setResizable(false);
        
//		//borderlayout
//		setLayout(new BorderLayout(10, 10));
//        add(display, BorderLayout.CENTER);
//        add(controlP, BorderLayout.NORTH);
//        add(messageP, BorderLayout.SOUTH);
        
        //gridbaglayout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //first row
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5,5,5,5);
        c.weightx = 1;
        c.weighty = 5;
        c.gridx = 0;
        c.gridy = 0;
        add(playB,c);
        c.gridx++;
        add(pauseB,c);
        c.gridx++;
        add(stepB,c);
        c.gridx++;
        add(rateL,c);
        c.gridx++;
        add(rateCB,c);
        //display
        c.gridy++;
        c.gridx = 0;
        c.gridwidth = 5;
        c.weighty = displayHeight;
        add(display, c);
        c.gridy++;
        c.gridx = 0;
        c.gridwidth = 2;
        c.weighty = 5;
        add(resetB,c);
        c.gridx++;
        c.gridx++;
        add(saveB,c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx++;
        c.gridx++;
        add(exitB,c);
        c.gridy++;
        c.gridx = 1;
        c.gridwidth = 1;
        add(fpsL,c);
        c.gridx++;
        add(generationL,c);
        c.gridx++;
        add(populationL,c);
        c.gridy++;
        c.gridx = 1;
        c.gridwidth = 1;
        add(fpsTF,c);
        c.gridx++;
        add(generationTF,c);
        c.gridx++;
        add(populationTF,c);
        c.gridy++;
        c.gridx = 0;
        c.gridwidth = 5;
        c.weighty = 50;
        add(messageP,c);
        
        
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
		fpsTF.setText("" + rate);
		populationTF.setText("" + sim.getPopulation());
		generationTF.setText("" + sim.getGeneration());
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
