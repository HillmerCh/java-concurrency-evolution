package coviddashboard;

public record StateData(State state, int active, int deaths, int recovered){}
/*
public class StateData {

	private State state;
	private int active;
	private int deaths;
	private int recovered;

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getRecovered() {
		return recovered;
	}

	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}

	@Override
	public String toString() {
		return "StateData{" +
				"state=" + state +
				", active=" + active +
				", deaths=" + deaths +
				", recovered=" + recovered +
				'}';
	}
}
*/
