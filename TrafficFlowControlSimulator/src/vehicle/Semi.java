package vehicle;

import java.awt.Color;
import java.awt.Graphics;

public class Semi extends Vehicle {
	
	public Semi(int newX, int newY) {
		super(newX, newY);
		width = 20; 
		height = 20; 
		speed = 4;
		carName = "semi";
	}

	public void drawVehicle(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
	}
	
}
