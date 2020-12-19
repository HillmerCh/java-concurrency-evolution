package restaurant;

public class Drink {
	private final String name;

	public Drink(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Drink{" +
				"name='" + name + '\'' +
				'}';
	}
}
