package restaurant;

public class ThreadsExample {

	FoodOrder order;

	public ThreadsExample(FoodOrder order){
		this.order = order;
	}

	public void processOrder(){
		for ( Drink d: order.getDrinkList()) {
			TableOrderingThreads t = new TableOrderingThreads( d );
			t.run();
		}

		for ( Food f: order.getFoodList()) {
			TableOrderingThreads t = new TableOrderingThreads( f );
			t.run();
		}
	}
}
