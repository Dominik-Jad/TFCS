package simulator;
import vehicle.Vehicle;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Road extends JPanel{
	
	final int laneHeight = 120;
	final int roadWidth = 1920;
	ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
	int carCount = 0; 
	int speedLimit = 10; 
	int blockedLane = 0;
	
	public Road() { 
		super();
	}
	
	public void addVehicle(Vehicle v) {
		cars.add(v);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.white);
		for(int i = laneHeight; i < laneHeight * 4; i = i + laneHeight) {// lane 
			for(int j = 0; j < getWidth(); j = j+80) { // line 
				g.fillRect(j, i, 30, 5);
			}
		}
		for(int i = 0; i < cars.size(); i++) {
			cars.get(i).drawVehicle(g);
		}
		
	}

	public void step() {
		for (int i = 0; i <cars.size(); i++) {
			Vehicle v = cars.get(i);			
			if (speedLimit < v.getSpeed()) {
				v.setSpeed(speedLimit);
			} else if (speedLimit > v.getSpeed()){
				if (v. getCarName() == "sedan") {
					for(int j = v.getSpeed(); j <= speedLimit; j++) {
						v.setSpeed(j);
					}				
				}
				if (v.getCarName() == "SUV") {
					for(int j = v.getSpeed(); j <= speedLimit; j++) {
						if(v.getSpeed() <= 5) {
						v.setSpeed(j);
						}
					}
				}
				if (v.getCarName() ==  "semi") {
					for(int j = v.getSpeed(); j <= speedLimit; j++) {
						if(v.getSpeed() <= 4) {
						v.setSpeed(j);
						}
					}
				}
			}
				
			if (laneClear(v.getX(),v.getY(), v) == true && v.getY() > 50 && laneBlocked(v) == false) { // if possible go back to the left lane 
				v.setY(v.getY() -  laneHeight);
				v.setX(v.getX() + v.getSpeed());				
			}	
			if(collision(v.getX()+ v.getSpeed(), v.getY(),v) == false) { // there is no collision 
				v.setX(v.getX() + v.getSpeed());
				if (v.getX() > roadWidth) {
					if(collision(0, v.getY(),v) == false) {
						v.setX(0);
						carCount++;
					}
				}
				
			}			
			else { // car ahead 
				slowDown(v);			
				if((v.getY() < 40 + 3 * laneHeight) && (collision(v.getX(), v.getY() + laneHeight, v) == false) && v.getY() + 120 < 281) {// switch to right lane 
					v.setY(v.getY() + laneHeight); 
					if (v. getCarName() == "sedan") {
						v.setSpeed(6);
					}
					if (v.getCarName() == "SUV") {
						v.setSpeed(4);
					}
					if (v.getCarName() ==  "semi") {
						v.setSpeed(2);
					}
				}			
			}	
			
		}
	}
	
	public boolean collision (int x, int y, Vehicle v) {
		for (int i = 0; i < cars.size(); i++) {
			Vehicle u = cars.get(i);
			if(y == u.getY() && x < u.getX() + u.getWidth() && x + v.getWidth() > u.getX()) {// my left to the left of his right side // my right to the right of his left side
				if (u.equals(v) == false) { // if the vehicle checking is not current vehicle. 
					return true; 
				}
			}
		}
		return false;
	}
	public boolean laneClear (int x, int y, Vehicle v) {
		for (int i = 0; i < cars.size(); i++) {
			Vehicle u = cars.get(i);
			if(y - laneHeight == u.getY() && x > u.getX() && x < u.getX() + u.getWidth()) {
				if (u.equals(v) == false) { // if the vehicle checking is not current vehicle. 
					return false; 
				}
			}
		}
		return true;
	}
	
	public void slowDown(Vehicle v) {
		for (int i = 0; i < cars.size(); i++) {
			Vehicle u = cars.get(i);
			if(v.getX() + v.getWidth() == u.getX() - 20 && v.getY() == u.getY()){				
				if(u.equals(v) == false) {
					if(v.getY() + laneHeight != u.getY() && v.getX() > u.getX() && v.getX() < u.getX() + u.getWidth()) {
						v.setY(v.getY() + 120);
					}
					else { 
						v.setSpeed(u.getSpeed());					
					}
				}
			}
		}
	}
	
	public boolean laneBlocked(Vehicle v) {
		if(blockedLane == 1  ) {
			if(v.getY() -120 == 40 || v.getY() == 40) {
				return true;
			}
			
		}
		if(blockedLane == 2  ) {
			if(v.getY() -120 == 160) {
				return true;
			}
		}
		return false; 
	}
	public void setLimit(int limit) {
		speedLimit = limit; 
	}
	public int getCarCount() {
		return carCount;
	}
	
	public void resetCarCount() {
		carCount = 0;
	}
}

