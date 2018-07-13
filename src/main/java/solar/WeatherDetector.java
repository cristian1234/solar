package solar;

import solar.geometry.ShapeBuilder;

/**
 * Calculate weather according to sun and planets positions
 * @return the weather calculated
 *
 */
public class WeatherDetector {
	
	private ShapeBuilder shapeBuilder = new ShapeBuilder();
	
	public Weather detectSolarSystemWeather(Point sun,Planet p1,Planet p2,Planet p3) {
		Shape planetsShape = shapeBuilder.buildWith(p1, p2, p3);

		if(isRainyDay(sun,planetsShape)) {
			return new Weather(Weather.WeatherType.RAINY,planetsShape.getPerimeter());
		}
		if(isIdealDay(sun,planetsShape)) {
			return new Weather(Weather.WeatherType.IDEAL,null);
		}
		if(isDryDay(sun, planetsShape)){
			return new Weather(Weather.WeatherType.DRY,null);
		}
		return new Weather(Weather.WeatherType.UNKNOWN, null);
	}

	private boolean isIdealDay(Point sun, Shape planetsShape) {
		return planetsShape.isLine() && !planetsShape.isLineAndIncludePoint(sun);
	}
	
	private boolean isRainyDay(Point sun, Shape planetsShape) {
		return planetsShape.isTriangleAndIncludePoint(sun);
	}

	private boolean isDryDay(Point sun, Shape planetsShape) {
		return planetsShape.isLineAndIncludePoint(sun);
	}
	
}
