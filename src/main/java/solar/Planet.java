package solar;

import java.math.BigDecimal;

/**
 * Contains planet coordinates and compute its movement in the simulated orbit 
 *
 */
public class Planet extends Point {

	private double distanceToSun;
	private double speed;
	private double currentAngleInRadians;
	private double x;
	private double y;
	
	private double initialAngle;
	private BigDecimal elapsedDays;
	
	public Planet(double distancetoSun,double initialAngle,double speed) {
		if(distancetoSun < 0) {
			throw new IllegalArgumentException("distance to sun must be a positive value");
		}
		this.initialAngle = initialAngle;
		this.elapsedDays = BigDecimal.ZERO;
		
		this.distanceToSun = distancetoSun;
		this.speed = speed;
		runMovementFor(elapsedDays);
	}
	
	private void updateCartesianCoords() {
        x = distanceToSun * Math.cos(-currentAngleInRadians);
        y = distanceToSun * Math.sin(-currentAngleInRadians);		
	}	
	
	/**
	 * Translate this planet over its circular orbit given by the distance to sun and the traslation speed in degrees per day
	 * Positive speed is clockwise and negative speed is counter clockwise
	 * @param days
	 */
	public void runMovementFor(BigDecimal days) {
		if(BigDecimal.ZERO.compareTo(days)>0) {
			throw new IllegalArgumentException("days must be zero or a positive value");
		}
		elapsedDays = elapsedDays.add(days);
		
		double currentAngleInDegrees = elapsedDays.multiply(BigDecimal.valueOf(speed)).
				add(BigDecimal.valueOf(initialAngle)).doubleValue();
		
		this.currentAngleInRadians = Math.toRadians(currentAngleInDegrees); 
		
		updateCartesianCoords();
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}
	
	public double getCurrentAngle() {
		return Math.toDegrees(currentAngleInRadians);
	}
	
	@Override
	public String toString() {
		return "(" + getX() + ";" + getY() + ") angle: " + getCurrentAngle();
	}
	
}
