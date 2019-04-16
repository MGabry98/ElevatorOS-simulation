import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Memory {
	int runningThreads = 0;
	ScreenTimeProcess time;
	ScreenCurrentFloorProcess currentFloorProcess;
	FanProcess fan;
	MoveProcess move;
	OpenDoorProcess open;
	CloseDoorProcess close;
	Queue<Process> highPriorityProcesses = new LinkedList<Process>();
	Queue<Process> mediumPriorityProcesses = new LinkedList<Process>();
	ArrayList<Process> lowPriorityProcesses = new ArrayList<Process>();
	ArrayList<Process> noPriorityProcess = new ArrayList<Process>();
	int currentFloor;
	int maxFloor = 7;
	int minFloor = 0;
	PriorityQueue<Integer> destinationFloors;
	Instant startRunning;
	Instant endRunning;
	long totaltime = 0;
	Instant timestartprocess;
	Instant timeendprocess;
	long timedurationprocess = 0;
	Instant currentFloorStartProcess;
	Instant currentFloorEndProcess;
	long currentFloorDurationProcess = 0;
	Instant fanStartProcess;
	Instant fanEndProcess;
	long fanDurationProcess = 0;
	Instant closeStartProcess;
	Instant closeEndProcess;
	long closeDurationProcess = 0;
	Instant openStartProcess;
	Instant openEndProcess;
	long openDurationProcess = 0;

	Instant moveStartProcess;
	Instant moveEndProcess;
	long moveDurationProcess = 0;

}
