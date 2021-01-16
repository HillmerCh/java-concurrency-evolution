package restaurant;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TableOrderingService{

	private static final Logger LOGGER = LogManager.getLogger( TableOrderingService.class );

	public void startFoodOrderPreparationSyncBlocking(FoodOrder foodOrder){
		LOGGER.info("Starting to prepare the order No.: " + foodOrder.getOrderNumber());
		foodOrder.getMenuItemList().forEach( this::prepareMenuItem );
	}
	private void prepareMenuItem(MenuItem menuItem){
		this.sleep();
		LOGGER.info(menuItem + " is ready to serve");
	}
	private void sleep() {
		try {
			Random r = new Random();
			Thread.sleep(r.nextInt(10) * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void startFoodOrderPreparationAsyncConcurrentWithThreads(FoodOrder foodOrder){
		LOGGER.info("Starting to prepare the order No.: " + foodOrder.getOrderNumber());

		foodOrder.getMenuItemList().forEach( menuItem -> {
			Thread t = new Thread( () -> this.prepareMenuItem( menuItem ) );
			t.start();
		});

		LOGGER.info("Food order No.: " + foodOrder.getOrderNumber() + " is being prepared");

	}

	public void startFoodOrderPreparationAsyncNotBlockingFuture(FoodOrder foodOrder){

		LOGGER.info("Starting to prepare the order No.: " + foodOrder.getOrderNumber());


		ExecutorService executor = Executors.newFixedThreadPool( 2 );
		foodOrder.getMenuItemList().forEach( menuItem -> executor.submit( ()-> this.prepareMenuItem( menuItem )));

		LOGGER.info("Food order No.: " + foodOrder.getOrderNumber() + " is being prepared");

	}

	public void startFoodOrderPreparationAsyncConcurrentWithCompletableFuture(FoodOrder foodOrder){

		LOGGER.info("Starting to prepare the order No.: " + foodOrder.getOrderNumber());
		foodOrder.getMenuItemList().forEach( menuItem->
		 CompletableFuture
				 .runAsync( ()-> this.prepareMenuItem( menuItem ))
		)


		;
		LOGGER.info("Food order No.: " + foodOrder.getOrderNumber() + " is being prepared");

	}

	public void startFoodOrderPreparationAsyncConcurrentWithLoomAndVirtualThread(FoodOrder foodOrder) {

		LOGGER.info("Starting to prepare the order No.: " + foodOrder.getOrderNumber());

		foodOrder.getMenuItemList().forEach( menuItem -> Thread.builder()
				.virtual()
				.name("menuItemThread")
				.task(() -> this.prepareMenuItem( menuItem ))
				.start());

	}

	public void startFoodOrderPreparationAsyncConcurrentWithLoomAndExecutors(FoodOrder foodOrder) {
		LOGGER.info("Starting to prepare the order No.: " + foodOrder.getOrderNumber());
		try (ExecutorService executor = Executors.newVirtualThreadExecutor()) {

			for(MenuItem menuItem : foodOrder.getMenuItemList()){
				executor.execute(() -> this.prepareMenuItem( menuItem ));
			}
		}
	}
}
