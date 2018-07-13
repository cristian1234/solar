package solar;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import solar.mocks.DummyPoint;

class PointTest {
	
	@Test
	void testGetDistanceToTrivial() {
		Point p1 = new DummyPoint(2,3);
		Point p2 = new DummyPoint(4,5);
		double distance = p1.getDistanceTo(p2);//sqrt((2-4)^2+(3-5)^2) = 2.828427....
		Assertions.assertEquals(2.828427, distance, 0.000001,"incorrect distance");
	}

	@Test
	public void testGetDistanceToSamePointReturnZero() {
		Point p1 = new DummyPoint(2,3);
		Point p2 = new DummyPoint(2,3);
		double distance = p1.getDistanceTo(p2);//sqrt((2-2)^2+(3-3)^2) = 0....
		Assertions.assertEquals(0, distance, 0.000001,"incorrect distance");
	}

	@Test
	public void testGetDistanceToXAxis() {
		Point p1 = new DummyPoint(2,0);
		Point p2 = new DummyPoint(4,0);
		double distance = p1.getDistanceTo(p2);//sqrt((2-4)^2+0^2) = 2....
		Assertions.assertEquals(2, distance, 0.000001,"incorrect distance");
	}

	
}
