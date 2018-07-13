package solar;

/**
 * Store day and weather for each computed day of the simulation 
 *
 */
public class RegisteredDay {

	private long day;
	private Weather weather;
	
	public RegisteredDay(long day,Weather weather) {
		this.day = day;
		this.weather = weather;
	}
	
	public boolean isRainy() {
		return weather.isRainy();
	}
	
	public boolean isDry() {
		return weather.isDry();
	}
	
	public boolean isIdeal() {
		return weather.isIdeal();
	}
	
	public double getHumidityRanking() {
		return weather.getHumidityRanking();
	}
	
	public long getDay() {
		return day;
	}
	
	@Override
	public String toString() {
		return "day:" + day + ", weather: " + weather;
	}
	
}
