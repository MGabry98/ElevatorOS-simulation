import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class OpenDoorProcess extends Process {

	Elevator elevator;

	public OpenDoorProcess(Elevator elevator) {
		this.elevator = elevator;
		super.ThreadName = "Open Door";
	}

	@Override
	public void run() {
		elevator.door = true;

		IO.print("Open Door Thread has been Stopped");

		IO.Memory.runningThreads--;
		IO.Memory.lowPriorityProcesses.remove(this);

		IO.Memory.openEndProcess = Instant.now();
		IO.Memory.openDurationProcess = Duration.between(IO.Memory.openStartProcess, IO.Memory.openEndProcess)
				.toMillis() + IO.Memory.openDurationProcess;
		this.stop();
		IO.Memory.open = null;

	}

}
