package CS415;

public class Simulation {
	
	private Grid initialState, currentState, nextState;
	private IRuleSet ruleset;
	private int generation;
	
	public Simulation() {
		
		generation = 0;
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
		
		
		
	}
	
	public static void main(String args[]) {
		
		
	}
	
}
