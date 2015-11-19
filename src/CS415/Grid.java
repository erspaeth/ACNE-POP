package CS415;

import java.util.HashMap;
import java.util.Map;

public class Grid {
	
	protected int width, height;
	protected HashMap<Cell, Integer> liveCells;
	
	public Grid() {
		
		this(50, 50);
	}

	public Grid(int width, int height) {
		
		this.width = width;
		this.height = height;
		
		liveCells = new HashMap<Cell, Integer>();
	}
	
	public Grid(int[][] cells) {
		
		this.width = cells.length;
		this.height = cells[0].length;
		
		liveCells = new HashMap<Cell, Integer>();
		
		for(int i = 0; i < width; i++) {
			
			for(int j = 0; j < height; j++) {
				
				if(cells[i][j] > 0) {
					
					liveCells.put(new Cell(i,j), cells[i][j]);
				}
			}
		}	
	}
	
	public Grid(Grid other) {
		
		this(other.width, other.height);
		
		Cell temp;
		for( Map.Entry<Cell, Integer> entry : liveCells.entrySet()){ 
			
			temp = entry.getKey();
			liveCells.put( new Cell(temp), entry.getValue() );
		}
		
	}
	
	//TODO Define this constructor
	public Grid(Shape input){}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public void clear() {
		
		liveCells.clear();
	}
	
	public int getCellValue(int row, int col) {
		
		Integer v = liveCells.get( new Cell(row, col));
		
		return (v != null) ? v.intValue() : 0;
	}
	
	public void setCellValue(int row, int col, int val) {
		
		if(val != 0) {
			
			liveCells.put(new Cell(row, col), val);
		}
		
	}
	
	public int[][] toArray() {
		
		int[][] aValues = new int[width][height];
		Cell temp;
		
		for( Map.Entry<Cell, Integer> entry : liveCells.entrySet()){ 
			
			temp = entry.getKey();
			aValues[temp.getRow()][temp.getCol()] = entry.getValue();
			
		}

		return aValues;
	}
	
	public String toString() {
		
		String sReturn;
		StringBuffer sbBuffer = new StringBuffer(200);
		int[][] aValues = this.toArray();
		
		for(int i = 0; i < height; i++) {
			
			sbBuffer.append("\n");
			for(int j = 0; j < width; j++) {
				
				sbBuffer.append( aValues[i][j] ); 				
			}
		}
		
		sbBuffer.append("\n");
		sReturn = sbBuffer.toString();
		return sReturn;
	}
	
}


