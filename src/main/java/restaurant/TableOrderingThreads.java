package restaurant;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TableOrderingThreads implements Runnable {

	private static final Logger LOGGER = LogManager.getLogger( TableOrderingThreads.class );

	Food food;
	Drink drink;

	public TableOrderingThreads(Food food) {
		this.food = food;
	}

	public TableOrderingThreads(Drink drink) {
		this.drink = drink;
	}

	void prepareFood() {
		//LOGGER.info( "Preparing Food: " + food );
		this.sleep();
		LOGGER.info("Starting to prepare the food: " + food);
	}

	void prepareDrink() {
		//LOGGER.info( "Preparing Drink: " + drink );
		this.sleep();
		LOGGER.info("Starting to prepare the drink: " + drink);
	}

	public void run() {
		if(food != null){
			prepareFood();
		} else {
			prepareDrink();
		}
	}

	private void sleep() {
		try {
			Random r = new Random();
			Thread.sleep( r.nextInt( 10 ) * 1000 );
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


