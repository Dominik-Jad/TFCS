package vehicle;

import java.awt.Color;
import java.awt.Graphics;

public class SUV extends Vehicle {
	
	public SUV(int newX, int newY) {
		super(newX, newY);
		width = 20; 
		height = 20; 
		speed = 5;
		carName = "SUV";
	}

	public void drawVehicle(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}
	
}
