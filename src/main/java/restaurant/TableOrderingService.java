package restaurant;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import restaurant.threads.DrinkBar;
import restaurant.threads.FoodKitchen;

public class TableOrderingService{

	private static final Logger LOGGER = LogManager.getLogger( TableOrderingService.class );
	//Sync: Each plate/drink is done synchronously
	public void startFoodOrderPreparationSyncBlocking(FoodOrder foodOrder){
		LOGGER.info("Starting to prepare the order No.: " + foodOrder.getOrderNumber());
		for(Drink drink:foodOrder.getDrinkList()){
			this.prepareDrink( drink );
		}

		for(Food food:foodOrder.getFoodList()){
			this.cookFood( food );
		}
	}

	public void startFoodOrderPreparationAsyncConcurrentWithThreads(FoodOrder foodOrder){
		LOGGER.info("Starting to prepare the order No.: " + foodOrder.getOrderNumber());

		for(Drink drink:foodOrder.getDrinkList()){
			DrinkBar drinkBar = new DrinkBar( drink);
			Thread t = new Thread( drinkBar );
			t.start();
		}

		for(Food food:foodOrder.getFoodList()){
			//this.cookFood( food );
			/*Thread t = new Thread( ()->  this.cookFood( food ));
			t.start();*/

			FoodKitchen foodKitchen = new FoodKitchen( food);

			Thread t = new Thread( foodKitchen );
			t.start();
		}
	}

	public void startFoodOrderPreparationAsyncConcurrentWithThreadsWithLambdas(FoodOrder foodOrder){
		LOGGER.info("Starting to prepare the order No.: " + foodOrder.getOrderNumber());

		for(Drink drink:foodOrder.getDrinkList()){
			//this.prepareDrink( drink );
			Thread t = new Thread( ()->  this.prepareDrink( drink ));
			t.start();
		}

		for(Food food:foodOrder.getFoodList()){
			//this.cookFood( food );
			Thread t = new Thread( ()->  this.cookFood( food ));
			t.start();
		}
	}

	public void startFoodOrderPreparationAsyncConcurrentWithThreads2(FoodOrder foodOrder){
		for ( Drink d: foodOrder.getDrinkList()) {
			TableOrderingThreads t = new TableOrderingThreads( d );
			t.run();
		}

		for ( Food f: foodOrder.getFoodList()) {
			TableOrderingThreads t = new TableOrderingThreads( f );
			t.run();
		}
	}

	public void startFoodOrderPreparationSyncBlockingFuture(FoodOrder foodOrder){

		//ExecutorService executor = Executors.newSingleThreadExecutor();//SYNC BLOCKING
		ExecutorService executor = Executors.newSingleThreadExecutor();

		LOGGER.info("Starting to prepare the order No.: " + foodOrder.getOrderNumber());
		for(Drink drink:foodOrder.getDrinkList()){
			Future<?> future = executor.submit( ()-> this.prepareDrink( drink ));
		}

		for(Food food:foodOrder.getFoodList()){
			Future<?> future = executor.submit( ()-> this.cookFood( food ));
		}

	}

	public void startFoodOrderPreparationAsyncConcurrentWithCompletableFuture(FoodOrder foodOrder){


		LOGGER.info("Starting to prepare the order No.: " + foodOrder.getOrderNumber());
		for(Drink drink:foodOrder.getDrinkList()){
			CompletableFuture.runAsync( ()-> this.prepareDrink( drink ));
		}

		for(Food food:foodOrder.getFoodList()){
			CompletableFuture.runAsync( ()-> this.cookFood( food ));
		}

	}




	public void startFoodOrderPreparationAsyncConcurrentWithLoomAndVirtualThread(FoodOrder foodOrder) {

		LOGGER.info("Starting to prepare the order No.: " + foodOrder.getOrderNumber());

		for(Drink drink:foodOrder.getDrinkList()){
			/*DrinkBar drinkBar = new DrinkBar( drink);
			Thread t = new Thread( drinkBar );
			t.start();*/

			Thread thread2 = Thread.builder()
					.virtual()
					.name("drinkBarThread")
					.task(() -> this.prepareDrink( drink ))
					.start();
		}

		for(Food food:foodOrder.getFoodList()){
			/*FoodKitchen foodKitchen = new FoodKitchen( food);

			Thread t = new Thread( foodKitchen );
			t.start();*/

			Thread thread = Thread.builder()
					.virtual()
					.name("foodThread")
					.task(() -> this.cookFood( food ))
					.start();
		}



	}


	public void startFoodOrderPreparationAsyncConcurrentWithLoomAndExecutors(FoodOrder foodOrder) {
		LOGGER.info("Starting to prepare the order No.: " + foodOrder.getOrderNumber());
		try (ExecutorService executor = Executors.newVirtualThreadExecutor()) {

			for(Drink drink:foodOrder.getDrinkList()){
				executor.execute(() -> this.prepareDrink( drink ));
			}

			for(Food food:foodOrder.getFoodList()){
				executor.execute(() -> this.cookFood( food ));
			}
		}
	}
	//TODO Async: Orders come in and are prepared in the order they were received. Multiple order can be prepared at the same time (Concurrent/Async)
	//THREAD donde, FUTURE, CF, LOOM



	/*
		public void updateFlightStatusAsyncWithFuture() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<?> future = executor.submit(()-> this.updateFlightStatusCommon());
	}

	public void updateFlightStatusAsyncWithFutureButBlocking() throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<?> future = executor.submit(()-> this.updateFlightStatusCommon());
		future.get();
	}
	* */

	private void prepareDrink(Drink drink){
		this.sleep();
		LOGGER.info("Starting to prepare the drink: " + drink);
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
