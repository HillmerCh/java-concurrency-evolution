package restaurant;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleThreads {

	private static final Logger LOGGER = LogManager.getLogger( SimpleThreads.class );


	public class FoodKitchen implements Runnable {

		//private static final Logger LOGGER2 = LogManager.getLogger( FoodKitchen.class );

		private Food food;

		public FoodKitchen(Food food) {
			this.food = food;
		}

		public void run() {
			System.out.println("FOOODDDDD");
			LOGGER.info("RUN to prepare the food: " + food);
			this.cookFood( this.food );
		}

		private void cookFood(Food food){
			LOGGER.info("FIRST to prepare the food: " + food);
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


	public class DrinkBar implements Runnable {

		private Drink drink;

		public DrinkBar(Drink drink) {
			this.drink = drink;
		}


		public void run() {
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



}
