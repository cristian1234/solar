package solar;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import solar.mocks.DummyPoint;

public class ShapeTest {

	@BeforeAll
	public static void setupPrecision() {
		Shape.PRECISION = 0.00001;
	}
	
	@AfterAll
	public static void resetPrecision() {
		Shape.PRECISION = 0.01;
	}
	
	@Test
	public void testIsTriangle() {
		Point p1 = new DummyPoint(2, 2);
		Point p2 = new DummyPoint(3, 3);
		Point p3 = new DummyPoint(5, -6);
		Shape s = new Shape(p1,p2,p3);
		Assertions.assertTrue(s.isTriangle(),"is triangle");	
	}

	@Test
	public void testIsLine3ColinearPointsInDistinctCuadrant() {
		Point p1 = new DummyPoint(4, 2);
		Point p2 = new DummyPoint(-1, -3);
		Point p3 = new DummyPoint(12, 10);
		Shape s = new Shape(p1,p2,p3);
		Assertions.assertTrue(s.isLine(),"is line");
	}

	@Test
	public void testIsLine3ColinearPoints() {
		Point p1 = new DummyPoint(4, 2);
		Point p2 = new DummyPoint(5, 3);
		Point p3 = new DummyPoint(12, 10);
		Shape s = new Shape(p1,p2,p3);
		Assertions.assertTrue(s.isLine(),"is line");
	}

	
	@Test
	public void testIsTriangleAndIncludePoint() {
		Point p1 = new DummyPoint(2, 2);
		Point p2 = new DummyPoint(3, 3);
		Point p3 = new DummyPoint(5, -6);
		Shape s = new Shape(p1,p2,p3);
		Point p = new DummyPoint(3, 2);
		Assertions.assertTrue(s.isTriangleAndIncludePoint(p),"include point PRESISION:" + Shape.PRECISION);
	}

	@Test
	public void testGetPerimeterTrivial() {
		Point p1 = new DummyPoint(2, 2);
		Point p2 = new DummyPoint(3, 3);
		Point p3 = new DummyPoint(5, -6);
		//sqrt((3-2)^2+(3-2)^2)+sqrt((5-3)^2+(-6+3)^2)+sqrt((5-2)^2+(-6+2)^2)=sqrt(2)+sqrt(4+81)+sqrt(9+64)=19.17776176
		Shape s = new Shape(p1,p2,p3);
		Assertions.assertEquals(19.17776176,s.getPerimeter(),0.00000001,"calculate perimeter");
	}

	@Test
	public void testGetPerimeterRectangle() {
		Point p1 = new DummyPoint(0, 4);
		Point p2 = new DummyPoint(4, 0);
		Point p3 = new DummyPoint(4,4);
		//sqrt((3-2)^2+(3-2)^2)+sqrt((5-3)^2+(-6+3)^2)+sqrt((5-2)^2+(-6+2)^2)=sqrt(32)+4+4=13.65685424
		Shape s = new Shape(p1,p2,p3);
		Assertions.assertEquals(13.65685424,s.getPerimeter(),0.00000001,"calculate perimeter");
	}
	
	@Test
	public void testIsLineAndIncludePoint() {
		Point p1 = new DummyPoint(4, 2);
		Point p2 = new DummyPoint(5, 3);
		Point p3 = new DummyPoint(12, 10);
		Shape s = new Shape(p1,p2,p3);
		Point p = new DummyPoint(-18,-20);
		Assertions.assertTrue(s.isLineAndIncludePoint(p),"is line and include point");
	}

	@Test
	public void testIsLineAndDoesntIncludePoint() {
		Point p1 = new DummyPoint(0, 3.162277);
		Point p2 = new DummyPoint(2.236067, 3.162277);
		Point p3 = new DummyPoint(3.872983, 3.162277);
		Shape s = new Shape(p1,p2,p3);
		Point p = new DummyPoint(0,0);
		Assertions.assertTrue(s.isLine(),"is line");
		Assertions.assertFalse(s.isLineAndIncludePoint(p),"is line and include point");
	}

	
}
