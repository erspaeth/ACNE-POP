package CS415;

public class Simulation {

	private Grid initialState, currentState, nextState;
	private IRuleSet ruleset;
	private int generation;
	
	public Simulation() {
		int bob[][] = new int[50][50];
		bob[4][5] = 1;
		bob[4][10] = 1;
		bob[5][5] = 1;
		bob[5][10] = 1;
		bob[6][5] = 1;
		bob[6][10] = 1;
		bob[7][3] = 1;
		bob[7][12] = 1;
		bob[8][3] = 1;
		bob[8][12] = 1;
		bob[9][4] = 1;
		bob[9][11] = 1;
		bob[10][5] = 1;
		bob[10][6] = 1;
		bob[10][9] = 1;
		bob[10][10] = 1;
		bob[11][7] = 1;
		bob[11][8] = 1;

		initialState = new Grid(bob);
		System.out.println(initialState);
		currentState = new Grid(initialState);
		System.out.println(currentState);
		nextState = new Grid();
		generation = 0;
		ruleset = new ConwaysGameOfLife();
	}
	
	public Simulation(IRuleSet ruleset, Shape shape) {
		
		this();
		initialState = new Grid(shape);
		currentState = new Grid(shape);
		nextState = new Grid();
	}
	
	public void step() {
		
		generation++;
		
		ruleset.applyRuleset(currentState, nextState);
		
		Grid temp = currentState;
		currentState = nextState;
		nextState = temp;
		nextState.clear();
		
	}
	
	public void reset() {
		
		currentState = new Grid(initialState);
		generation = 0;
		
	}
	
	public int getGeneration(){
		return generation;
	}
	
	public Grid getCurrentState(){
		return currentState;
	}
	
	public static void main(String args[]) {}
		
}
