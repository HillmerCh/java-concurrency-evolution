package restaurant;

public class Food implements MenuItem {
	private final String name;

	public Food(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Food{" +
				"name='" + name + '\'' +
				'}';
	}
}
