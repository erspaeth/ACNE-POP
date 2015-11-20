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
		bob[7][3] = 2;
		bob[7][12] = 2;
		bob[8][3] = 2;
		bob[8][12] = 2;
		bob[9][4] = 3;
		bob[9][11] = 3;
		bob[10][5] = 3;
		bob[10][6] = 3;
		bob[10][9] = 3;
		bob[10][10] = 3;
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
