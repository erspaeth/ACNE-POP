package CS415;

public class Simulation {

	private Grid startGrid, currentGrid;
	int iterations;
	
	public Simulation(Grid start, Grid current, int iter){
		startGrid = start;
		currentGrid = current;
		iterations = iter;
	}
	
	public Simulation(Grid fresh){
		startGrid = currentGrid = fresh;
		iterations = 0;
	}
	
	public Grid getCurrentGrid(){
		return currentGrid;
	}
}
