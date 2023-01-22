package vehicle;

import java.awt.Color;
import java.awt.Graphics;

public class Sedan extends Vehicle {
	
	public Sedan(int newX, int newY) {
		super(newX, newY);
		width = 20; 
		height = 20; 
		speed = 6;
		carName = "sedan";
	}

	public void drawVehicle(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, width, height);
	}
	
}
