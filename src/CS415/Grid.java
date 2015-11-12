package CS415;

public class Grid {
	
	private boolean doesWrap;
	private Cell[][] storedCells;
	private int width, height;
	
	public Grid(int width, int height) {
		
		this.width = width;
		this.height = height;
		
		storedCells = new Cell[width][height];
		
		for(int i = 0; i < width; i++) {
			
			for(int j = 0; j < height; j++) {
				
				storedCells[i][j] = new Cell();
			}
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
	
	public Cell getCell(int row, int col) {
		
		
		return storedCells[row][col];
	}
	
	public String toString() {
		
		String sReturn;
		
		StringBuffer sbBuffer = new StringBuffer(200);
		
		for(int i = 0; i < height; i++) {
			
			sbBuffer.append("\n");
			for(int j = 0; j < width; j++) {
				
				sbBuffer.append( storedCells[i][j].getState() ); 				
			}
		}
		
		sbBuffer.append("\n");
		sReturn = sbBuffer.toString();
		return sReturn;
	}
	
	
	
	
    public static void main(String[] args) {
    	
    	Grid g = new Grid(10, 10);
    	
    	Cell c;
    	
    	for(int i = 0; i < 10; i++) {
    		
    		c = g.getCell(i, 2);
    		c.setState(2);
    		c = g.getCell(i, 6);
    		c.setState(2);
    
    	}
    	System.out.println("hello");
        System.out.println(g.toString()); // Display the string.
    }
	
}


