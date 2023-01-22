package vehicle;

import java.awt.Color;
import java.awt.Graphics;

public class Breakdown extends Vehicle {
	
	public Breakdown(int newX, int newY) {
		super(newX, newY);
		width = 20; 
		height = 20; 
	}

	public void drawVehicle(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}
	
}
