package solar;

public class Weather {

	public enum WeatherType {
		RAINY, DRY, IDEAL,UNKNOWN
	}

	private WeatherType weatherType;

	private Double humidityRanking;

	public Weather(WeatherType typeOfWeather, Double humidityRanking) {
		this.weatherType = typeOfWeather;
		if (humidityRanking != null) {
			this.humidityRanking = humidityRanking;
		} else {
			this.humidityRanking = 0d;
		}
	}

	public boolean isRainy() {
		return WeatherType.RAINY.equals(weatherType);
	}

	public boolean isDry() {
		return WeatherType.DRY.equals(weatherType);
	}

	public boolean isIdeal() {
		return WeatherType.IDEAL.equals(weatherType);
	}

	public Double getHumidityRanking() {
		return humidityRanking;
	}
	
	public boolean isUnknown() {
		return Weather.WeatherType.UNKNOWN.equals(weatherType);
	}
	
	@Override
	public String toString() {
		return weatherType + " humidityRanking: " + humidityRanking;
	}

}
