package CS415;
import java.awt.Color;

import javax.swing.*;
import javax.swing.table.*;
import java.util.List;
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
	private final Color STATE_0 = Color.WHITE;
	private final Color STATE_1 = Color.BLACK;
	
	private List<Point> fillCells1, fillCells0;
	private Grid currentGrid;
	
	public Display(){
		super();
		int width = 50, height = 50;
		
		fillCells0 = new ArrayList<>(width * height);
		fillCells1 = new ArrayList<>(width * height);
		
		fillCells0.add(new Point(0,0));
		fillCells1.add(new Point(1,0));
		repaint();
	}
	
	public Display(Grid source){
		super();
		currentGrid = source;
		int width = source.getWidth(), height = source.getHeight();
		
		fillCells0 = new ArrayList<>(width * height);
		fillCells1 = new ArrayList<>(width * height);
		
		for (int row = 0; row < width; row++) {
			for (int column = 0; column < width; column++) {
				int state = source.getCell(row, column).getState();
				if (state == 0){
					fillCells0.add(new Point(row, column));
				}
				else if (state == 1) {
					fillCells1.add(new Point(row, column));
				}
			}
		}
		repaint();
	}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //draw the lists in the correct color
        colorCell(fillCells0, g, STATE_0);
        colorCell(fillCells1, g, STATE_1);
        
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
