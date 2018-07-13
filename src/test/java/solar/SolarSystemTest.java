package solar;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import solar.Weather.WeatherType;

public class SolarSystemTest {
	
	@Mock
	private WeatherDetector weatherDetector;
	
	@InjectMocks
	private SolarSystem solarSystem;
	
	private static Weather rainyWeather;
	private static Weather mostRainyWeather;
	private static Weather unknownWeather;
	private static Weather idealWeather;
	private static Weather dryWeather;
	
	@BeforeEach
	public void setupMocks() {
		MockitoAnnotations.initMocks(this);
		solarSystem.setSimulatedDaysFractions(1);
	}
	
	@BeforeAll
	public static void setupPrototypeWeathers() {
		rainyWeather = new Weather(WeatherType.RAINY,100d);
		mostRainyWeather = new Weather(WeatherType.RAINY,1000d);
		unknownWeather = new Weather(WeatherType.UNKNOWN,null);
		idealWeather = new Weather(WeatherType.IDEAL,null);
		dryWeather = new Weather(WeatherType.DRY,null);
	}
	
	@Test
	public void testGetOneRainyPeriodsCount() {
		solarSystem.setSimulatedDaysFractions(1);
		when(weatherDetector.detectSolarSystemWeather(any(), any(), any(),any())).thenReturn(
				rainyWeather,unknownWeather,dryWeather,dryWeather,dryWeather,unknownWeather);
		solarSystem.runSimulationForYears(2);
		int rainyPeridsCount = solarSystem.getRainyPeriodsCount();		
		Assertions.assertEquals(1, rainyPeridsCount,"rainy periods");
	}

	@Test
	public void testGetOneDryPeriodsCount() {
		solarSystem.setSimulatedDaysFractions(1);
		when(weatherDetector.detectSolarSystemWeather(any(), any(), any(),any())).thenReturn(
				rainyWeather,unknownWeather,idealWeather,
				dryWeather,idealWeather,idealWeather,
				rainyWeather,unknownWeather);
		solarSystem.runSimulationForYears(2);
		int dryPeridsCount = solarSystem.getDryPeriodsCount();		
		Assertions.assertEquals(1, dryPeridsCount,"dry periods");
	}

	@Test
	public void testGetOneIdealPeriodsCount() {
		solarSystem.setSimulatedDaysFractions(1);
		when(weatherDetector.detectSolarSystemWeather(any(), any(), any(),any())).thenReturn(
				rainyWeather,unknownWeather,idealWeather,dryWeather,unknownWeather);
		solarSystem.runSimulationForYears(2);
		int idealPeridsCount = solarSystem.getIdealPeriodsCount();		
		Assertions.assertEquals(1, idealPeridsCount,"ideal periods");
	}

	@Test
	public void testGetZeroRainyDays() {
		when(weatherDetector.detectSolarSystemWeather(any(), any(), any(),any())).thenReturn(
				unknownWeather,
				idealWeather,
				dryWeather);
		solarSystem.runSimulationForYears(2);
		int dryPeridsCount = solarSystem.getRainyPeriodsCount();		
		Assertions.assertEquals(0, dryPeridsCount,"rainy periods");
	}

	@Test
	public void testGetZeroIdealPeriodsCount() {
		when(weatherDetector.detectSolarSystemWeather(any(), any(), any(),any())).thenReturn(
				rainyWeather,unknownWeather,dryWeather,unknownWeather);
		solarSystem.runSimulationForYears(2);
		int idealPeridsCount = solarSystem.getIdealPeriodsCount();		
		Assertions.assertEquals(0, idealPeridsCount,"ideal periods");
	}

	@Test
	public void testGetZeroDryPeriodsCount() {
		when(weatherDetector.detectSolarSystemWeather(any(), any(), any(),any())).thenReturn(
				rainyWeather,unknownWeather,idealWeather,unknownWeather);
		solarSystem.runSimulationForYears(2);
		int dryPeridsCount = solarSystem.getDryPeriodsCount();		
		Assertions.assertEquals(0, dryPeridsCount,"dry periods");
	}

	@Test
	public void testWhenRegisteredDaysWithOnlyUnknownWeatherReturnZeroPeriodsCount() {
		when(weatherDetector.detectSolarSystemWeather(any(), any(), any(),any())).thenReturn(
				unknownWeather);
		solarSystem.runSimulationForYears(2);
		int idealPeridsCount = solarSystem.getIdealPeriodsCount();		
		int dryPeridsCount = solarSystem.getDryPeriodsCount();
		int rainyPeridsCount = solarSystem.getRainyPeriodsCount();
		Assertions.assertEquals(0, idealPeridsCount,"ideal periods");
		Assertions.assertEquals(0, dryPeridsCount,"dry periods");
		Assertions.assertEquals(0, rainyPeridsCount,"rainy periods");
	}
	
	@Test
	public void testWhenNotStartedSimulationThrowException() {
		   Assertions.assertThrows(IllegalStateException.class,
		            ()->{
		        		when(weatherDetector.detectSolarSystemWeather(any(), any(), any(),any())).thenReturn(
		        				unknownWeather);
		        		int idealPeridsCount = solarSystem.getIdealPeriodsCount();		
		        		int dryPeridsCount = solarSystem.getDryPeriodsCount();
		        		int rainyPeridsCount = solarSystem.getRainyPeriodsCount();
		            });
	}
	
	@Test
	public void testGetWettestDayExists() {
		when(weatherDetector.detectSolarSystemWeather(any(), any(), any(),any())).thenReturn(
				rainyWeather,unknownWeather,dryWeather,dryWeather,dryWeather,mostRainyWeather,unknownWeather);
		solarSystem.runSimulationForYears(2);
		RegisteredDay wettestDay = solarSystem.getWettestDay();
		Assertions.assertEquals(5, wettestDay.getDay(),"wettest day");
		Assertions.assertEquals(1000d, wettestDay.getHumidityRanking(),"wettest day ranking");
	}
	
	@Test
	public void testGetWettestDayNotExists() {
		when(weatherDetector.detectSolarSystemWeather(any(), any(), any(),any())).thenReturn(
				unknownWeather,dryWeather,dryWeather,dryWeather,unknownWeather);
		solarSystem.runSimulationForYears(2);
		RegisteredDay wettestDay = solarSystem.getWettestDay();
		Assertions.assertNull(wettestDay,"wettest day");
	}	

}
