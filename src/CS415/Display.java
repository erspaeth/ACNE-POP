package CS415;
import java.awt.Color;

import javax.swing.*;
import javax.swing.table.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.ArrayList;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Display extends JPanel{
	private final Color[] STATES = {Color.YELLOW, Color.BLACK};
	
	//private List<Point> fillCells1, fillCells0;
	private Grid currentGrid;
	private Vector<List<Point>> fillCells;
	
	public Display(){
		super();
		int width = 50, height = 50;
		
		/*fillCells0 = new ArrayList<>(width * height);
		fillCells1 = new ArrayList<>(width * height);
		
		fillCells1.add(new Point(1,0));
		fillCells1.add(new Point(2,0));
		fillCells1.add(new Point(1,1));
		fillCells1.add(new Point(2,1));
		fillCells1.add(new Point(1,2));
		fillCells1.add(new Point(2,2));
		fillCells1.add(new Point(1,3));
		fillCells1.add(new Point(2,3));*/
		
		fillCells.insertElementAt(new ArrayList<Point>(width * height), 1);
		fillCells.elementAt(1).add(new Point(1,1));
		
		repaint();
	}
	
	public Display(Grid source){
		super();
		currentGrid = source;
		int width = source.getWidth(), height = source.getHeight();
		
		
		
		//fillCells1 = new ArrayList<>(width * height); // for state 1 cells
		
		/*for (int row = 0; row < width; row++) {
			for (int column = 0; column < width; column++) {
				int state = source.getCell(row, column).getState();
				if (state == 0){
					fillCells0.add(new Point(row, column));
				}
				else if (state == 1) {
					fillCells1.add(new Point(row, column));
				}
			}
		}*/
		
		Set<Map.Entry<Cell, Integer>> liveCellMap = source.getLiveCells();
		
		Cell temp;
		int state;
		
		for (Map.Entry<Cell, Integer> entry : liveCellMap){
			//use these to get the values
			//entry.getKey();
			//entry.getValue();
			
			temp = entry.getKey();
			state = entry.getValue();
			
			if (fillCells.elementAt(state).isEmpty()) {
				fillCells.insertElementAt(new ArrayList<Point>(width * height), state);
			}

			fillCells.elementAt(state).add(new Point(temp.getRow(), temp.getCol()));
			
		}
		
		repaint();
	}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //draw the lists in the correct color
        g.drawRect(10, 10, 500, 500);
        g.setColor(STATES[0]);
        g.fillRect(10, 10, 500, 500); //set background to dead color
        
        //iterate over vector of points to color
        List<Point> temp;
        for (int i = 1; i < 10; i++){
        	temp = fillCells.elementAt(i);
        	if (temp != null){
        		colorCell(temp, g, STATES[i]);
        	}
        }
        
        
        //draw grid lines
        g.setColor(Color.blue);
        g.drawRect(10, 10, 500, 500);

        for (int i = 10; i <= 500; i += 10) {
            g.drawLine(i, 10, i, 510);
        }

        for (int i = 10; i <= 500; i += 10) {
            g.drawLine(10, i, 510, i);
        }
        
    }

    private void colorCell(List<Point> fillCells, Graphics g, Color color){
    	for (Point fillCell : fillCells) {
            int cellX = 10 + (fillCell.x * 10);
            int cellY = 10 + (fillCell.y * 10);
            g.setColor(color);
            g.fillRect(cellX, cellY, 10, 10);
        }
    }
    
    
	
	/*
	private JTable display;
	
	public Display(Grid source){
		int width = source.getWidth(), height = source.getHeight();
		display = new JTable(width, height);
		for (int row = 0; row < width; row++) {
			for (int column = 0; column < width; column++) {
				JLabel newL = new JLabel();
				display.add(newL);
			}
		}
		this.add(display);
	}

	public void draw(Grid source){
		
		int width = source.getWidth(), height = source.getHeight();
		
		JTable altered = new JTable(width, height);
		for (int row = 0; row < width; row++) {
			for (int column = 0; column < width; column++) {
				JLabel newL = new JLabel();
				altered.add(newL);
			}
		}
		Cell current;
		
		// for each cell in grid, set the background color of the cell to the
		// corresponding color
		for (int row = 0; row < source.getWidth(); row++) {
			for (int column = 0; column < source.getHeight(); column++) {
				JLabel tableCell = (JLabel)altered.getComponentAt(row, height);
				current = source.getCell(row, column);
				switch (current.getState()) {
				case 0:
					tableCell.setBackground(STATE_0);
					break;
				case 1:
					tableCell.setBackground(STATE_1);
					break;
				default:
					break;
				}
			}
		}
		this.remove(display);
		display = altered;
		this.add(display);
	}*/
	
}
