package solar;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlanetTest {

	@Test
	public void testGetCoordsAtInitialState() {
		Planet p1 = new Planet(10, 0, 0);
		double x = p1.getX();
		double y = p1.getY();
		Assertions.assertEquals(10, x, 0.0000001, "x coordinate");
		Assertions.assertEquals(0, y, 0.0000001, "y coordinate");
	}

	@Test
	public void testGetCoordsAtOrigin() {
		Planet p1 = new Planet(0, 0, 0);
		double x = p1.getX();
		double y = p1.getY();
		Assertions.assertEquals(0, x, 0.0000001, "x coordinate");
		Assertions.assertEquals(0, y, 0.0000001, "y coordinate");
	}

	@Test
	public void testGetCoordsAtInitialStateWithInitialAngle() {
		Planet p1 = new Planet(10, 90, 0);
		double x = p1.getX();
		double y = p1.getY();
		Assertions.assertEquals(0, x, 0.0000001, "x coordinate");
		Assertions.assertEquals(-10, y, 0.0000001, "y coordinate");
	}

	@Test
	public void testRunMovementForOneDegreePerDay() {
		Planet p1 = new Planet(10, 0, 1);
		p1.runMovementFor(BigDecimal.ONE);
		double x = p1.getX();// 10*cos -1 = 9,998476
		double y = p1.getY();// 10*sin -1 = -0,174524
		Assertions.assertEquals(9.998476, x, 0.000001, "x coordinate");
		Assertions.assertEquals(-0.174524, y, 0.000001, "y coordinate");
	}

	@Test
	public void testRunMovementReaching360degrees() {
		Planet p1 = new Planet(10, 359, 1);
		p1.runMovementFor(BigDecimal.ONE);
		double x = p1.getX();// 10*cos -360 = 10
		double y = p1.getY();// 10*sin -360 = 0
		Assertions.assertEquals(10, x, 0.000001, "x coordinate");
		Assertions.assertEquals(0, y, 0.000001, "y coordinate");

	}

	@Test
	public void testRunMovementReachingMinus360degrees() {
		Planet p1 = new Planet(10, -359, -1);
		p1.runMovementFor(BigDecimal.ONE);
		double x = p1.getX();// 10*cos 360 = 10
		double y = p1.getY();// 10*sin 360 = 0
		Assertions.assertEquals(10, x, 0.000001, "x coordinate");
		Assertions.assertEquals(0, y, 0.000001, "y coordinate");
	}

	@Test
	public void testRunMovementOver360degrees() {
		Planet p1 = new Planet(10, 360, 1);
		p1.runMovementFor(BigDecimal.ONE);
		double x = p1.getX();// 10*cos -361 = 9,998476
		double y = p1.getY();// 10*sin -361 = -0,174524
		Assertions.assertEquals(9.998476, x, 0.000001, "x coordinate");
		Assertions.assertEquals(-0.174524, y, 0.000001, "y coordinate");
	}

	@Test
	public void testRunMovementBelowMinus360degrees() {
		Planet p1 = new Planet(10, -360, -1);
		p1.runMovementFor(BigDecimal.ONE);
		double x = p1.getX();// 10*cos -361 = 9,998476
		double y = p1.getY();// 10*sin -361 = 0,174524
		Assertions.assertEquals(9.998476, x, 0.000001, "x coordinate");
		Assertions.assertEquals(0.174524, y, 0.000001, "y coordinate");
	}

	@Test
	public void testRunMovementForMinusOneDegreePerDay() {
		Planet p1 = new Planet(10, 0, -1);
		p1.runMovementFor(BigDecimal.ONE);
		double x = p1.getX();// 10*cos 1 = 9,998476
		double y = p1.getY();// 10*sin 1 = 0,174524
		Assertions.assertEquals(9.998476, x, 0.000001, "x coordinate");
		Assertions.assertEquals(0.174524, y, 0.000001, "y coordinate");
	}

}
