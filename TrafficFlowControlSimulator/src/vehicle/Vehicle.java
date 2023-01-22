package vehicle;

import java.awt.Graphics;

public class Vehicle {
	int x;
	int y; 
	int width = 0; 
	int height = 0; 
	int speed = 0; 
	String carName = "";
	
	public Vehicle(int newX, int newY) {
		x = newX;
		y = newY;
	}
	public void drawVehicle(Graphics g) {
		
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int newX){
		x = newX;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getY() {
		return y; 
	}
	public void setY(int newY) {
		y= newY;
	}
	public int getWidth() {
		return width;
	}
	public String getCarName() {
		return carName;
	}
	public int getHeight() {
		return height; 
	}
	
	public void setSpeed(int newSpeed) {
		speed = newSpeed; 
	}
}
