package solar;

/**
 * Represent a Shape2D formed by 3 points. Answer if it is a triangle, a line, and if include another point inside.
 *
 */
public class Shape {

	private Point v1,v2,v3;
	private Boolean isLine; 
	
	public static double PRECISION = 0.01;
	
	public Shape(Point v1,Point v2,Point v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		isLine = Math.abs(scalarProductForVectorPoints(v1, v2, v3))<PRECISION;
	}
	
	public boolean isTriangle() {
		return !isLine();
	}
	
	public boolean isLine() {
		return isLine;
	}
	
	private double scalarProductForVectorPoints (Point p1, Point p2, Point p3) {
	    return (p1.getX() - p3.getX()) * (p2.getY() - p3.getY()) - (p2.getX() - p3.getX()) * (p1.getY() - p3.getY());
	}
	
	public boolean isTriangleAndIncludePoint(Point anotherPoint) {
		if(isLine()) {
			return false;
		}
	    boolean b1 = scalarProductForVectorPoints(anotherPoint, v1, v2) < 0;
	    boolean b2 = scalarProductForVectorPoints(anotherPoint, v2, v3) < 0;
	    boolean b3 = scalarProductForVectorPoints(anotherPoint, v3, v1) < 0;
	    return (b1 == b2) && (b2 == b3);
	}
	
	public double getPerimeter() {
		return v1.getDistanceTo(v2)+v2.getDistanceTo(v3)+v3.getDistanceTo(v1);
	}

	public boolean isLineAndIncludePoint(Point anotherPoint) {
		if(!isLine()) {
			return false;
		}
		return Math.abs(scalarProductForVectorPoints(anotherPoint, v1, v2)) < PRECISION;
	}
	
}
