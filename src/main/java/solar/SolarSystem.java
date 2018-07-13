package solar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Main entry point, contain all solar system behaviur rules, runs the
 * simulations and answers weather questions
 *
 */
public class SolarSystem {

	private static final int DAYS_PER_YEAR = 365;

	private int simulatedDaysFractions = 10;

	private Point sun;

	private WeatherDetector weatherDetector;

	private Planet ferengi;
	private Planet betasoide;
	private Planet vulcano;
	private List<RegisteredDay> registeredDays;
	private long unknownWeathers = 0;

	private static class Sun extends Point {

		@Override
		public double getX() {
			return 0;
		}

		@Override
		public double getY() {
			return 0;
		}

	}

	public SolarSystem() {
		Shape.PRECISION = 0.001;
		sun = new Sun();
		weatherDetector = new WeatherDetector();
	}

	/**
	 * Set granularity of the simulation
	 * @param fractions
	 */
	public void setSimulatedDaysFractions(int fractions) {
		this.simulatedDaysFractions = fractions;
	}

	private void initPlanets() {
		this.ferengi = new Planet(500, 0, 1);
		this.betasoide = new Planet(2000, 0, 3);
		this.vulcano = new Planet(1000, 0, -5);
	}

	/**
	 * Run simulation for next days, accept fractional days
	 * 
	 * @param days
	 */
	private void continueSimulationForDays(BigDecimal days) {
		ferengi.runMovementFor(days);
		betasoide.runMovementFor(days);
		vulcano.runMovementFor(days);
	}

	public void runSimulationForTenYears() {
		runSimulationForYears(10);
	}

	private boolean registeredCurrentDay(RegisteredDay regDay, long day) {
		return regDay != null && regDay.getDay() == day;
	}

	public void runSimulationForYears(int years) {
		initPlanets();
		long simulationSteps = DAYS_PER_YEAR * years * simulatedDaysFractions;
		registeredDays = new ArrayList<>();
		RegisteredDay regDay = null;
		System.out.println("Starting simulation for " + DAYS_PER_YEAR * years + " days");
		for (long simulationStep = 0; simulationStep < simulationSteps; simulationStep++) {
			long day = simulationStep / simulatedDaysFractions;
			Weather weather = weatherDetector.detectSolarSystemWeather(sun, ferengi, betasoide, vulcano);
			if (!weather.isUnknown()) {
				if (!registeredCurrentDay(regDay, day)) {
					regDay = new RegisteredDay(day, weather);
					registeredDays.add(regDay);
				}
			} else {
				unknownWeathers++;
			}
			continueSimulationForDays(BigDecimal.ONE.divide(BigDecimal.valueOf(simulatedDaysFractions)));
		}
		System.out.println("Finished simulation for " + DAYS_PER_YEAR * years + " days");
	}

	private void checkFinishedSimulation() {
		if (registeredDays == null) {
			throw new IllegalStateException("simulation not completed");
		}
	}

	public int getRainyPeriodsCount() {
		checkFinishedSimulation();
		long rainyDays = registeredDays.stream().filter(d -> d.isRainy()).count();
		return new Long(rainyDays).intValue();
	}

	public int getDryPeriodsCount() {
		checkFinishedSimulation();
		long dryDays = registeredDays.stream().filter(d -> d.isDry()).count();
		return new Long(dryDays).intValue();
	}

	public int getIdealPeriodsCount() {
		checkFinishedSimulation();
		long dryDays = registeredDays.stream().filter(d -> d.isIdeal()).count();
		return new Long(dryDays).intValue();
	}
	
	public long getUnknownWeatherCount() {
		checkFinishedSimulation();
		return unknownWeathers;
	}

	public RegisteredDay getWettestDay() {
		checkFinishedSimulation();
		Optional<RegisteredDay> wettestDayOptional = registeredDays.stream().filter(d -> d.isRainy())
				.max(new Comparator<RegisteredDay>() {
					@Override
					public int compare(RegisteredDay d1, RegisteredDay d2) {
						return Double.valueOf(d1.getHumidityRanking() - d2.getHumidityRanking()).intValue();
					}
				});
		if (wettestDayOptional.isPresent()) {
			return wettestDayOptional.get();
		}
		return null;

	}

	public static void main(String[] args) {
		SolarSystem solarSystem = new SolarSystem();
		solarSystem.runSimulationForTenYears();

		System.out.println("Simulation for 10 years");

		int dryPeriods = solarSystem.getDryPeriodsCount();
		System.out.println("Dry periods: " + dryPeriods);

		int idealPeriods = solarSystem.getIdealPeriodsCount();
		System.out.println("Ideal periods: " + idealPeriods);

		int rainyPeriods = solarSystem.getRainyPeriodsCount();
		System.out.println("Rainy periods: " + rainyPeriods);

		RegisteredDay wettestDay = solarSystem.getWettestDay();
		System.out.println("Wettest period: " + wettestDay);

		long unknownWeatherDays = solarSystem.getUnknownWeatherCount();
		System.out.println("Unknown weather periods: " + unknownWeatherDays);

	}

}
