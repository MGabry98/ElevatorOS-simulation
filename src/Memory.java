import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class Memory {
	static int runningThreads = 0;
	static ScreenTimeProcess time;
	static ScreenCurrentFloorProcess currentFloorProcess;
	static FanProcess fan;
	static MoveProcess move;
	static OpenDoorProcess open;
	static CloseDoorProcess close;
	static Queue<Process> highPriorityProcesses=new LinkedList<Process>();
	static Queue<Process> mediumPriorityProcesses=new LinkedList<Process>();
	static ArrayList<Process> lowPriorityProcesses=new ArrayList<Process>();
	static ArrayList<Process> noPriorityProcess=new ArrayList<Process>();
	static int currentFloor;
	static int maxFloor=7;
	static int minFloor=0;
	static PriorityQueue<Integer> destinationFloors;
	static Instant startRunning;
	static Instant endRunning;
	static long totaltime=0;
	static Instant timestartprocess;
	static Instant timeendprocess;
	static long timedurationprocess=0;
	static Instant currentFloorStartProcess;
	static Instant currentFloorEndProcess;
	static long currentFloorDurationProcess=0;
	static Instant	fanStartProcess;
	static Instant fanEndProcess;
	static long fanDurationProcess=0;
	static Instant	closeStartProcess;
	static Instant closeEndProcess;
	static long closeDurationProcess=0;
	static Instant	openStartProcess;
	static Instant openEndProcess;
	static long openDurationProcess=0;
	
	static Instant moveStartProcess;
	static Instant moveEndProcess;
	static long moveDurationProcess=0;



}
