package restaurant;

import java.util.List;

public class FoodOrder {

	private final Long orderNumber;
	private final List<MenuItem> menuItemList;

	public FoodOrder(Long orderNumber, List<MenuItem> menuItemList) {
		this.orderNumber = orderNumber;
		this.menuItemList = menuItemList;
	}


	public Long getOrderNumber() {
		return orderNumber;
	}

	public List<MenuItem> getMenuItemList() {
		return menuItemList;
	}

	@Override
	public String toString() {
		return "FoodOrder{" +
				"orderNumber=" + orderNumber +
				", menuItemList=" + menuItemList +
				'}';
	}
}
