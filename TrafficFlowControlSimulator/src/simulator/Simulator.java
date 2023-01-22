package simulator;
import vehicle.Breakdown;
import vehicle.SUV;
import vehicle.Sedan;
import vehicle.Semi;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Simulator implements ActionListener, Runnable {	
	JFrame frame = new JFrame("Traffic Flow Control Simulator");
	Road road = new Road();
	JButton start = new JButton("start");
	JButton stop = new JButton ("stop");
	
	String[] limits = { "30", "40", "50", "60", "70" };
	JComboBox limitList = new JComboBox(limits);

	JLabel carcount = new JLabel("cars: ");
	Container bottom = new Container();
	
	JButton semi = new JButton ("semi");
	JButton suv = new JButton ("SUV");
	JButton sedan = new JButton ("Sedan");
	JButton blockage = new JButton ("Road Block");
	Container left = new Container();
	
	boolean running = false;
	int carCount = 0; 
	long startTime = 0;
	
	public Simulator() throws Exception{		 
       frame.setSize(1920, 500);
       frame.setLayout(new BorderLayout());
       frame.add(road, BorderLayout.CENTER);
  
       bottom.setLayout(new GridLayout(1,4));
       bottom.add(start);
       start.addActionListener(this);
       bottom.add(stop);
       stop.addActionListener(this);
       bottom.add(carcount);
       bottom.add(limitList);
       limitList.setSelectedIndex(4);
       limitList.addActionListener(this);
       frame.add(bottom, BorderLayout.SOUTH);
     
       left.setLayout(new GridLayout(4,1));
       left.add(semi);
       semi.addActionListener(this);
       left.add(suv);
       suv.addActionListener(this);
       left.add(sedan);
       sedan.addActionListener(this);
       left.add(blockage);
       blockage.addActionListener(this);
       frame.add(left, BorderLayout.WEST);
      
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
       

       frame.repaint();
	}
    public static void main(String args[]) throws Exception {
    	 new Simulator();
         }
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(start)) {
			if(running == false) {
				running = true;
				road.resetCarCount();
				Thread t = new Thread(this);
				t.start();
			}
		}
		if (e.getSource().equals(stop)) {
			running = false;
		}	
		if (e.getSource().equals(limitList)) {
			if (limitList.getSelectedItem() == "30") {
				road.setLimit(3);
			}
			if (limitList.getSelectedItem() == "40") {
				road.setLimit(4);
			}
			if (limitList.getSelectedItem() == "50") {
				road.setLimit(5);
			}
			if (limitList.getSelectedItem() == "60") {
				road.setLimit(6);
			}
			if (limitList.getSelectedItem() == "70") {
				road.setLimit(7);
			}
		}	
		
		if (e.getSource().equals(semi)) {
			Semi semi = new Semi(0,30);
			road.addVehicle(semi);
			for (int x = 0; x < road.roadWidth; x = x + 20) {
				for(int y = 40; y < 360; y = y + 120) {
					semi.setX(x);
					semi.setY(y);
					if(road.collision(x, y, semi) == false) {
						frame.repaint();
						return;
					}
				}
			}
		}
		if (e.getSource().equals(suv)) {
			SUV suv = new SUV(0,30);
			road.addVehicle(suv);
			for (int x = 0; x < road.roadWidth; x = x + 20) {
				for(int y = 40; y < 360; y = y + 120) {
					suv.setX(x);
					suv.setY(y);
					if(road.collision(x, y, suv) == false) {
						frame.repaint();
						return;
					}
				}
			}
		}
		if (e.getSource().equals(sedan)) {
			Sedan sedan = new Sedan(0,30);
			road.addVehicle(sedan);
			for (int x = 0; x < road.roadWidth; x = x + 20) {
				for(int y = 40; y < 360; y = y + 120) {					
					sedan.setX(x);
					sedan.setY(y);					
					if(road.collision(x, y, sedan) == false) {						
						frame.repaint();				
						return;
					}
				}
			}
		}
		
		if(e.getSource().equals(blockage)) {
			Breakdown bd = new Breakdown(700,40);
			road.blockedLane += 1; 
			road.addVehicle(bd);
			frame.repaint();
		}
	}
	@Override
	public void run() {
		while(running == true) {
			road.step();
			carCount = road.getCarCount();
			carcount.setText("cars: " + carCount);
			frame.repaint();
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}