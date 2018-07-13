package solar;


import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import solar.geometry.ShapeBuilder;
import solar.mocks.DummyPoint;


public class WeatherDetectorTest {

	private static DummyPoint mockedSun;
	private static Planet p1;
	private static Planet p2;
	private static Planet p3;
	
	@Mock
	private Shape mockedShape;

	@Mock
	private ShapeBuilder mockedShapeBuilder;

	@InjectMocks
	private WeatherDetector weatherDetector;

	@BeforeAll
	public static void setupMockedSolarSystem() {
		mockedSun = new DummyPoint(0,0);
		p1 = new Planet(100000,0,1);
		p2 = new Planet(20000,0,2);
		p3 = new Planet(40000,0,-1);
	}
	
	@BeforeEach
	public void setupMocks() {
		MockitoAnnotations.initMocks(this);
		when(mockedShapeBuilder.buildWith(p1, p2, p3)).thenReturn(mockedShape);
	}
	
	@Test
	public void testDetectSolarSystemDryWeather() {
		
		when(mockedShape.isLineAndIncludePoint(mockedSun)).thenReturn(true);
		when(mockedShape.isLine()).thenReturn(true);
		when(mockedShape.isTriangle()).thenReturn(false);
		when(mockedShape.isTriangleAndIncludePoint(mockedSun)).thenReturn(false);
		
		Weather detectedWeather = weatherDetector.detectSolarSystemWeather(mockedSun, p1, p2, p3);
		
		Assertions.assertTrue(detectedWeather.isDry(),"is dry");
		Assertions.assertFalse(detectedWeather.isRainy(),"is not rainy");
		Assertions.assertFalse( detectedWeather.isIdeal(),"is not ideal");
		Assertions.assertFalse(detectedWeather.isUnknown(),"is known");
	}

	@Test
	public void testDetectSolarSystemRainyWeather() {
		
		when(mockedShape.isLineAndIncludePoint(mockedSun)).thenReturn(false);
		when(mockedShape.isLine()).thenReturn(false);
		when(mockedShape.isTriangle()).thenReturn(true);
		when(mockedShape.isTriangleAndIncludePoint(mockedSun)).thenReturn(true);
		when(mockedShape.getPerimeter()).thenReturn(new Double(100d));
		
		Weather detectedWeather = weatherDetector.detectSolarSystemWeather(mockedSun, p1, p2, p3);
		
		Assertions.assertFalse(detectedWeather.isDry(),"is not dry");
		Assertions.assertTrue(detectedWeather.isRainy(),"is rainy");
		Assertions.assertFalse( detectedWeather.isIdeal(),"is not ideal");
		Assertions.assertFalse(detectedWeather.isUnknown(),"is known");
		Assertions.assertEquals(new Double(100d),detectedWeather.getHumidityRanking(),"humidity ranking");	
	}

	@Test
	public void testDetectSolarSystemIdealWeather() {
		
		when(mockedShape.isLineAndIncludePoint(mockedSun)).thenReturn(false);
		when(mockedShape.isLine()).thenReturn(true);
		when(mockedShape.isTriangle()).thenReturn(false);
		when(mockedShape.isTriangleAndIncludePoint(mockedSun)).thenReturn(false);
		
		Weather detectedWeather = weatherDetector.detectSolarSystemWeather(mockedSun, p1, p2, p3);
		
		Assertions.assertFalse(detectedWeather.isDry(),"is not dry");
		Assertions.assertFalse(detectedWeather.isRainy(),"is not rainy");
		Assertions.assertTrue( detectedWeather.isIdeal(),"is ideal");
		Assertions.assertFalse(detectedWeather.isUnknown(),"is known");
	}

	
}
