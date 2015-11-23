package CS415;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
	
	public Grid(int width, int height, int numberOfStates, int popPercentage) {
		
		this.width = width;
		this.height = height;
		
		liveCells = new HashMap<Cell, Integer>();
		Random rand = new Random();
		
		for(int i = 0; i < width; i++) {
			
			for(int j = 0; j < height; j++) {
					if(rand.nextInt(100) < popPercentage){
						liveCells.put(new Cell(i,j), rand.nextInt(numberOfStates-1) + 1);
					}
			}
		}
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
		for( Map.Entry<Cell, Integer> entry : other.getLiveCells()){ 
			
			temp = entry.getKey();
			liveCells.put( new Cell(temp), entry.getValue() );
		}
		
	}
	
	
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
		
		return getCellValue(new Cell(row, col));
	}
	
	public int getCellValue(Cell target) {
		
		Integer v = liveCells.get(target);
		return (v != null) ? v.intValue() : 0;
	}
	
	public void setCellValue(int row, int col, int val) {
		
		liveCells.put(new Cell(row, col), new Integer(val));
	}
	
	public void setCellValue(Cell cell, Integer val) {
		
		if(val == 0) {
			
			liveCells.remove(cell);
		}
		else {
			
			liveCells.put(cell,  val);
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
	
	public Set<Map.Entry<Cell, Integer>> getLiveCells() {
		
		return liveCells.entrySet();
	}
	
	public int getPopulation(){
		return liveCells.size();
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
	
	public static void copyLiveCells(Grid source, Grid target) {
		
		target.clear();
		
		Set<Map.Entry<Cell, Integer>> cells = source.getLiveCells();
		
		Cell temp;
		Integer value;
		for( Map.Entry<Cell, Integer> entry : cells){ 
			
			temp = entry.getKey();
			value = new Integer(entry.getValue());
			target.setCellValue(temp, value);
		}
	}
	
}


