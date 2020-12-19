package restaurant.threads;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import restaurant.Drink;

public class DrinkBar implements Runnable {

	private static final Logger LOGGER = LogManager.getLogger( DrinkBar.class );

	private Drink drink;

	public DrinkBar(Drink drink) {
		this.drink = drink;
	}


	public void run() {
		LOGGER.info("RUN to prepare the drink: " + drink);
		this.prepareDrink(this.drink);
	}

	private void prepareDrink(Drink drink){
		this.sleep();
		LOGGER.info("Starting to prepare the drink: " + drink);
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
