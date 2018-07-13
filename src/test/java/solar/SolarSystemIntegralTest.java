package solar;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SolarSystemIntegralTest {
	
	private SolarSystem solarSystem;
	
	@BeforeEach
	public void setupSolarSystem() {
		solarSystem = new SolarSystem();
	}
	
	/** TODO: Calculate and create a consistent integration data set in order to compare with the full simulation */
	@Test
	public void testMethods() {
		solarSystem.runSimulationForYears(10);
		int dryPeriods = solarSystem.getDryPeriodsCount();
		System.out.println(" dry periods: "+ dryPeriods);
		
		int idealPeriods = solarSystem.getIdealPeriodsCount();
		System.out.println(" ideal periods: "+ idealPeriods);
		
		int rainyPeriods = solarSystem.getRainyPeriodsCount();
		System.out.println(" rainy periods: "+ rainyPeriods);
		
		RegisteredDay wettestDay = solarSystem.getWettestDay();
		System.out.println(" wettest period: "+ wettestDay);
		
		long unknownWeatherDays = solarSystem.getUnknownWeatherCount();
		System.out.println("Unknown weather periods: " + unknownWeatherDays);

	}

}
