package CS415;
import javax.swing.*;
import java.awt.*;

public class SimControl extends JFrame{

	Display display;
	
	public SimControl(Simulation sim){
		
		//get current grid from simulation
		Grid current = sim.getCurrentGrid();
		
		//create display from grid
		display = new Display(current);
		
		//get iterations
		
		setTitle("Grid Display");
        setSize(700, 700);
        setLayout(new BorderLayout());
        add(display, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
	}
	
}
