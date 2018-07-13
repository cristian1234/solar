package solar.mocks;

import solar.Planet;

public class MockPlannet extends Planet {

	public MockPlannet() {
		super(100,0, 1);
	}
	
	@Override
	public double getX() {
		return 1;
	}

	@Override
	public double getY() {
		return 1;
	}

}
