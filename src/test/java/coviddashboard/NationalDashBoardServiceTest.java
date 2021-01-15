package coviddashboard;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;


public class NationalDashBoardServiceTest {

	private static final Logger LOGGER = Logger.getLogger( NationalDashBoardServiceTest.class.getName());

	@Test
	void createDashboardSyncBlocking(){
		NationalDashBoardService nationalDashBoardService = new NationalDashBoardService();
		nationalDashBoardService.createDashboardSyncBlocking();
	}


	@Test
	void createDashboardAsyncConcurrentWithCompletableFutureWithExecutors(){
		NationalDashBoardService nationalDashBoardService = new NationalDashBoardService();


		Instant start = null;//Variable usada para contar el tiempo en que se tiene respuesta del método

		/*LOGGER.info( "Prueba con Executor: Single Thread");
		start = Instant.now();
		nationalDashBoardService.createDashboardAsyncConcurrentWithCompletableFutureWithExecutors( Executors.newSingleThreadExecutor());
		LOGGER.info( "Tiempo de respuesta (Single Thread): " + Duration.between( start, Instant.now()).toString());*/

		LOGGER.info( "Prueba con Executor: Common Pool");
		start = Instant.now();
		nationalDashBoardService.createDashboardAsyncConcurrentWithCompletableFutureWithExecutors( ForkJoinPool.commonPool());
		LOGGER.info( "Tiempo de respuesta (Common Pool): " + Duration.between( start, Instant.now()).toString());



		/*
		LOGGER.info( "Prueba con Executor: Executors.newFixedThreadPool( 5)");
		start = Instant.now();
		nationalDashBoardService.createDashboardAsyncConcurrentWithCompletableFutureWithExecutors( Executors.newFixedThreadPool( 5));
		LOGGER.info( "Tiempo de respuesta (Fixed Thread): " + Duration.between( start, Instant.now()).toString());*/


	}
}
