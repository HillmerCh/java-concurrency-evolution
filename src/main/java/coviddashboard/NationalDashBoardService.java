package coviddashboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NationalDashBoardService {

	private static final Logger LOGGER = LogManager.getLogger( NationalDashBoardService.class );

	void createDashboardSyncBlocking(){

		List<State> stateList = this.getAllStates();
		List<StateData> stateDataList =  new ArrayList<>();
		for ( State state:stateList ) {
			stateDataList.add( this.getStateData(state) );
		}

		List<StateData> stateDataListOrdered = stateDataList.stream()
				.sorted( Comparator.comparingInt( StateData::active ) )
				.collect( Collectors.toList());

		int totalActive = stateDataList.stream()
				.mapToInt( StateData::active  )
				.sum();

		LOGGER.info(  "Total active: " + totalActive );
		stateDataListOrdered.forEach( LOGGER::info  );

	}

	void createDashboardAsyncConcurrentWithCompletableFutureWithExecutors(Executor executor){

		printPoolInfo();
		List<State> stateList = this.getAllStates();
		List<CompletableFuture<StateData>> cfList = new ArrayList<>();
		for ( State state:stateList ) {
			CompletableFuture<StateData> cf = CompletableFuture.supplyAsync( () -> this.getStateData( state ), executor )
					/*.thenApply( p-> {
						System.out.println(p);
								return p;
					} )*/
					.thenApply(
							  p-> {
								  this.printPoolInfo();
								  return p;
							  }
					)
					
					;
			cfList.add( cf );

		}
		printPoolInfo();
		CompletableFuture.allOf( cfList.toArray( new CompletableFuture[0] ) ).join();

		List<StateData> stateDataListOrdered  = cfList.stream()
				.filter( CompletableFuture::isDone )
				.map( CompletableFuture::join )
				.sorted( Comparator.comparingInt( StateData::active ))
				.collect( Collectors.toList() );

		printPoolInfo();
		int totalActive = cfList.stream()
				.filter( CompletableFuture::isDone )
				.map( CompletableFuture::join )
				.mapToInt( StateData::active  )
				.sum();
		printPoolInfo();

		LOGGER.info(  "Total active: " + totalActive );
		stateDataListOrdered.forEach( LOGGER::info  );

	}




	private void printPoolInfo(){
		System.out.println( "Pool size: " + ForkJoinPool.commonPool().getPoolSize()
									+ ". Thread:  " + Thread.currentThread().getName());
	}

	private void sleep(long time) {
		try {
			Thread.sleep( time );
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private StateData getStateData(State state){
		Random random = new Random();
		StateData stateData = new StateData(state,
											random.ints( 0, 20 ).findAny().getAsInt(),
											random.ints( 0, 20 ).findAny().getAsInt(),
											random.ints( 0, 20 ).findAny().getAsInt());

		this.sleep( 2_000 );//Una pausa de 1 segundo para simular el tiempo que demora la carga de las tareas


		return stateData;
	}

	private List<State> getAllStates(){
		return List.of(new State("Alabama"), new State("Alaska"), new State("Arizona"), new State("Arkansas"), new State("California"));
		/*


		Alabama
Alaska
Arizona
Arkansas
California
Colorado
Connecticut
Delaware
Florida
Georgia
Hawaii
Idaho
Illinois
Indiana
Iowa
Kansas
Kentucky
Louisiana
Maine
Maryland
Massachusetts
Michigan
Minnesota
Mississippi
Missouri
Montana
Nebraska
Nevada
New Hampshire
New Jersey
New Mexico
New York
North Carolina
North Dakota
Ohio
Oklahoma
Oregon
Pennsylvania
Rhode Island
South Carolina
South Dakota
Tennessee
Texas
Utah
Vermont
Virginia
Washington
West Virginia
Wisconsin
Wyoming

		* */
	}


}
