package solar.mocks;

import solar.Point;
import solar.Shape;

public class MockShape extends Shape {

	double perimeter;
	boolean isLine;
	boolean isTriangle;
	boolean isTriangleAndIncludePoint;
	boolean isLineAndIncludePoint;
	
	public MockShape(Point v1, Point v2, Point v3) {
		super(v1, v2, v3);
	}
	
	public boolean isTriangle() {
		return isTriangle;
	}
	
	public boolean isLine() {
		return isLine;
	}
	
	
	public boolean isTriangleAndIncludePoint(Point anotherPoint) {
		return isTriangleAndIncludePoint;
	}
	
	public double getPerimeter() {
		return perimeter;
	}

	public boolean isLineAndIncludePoint(Point anotherPoint) {
		return isLineAndIncludePoint;
	}

}
