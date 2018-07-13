package solar;

/**
 * 2D point abstraction, it has x,y coordinates and can calculate distance to another point
 *
 */
public abstract class Point {
	
	public abstract double getX();
	
	public abstract double getY();
	
	public double getDistanceTo(Point anotherPoint) {
		return Math.hypot(getX()-anotherPoint.getX(), getY()-anotherPoint.getY());
	}
	
}
