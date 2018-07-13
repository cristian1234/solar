package solar.geometry;

import solar.Point;
import solar.Shape;

public class ShapeBuilder {

	public Shape buildWith(Point p1,Point p2,Point p3) {
		return new Shape(p1,p2,p3);
	}
	
}
