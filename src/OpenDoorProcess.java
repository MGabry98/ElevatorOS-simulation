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

		GUI.gui.Memory.runningThreads--;
		GUI.gui.Memory.lowPriorityProcesses.remove(this);

		GUI.gui.Memory.openEndProcess = Instant.now();
		GUI.gui.Memory.openDurationProcess = Duration.between(GUI.gui.Memory.openStartProcess, GUI.gui.Memory.openEndProcess)
				.toMillis() + GUI.gui.Memory.openDurationProcess;
		ProcessTime.processrunstop.add(GUI.gui.Memory.openEndProcess);
		ProcessTime.stoppedNow.add("Open");
		this.stop();
		GUI.gui.Memory.open = null;

	}

}
