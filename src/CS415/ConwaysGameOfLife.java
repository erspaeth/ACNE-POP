package CS415;

import java.awt.Color;

public class ConwaysGameOfLife extends RuleSet {
	
	public static int NUMBER_OF_STATES = 2;
	public static String RULE_NAME = "Conway's Game of Live";
	public static String DESCRIPTION = "blah blah blah";
	public static Color[] colors = {Color.GREEN, Color.RED};

	public ConwaysGameOfLife() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void applyRuleset(Grid input, Grid target) {
		
		int width = input.getWidth();
		int height = input.getHeight();
		
		if(width != target.getWidth() || height != target.getHeight()) {
			// throw exception
		}
		
		target.clear();
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				
				int count = 0;
				int currentState = input.getCellValue(i, j);
				
				for(int m = i-1; m <= i+1; m++) {
					for(int n = j-1; n <= j+1; n++) {
						
						if((m!=i) || (n!=j)) {
							

							count += input.getCellValue(mod(m, width), mod(n, height));
							

						}
						
					}
				}
				
				if(currentState == 1) {
					if(count == 2 || count == 3) {
						target.setCellValue(i, j, 1);
					}
					
				}
				else {
					if(count == 3) {
						target.setCellValue(i, j, 1);
					}
				}
				
			}
		}
	}
	
	private int mod(int i, int divisor) {
		i = i%divisor;
		if(i < 0) {
			
			i += divisor;
		}
		return i;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return RULE_NAME;
	}

	@Override
	public int getNumberOfStates() {
		// TODO Auto-generated method stub
		return NUMBER_OF_STATES;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return DESCRIPTION;
	}

	@Override
	public Color[] getColorPallette() {
		// TODO Auto-generated method stub
		return colors;
	}

}
