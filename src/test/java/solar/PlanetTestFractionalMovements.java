package solar;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlanetTestFractionalMovements {

	private void movePlanet(Planet p) {
		for (int i = 0; i < 10; i++) {
			p.runMovementFor(BigDecimal.ONE.divide(BigDecimal.TEN));
		}
	}

	@Test
	public void testRunMovementForOneDegreePerDay() {
		Planet p1 = new Planet(10, 0, 1);
		movePlanet(p1);
		double x = p1.getX();// 10*cos -1 = 9,998476
		double y = p1.getY();// 10*sin -1 = -0,174524
		Assertions.assertEquals(9.998476, x, 0.000001, "x coordinate");
		Assertions.assertEquals(-0.174524, y, 0.000001, "y coordinate");
	}

	@Test
	public void testRunMovementReaching360degrees() {
		Planet p1 = new Planet(10, 359, 1);
		movePlanet(p1);
		double x = p1.getX();// 10*cos -360 = 10
		double y = p1.getY();// 10*sin -360 = 0
		Assertions.assertEquals(10, x, 0.000001, "x coordinate");
		Assertions.assertEquals(0, y, 0.000001, "y coordinate");

	}

	@Test
	public void testRunMovementReachingMinus360degrees() {
		Planet p1 = new Planet(10, -359, -1);
		movePlanet(p1);
		double x = p1.getX();// 10*cos 360 = 10
		double y = p1.getY();// 10*sin 360 = 0
		Assertions.assertEquals(10, x, 0.000001, "x coordinate");
		Assertions.assertEquals(0, y, 0.000001, "y coordinate");
	}

	@Test
	public void testRunMovementOver360degrees() {
		Planet p1 = new Planet(10, 360, 1);
		movePlanet(p1);
		double x = p1.getX();// 10*cos -361 = 9,998476
		double y = p1.getY();// 10*sin -361 = -0,174524
		Assertions.assertEquals(9.998476, x, 0.000001, "x coordinate");
		Assertions.assertEquals(-0.174524, y, 0.000001, "y coordinate");
	}

	@Test
	public void testRunMovementBelowMinus360degrees() {
		Planet p1 = new Planet(10, -360, -1);
		movePlanet(p1);
		double x = p1.getX();// 10*cos -361 = 9,998476
		double y = p1.getY();// 10*sin -361 = 0,174524
		Assertions.assertEquals(9.998476, x, 0.000001, "x coordinate");
		Assertions.assertEquals(0.174524, y, 0.000001, "y coordinate");
	}

	@Test
	public void testRunMovementForMinusOneDegreePerDay() {
		Planet p1 = new Planet(10, 0, -1);
		movePlanet(p1);
		double x = p1.getX();// 10*cos 1 = 9,998476
		double y = p1.getY();// 10*sin 1 = 0,174524
		Assertions.assertEquals(9.998476, x, 0.000001, "x coordinate");
		Assertions.assertEquals(0.174524, y, 0.000001, "y coordinate");
	}

}
