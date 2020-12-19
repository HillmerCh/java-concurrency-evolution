package restaurant;

import java.util.List;

public class FoodOrder {

	private final Long orderNumber;
	private final List<Drink> drinkList;
	private final List<Food> foodList;

	public FoodOrder(Long orderNumber, List<Drink> drinkList, List<Food> foodList) {
		this.orderNumber = orderNumber;
		this.drinkList = drinkList;
		this.foodList = foodList;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public List<Drink> getDrinkList() {
		return drinkList;
	}

	public List<Food> getFoodList() {
		return foodList;
	}

	@Override
	public String toString() {
		return "FoodOrder{" +
				"drinkList=" + drinkList +
				", foodList=" + foodList +
				'}';
	}
}
