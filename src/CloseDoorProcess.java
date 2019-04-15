import java.time.Duration;
import java.time.Instant;

public class CloseDoorProcess extends Process {

	Elevator elevator;

	public CloseDoorProcess(Elevator elevator) {
		this.elevator = elevator;
		super.ThreadName = "Close Door";
	}

	@Override
	public void run() {
		elevator.door = false;
		IO.print("Close Door Thread has been Stopped");

		Memory.runningThreads--;
		Memory.highPriorityProcesses.remove(this);
		Memory.closeEndProcess=Instant.now();
		Memory.closeDurationProcess=Duration.between(Memory.closeStartProcess, Memory.closeEndProcess).toMillis()+Memory.closeDurationProcess;
		
		this.stop();
		Memory.close = null;
	}

}
