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
		Instant start = Instant.now();
		NationalDashBoardService nationalDashBoardService = new NationalDashBoardService();
		nationalDashBoardService.createDashboardSyncBlocking();
		LOGGER.info( "Response Time: " + Duration.between( start, Instant.now()).toString());
	}

	@Test
	void createDashboardAsyncConcurrentWithCompletableFutureWithExecutors(){
		NationalDashBoardService nationalDashBoardService = new NationalDashBoardService();

		Instant start = Instant.now();
		LOGGER.info( "Test with Executor");
		nationalDashBoardService.createDashboardAsyncConcurrentWithCompletableFutureWithExecutors( ForkJoinPool.commonPool());
		LOGGER.info( "Response Time: " + Duration.between( start, Instant.now()).toString());

//		Executors.newSingleThreadExecutor()
//		Executors.newFixedThreadPool( 5)
	}
}