package CS415;

public class Cell {
	
	private int row;
	private int col;
	
	public Cell() {
		
		row = 0;
		col = 0;
	}
	
	public Cell(int row, int col){
		
		this.row = row;
		this.col = col;
	}
	
	public Cell(Cell other) {
		
		this.row = other.row;
		this.col = other.col;
	}
	
	public int getRow() {
		
		return row;
	}
	
	public int getCol() {
		
		return col;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
	
	
	

}
