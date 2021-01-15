package restaurant;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

class TableOrderingServiceTest {

	private FoodOrder foodOrder = new FoodOrder( 1L,
												 Arrays.asList(
														 new Drink( "BEER" ),
														 new Drink( "COKE" ),
														 new Food("FRIES"),
														 new Food("BURGER")));
	@Test
	void startFoodOrderPreparationSyncBlocking() {
		TableOrderingService tableOrderingService = new TableOrderingService();
		tableOrderingService.startFoodOrderPreparationSyncBlocking( foodOrder );
	}

	@Test
	void startFoodOrderPreparationAsyncConcurrentWithThreads() {
		TableOrderingService tableOrderingService = new TableOrderingService();
		tableOrderingService.startFoodOrderPreparationAsyncConcurrentWithThreads( foodOrder );
		sleep(20_000);
	}


	@Test
	void startFoodOrderPreparationSyncBlockingFuture(){

		TableOrderingService tableOrderingService = new TableOrderingService();
		tableOrderingService.startFoodOrderPreparationAsyncBlockingFuture( foodOrder );
		sleep(20_000);

	}

	@Test
	void startFoodOrderPreparationAsyncConcurrentWithCompletableFuture(){

		TableOrderingService tableOrderingService = new TableOrderingService();
		tableOrderingService.startFoodOrderPreparationAsyncConcurrentWithCompletableFuture( foodOrder );
		sleep(20_000);

	}


	@Test
	void startFoodOrderPreparationAsyncConcurrentWithLoomAndVirtualThread() {
		TableOrderingService tableOrderingService = new TableOrderingService();
		tableOrderingService.startFoodOrderPreparationAsyncConcurrentWithLoomAndVirtualThread( foodOrder );
		sleep(20_000);
	}

	@Test
	void startFoodOrderPreparationAsyncConcurrentWithLoomAndExecutors() {
		TableOrderingService tableOrderingService = new TableOrderingService();
		tableOrderingService.startFoodOrderPreparationAsyncConcurrentWithLoomAndExecutors( foodOrder );
		sleep(20_000);
	}




	private void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
