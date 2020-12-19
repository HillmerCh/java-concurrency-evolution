package restaurant.threads;

import java.util.Random;

import restaurant.Food;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FoodKitchen implements Runnable {

	private static final Logger LOGGER = LogManager.getLogger( FoodKitchen.class );

	private Food food;

	public FoodKitchen(Food food) {
		this.food = food;
	}

	public void run() {
		LOGGER.info("RUN to prepare the food: " + food);
		this.cookFood( this.food );

	}

	private void cookFood(Food food){
		this.sleep();
		LOGGER.info("Starting to prepare the food: " + food);
	}

	private void sleep() {
		try {
			Random r = new Random();
			Thread.sleep(r.nextInt(10) * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
