package restaurant;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class TableOrderingServiceTest {
	private static final Logger LOGGER = LogManager.getLogger( TableOrderingServiceTest.class );

	private FoodOrder foodOrder = new FoodOrder( 1L,
												 Arrays.asList(
														 new Drink( "BEER" ),
														 new Drink( "COKE" ),
														 new Food("FRIES"),
														 new Food("BURGER")));
	@Test
	void startFoodOrderPreparationSyncBlocking() {
		LOGGER.debug( "Starting testing invocation" );
		TableOrderingService tableOrderingService = new TableOrderingService();
		tableOrderingService.startFoodOrderPreparationSyncBlocking( foodOrder );
		LOGGER.debug( "End testing invocation" );
	}

	@Test
	void startFoodOrderPreparationAsyncConcurrentWithThreads() {
		LOGGER.debug( "Starting testing invocation" );
		TableOrderingService tableOrderingService = new TableOrderingService();
		tableOrderingService.startFoodOrderPreparationAsyncConcurrentWithThreads( foodOrder );
		LOGGER.debug( "End testing invocation" );
		sleep(20_000);//20 Seconds to see the logs
	}


	@Test
	void startFoodOrderPreparationSyncBlockingFuture(){
		LOGGER.debug( "Starting testing invocation" );
		TableOrderingService tableOrderingService = new TableOrderingService();
		tableOrderingService.startFoodOrderPreparationAsyncBlockingFuture( foodOrder );
		LOGGER.debug( "End testing invocation" );
		sleep(20_000);//20 Seconds to see the logs

	}

	@Test
	void startFoodOrderPreparationAsyncConcurrentWithCompletableFuture(){
		LOGGER.debug( "Starting testing invocation" );
		TableOrderingService tableOrderingService = new TableOrderingService();
		tableOrderingService.startFoodOrderPreparationAsyncConcurrentWithCompletableFuture( foodOrder );
		LOGGER.debug( "End testing invocation" );
		sleep(20_000);

	}


	@Test
	void startFoodOrderPreparationAsyncConcurrentWithLoomAndVirtualThread() {
		LOGGER.debug( "Starting testing invocation" );
		TableOrderingService tableOrderingService = new TableOrderingService();
		tableOrderingService.startFoodOrderPreparationAsyncConcurrentWithLoomAndVirtualThread( foodOrder );
		LOGGER.debug( "End testing invocation" );
		sleep(20_000);
	}

	@Test
	void startFoodOrderPreparationAsyncConcurrentWithLoomAndExecutors() {
		LOGGER.debug( "Starting testing invocation" );
		TableOrderingService tableOrderingService = new TableOrderingService();
		tableOrderingService.startFoodOrderPreparationAsyncConcurrentWithLoomAndExecutors( foodOrder );
		LOGGER.debug( "End testing invocation" );
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
