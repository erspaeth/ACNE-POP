package CS415;

import java.awt.Color;

public class LangtonsAnt extends RuleSet {
	
	public static int NUMBER_OF_STATES = 10;
	public static String RULE_NAME = "Langton's Ant";
	public static String DESCRIPTION = "This one does some stuff";
	public static Color[] colors = {
							Color.DARK_GRAY, Color.GRAY,
							Color.RED, Color.RED,
							Color.RED, Color.RED, 
							Color.RED, Color.RED,
							Color.RED, Color.RED
							};

	private Cell ant;
	
	public LangtonsAnt() {
		ant = null;
	}

	@Override
	public void applyRuleset(Grid input, Grid target) {
		
		if(ant == null) {
			findAnt(input);
		}
		
		// copy cells from input into the target
		Grid.copyLiveCells(input, target);
		
		int antValue= input.getCellValue(ant) - 2;
		
		
		// The cell beneath the ant is 'off'
		if(antValue < 4){
			
			// Set current cell to 'on'
			target.setCellValue(ant, 1);
		}
		else {
			
			// The cell beneath the ant is 'on'
			// Set cell to 'off'
			target.setCellValue(ant, 0);
		}
		
		Cell nextAnt;
		int nextState;
		
		switch(antValue % 4) {
		
		case 0: // facing up
			
			nextAnt = new Cell(ant.getRow(), ant.getCol() + 1);
			nextState = (input.getCellValue(nextAnt) == 0) ? 5 : 7;
			break;
			
		case 1: // facing right
			
			nextAnt = new Cell(ant.getRow() + 1, ant.getCol()); 
			nextState = (input.getCellValue(nextAnt) == 0) ? 2 : 8;
			break;
			
		case 2: // facing down
			
			nextAnt = new Cell(ant.getRow(), ant.getCol() - 1);
			nextState = (input.getCellValue(nextAnt) == 0) ? 3 : 9;
			break;
			
		case 3: // facing left
			
			nextAnt = new Cell(ant.getRow() - 1, ant.getCol());
			nextState = (input.getCellValue(nextAnt) == 0) ? 4 : 6;
			break;
			
		default:
			// We shouldn't ever actually get in here but we need to explicity
			// set these vars or the compiler complains
			nextAnt = null;
			nextState = 0;
			
			break;
		}
		
		target.setCellValue(nextAnt, nextState);
		ant = nextAnt;
		
	}

	private void findAnt(Grid g) {
		
		for(int i = 0; i < g.width; i++) {
			for(int j = 0; j < g.height; j++) {
				
				int state = g.getCellValue(i, j);
				
				if(state > 1) {
					ant = new Cell(i, j);
					break;					// Break out of inner loop
				}
			}
			
			if(ant != null) {
				break; 						// Break out of outer loop
			}
		}
	}
	
	
	@Override
	public String toString() {
		
		return RULE_NAME;
	}

	@Override
	public int getNumberOfStates() {
		
		return NUMBER_OF_STATES;
	}

	@Override
	public String getDescription() {
		
		return DESCRIPTION;
	}

	@Override
	public Color[] getColorPallette() {
		
		return colors;
	}

}
