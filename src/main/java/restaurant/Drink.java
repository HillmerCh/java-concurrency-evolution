package restaurant;

public class Drink implements MenuItem{
	private final String name;

	public Drink(String name) {
		this.name = name;
	}

	@Override
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
