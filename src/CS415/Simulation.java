package CS415;

public class Simulation {

	private Grid initialState, currentState, nextState;
	private IRuleSet ruleset;
	private int generation;
	
	public Simulation() {
		initialState = new Grid(50,50,2,50);
		currentState = new Grid(initialState);
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
	
	public int getPopulation(){
		return currentState.getPopulation();
	}
	
	public Grid getCurrentState(){
		return currentState;
	}
	
	public static void main(String args[]) {
		
		Grid g = new Grid(10, 10);
		Grid g2 = new Grid(10, 10);
		
		IRuleSet rules = new ConwaysGameOfLife();
		
		g.setCellValue(2, 2, 1);
		g.setCellValue(3, 3, 1);
		
		g.setCellValue(3, 4, 1);
		g.setCellValue(2, 4, 1);
		g.setCellValue(1, 4, 1);
		
		System.out.println(g.toString());
		
		g2.clear();
		rules.applyRuleset(g, g2);
		System.out.println(g2.toString());
		
		g.clear();
		rules.applyRuleset(g2, g);
		System.out.println(g.toString());
		
		g2.clear();
		rules.applyRuleset(g, g2);
		System.out.println(g2.toString());
		
		g.clear();
		rules.applyRuleset(g2, g);
		System.out.println(g.toString());
		
		g2.clear();
		rules.applyRuleset(g, g2);
		System.out.println(g2.toString());
		
		g.clear();
		rules.applyRuleset(g2, g);
		System.out.println(g.toString());
		
		
	}
		
}
