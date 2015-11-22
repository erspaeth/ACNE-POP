package CS415;
import java.awt.Color;

import javax.swing.*;
import javax.swing.table.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.ArrayList;
import java.util.HashMap;
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
	private Color[] STATES;
	private final int UNIT_SIZE = 10;
	
	private Grid currentGrid;
	private HashMap<Integer, List<Point>> fillCells;
	private int width, height;
	
	public Display(Color[] colors){
		super();
		STATES = colors;
		width = 50;
		height = 50;
		
		//for (int = 0;)
		fillCells = new HashMap<Integer, List<Point>>();
		
		fillCells.put(1, new ArrayList<Point>(width * height));
		fillCells.get(1).add(new Point(1,1));
		fillCells.put(2, new ArrayList<Point>(width * height));
		fillCells.get(2).add(new Point(2,1));
		fillCells.put(3, new ArrayList<Point>(width * height));
		fillCells.get(3).add(new Point(3,1));
		
		repaint();
	}
	
	public Display(Grid source, Color[] colors){
		super();
		STATES = colors;
		width = source.getWidth();
		height = source.getHeight();
		copyGrid(source);
		
		repaint();
	}
	
	public void draw(Grid newGrid){
		copyGrid(newGrid);
		repaint();
	}
	
	private void copyGrid(Grid source){
		currentGrid = source;
		width = source.getWidth(); 
		height = source.getHeight();
		fillCells = new HashMap<Integer, List<Point>>();
		Set<Map.Entry<Cell, Integer>> liveCellMap = source.getLiveCells();
		
		Cell temp;
		int state;
		
		for (Map.Entry<Cell, Integer> entry : liveCellMap){
			
			temp = entry.getKey();
			state = entry.getValue();
			
			if (fillCells.get(state) == null) {
				fillCells.put(state, new ArrayList<Point>(width * height));
			}

			fillCells.get(state).add(new Point(temp.getCol(), temp.getRow()));
			
		}
	}
	
	public int getWidth(){
		return width * UNIT_SIZE;
	}
	
	public int getHeight(){
		return height * UNIT_SIZE;
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //draw the lists in the correct color
        g.drawRect(0, 0, (width+1) * UNIT_SIZE, height * UNIT_SIZE);
        
        g.setColor(STATES[0]);
        g.fillRect(0, 0, width * UNIT_SIZE, height * UNIT_SIZE); //set default background to dead color
        
        //draw the living cells
        Set<Map.Entry<Integer, List<Point>>> liveCells = fillCells.entrySet();
        Integer state;
        for (Map.Entry<Integer, List<Point>> entry : liveCells){
        	state = entry.getKey();
        	if (state < STATES.length && state >= 0){
        		colorCell(entry.getValue(), g, STATES[state]);
        	}
        }
        
        //draw grid lines
        g.setColor(Color.blue);
        g.drawRect(0, 0, width * UNIT_SIZE, height * UNIT_SIZE);

        for (int i = 0; i <= width * UNIT_SIZE; i += UNIT_SIZE) {
            g.drawLine(i, 0, i, (height+1) * UNIT_SIZE);
        }

        for (int i = 0; i <= height * UNIT_SIZE; i += UNIT_SIZE) {
            g.drawLine(0, i, (width+1) * UNIT_SIZE, i);
        }
        
    }

    private void colorCell(List<Point> fillCells, Graphics g, Color color){
    	for (Point fillCell : fillCells) {
            int cellX = UNIT_SIZE + ((fillCell.x - 1) * UNIT_SIZE);
            int cellY = UNIT_SIZE + ((fillCell.y - 1)* UNIT_SIZE);
            g.setColor(color);
            g.fillRect(cellX, cellY, UNIT_SIZE, UNIT_SIZE);
        }
    }
	
}
