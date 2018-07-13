package solar.mocks;

import solar.Point;

public class DummyPoint extends Point {

	private double x,y;
	
	public DummyPoint(double x,double y ) {
		this.x = x;
		this.y= y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
}
