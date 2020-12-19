package restaurant;

public class Food {
	private final String name;

	public Food(String name) {
		this.name = name;
	}

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
