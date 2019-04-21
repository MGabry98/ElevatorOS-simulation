import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class CloseDoorProcess extends Process {

	Elevator elevator;

	public CloseDoorProcess(Elevator elevator) {
		this.elevator = elevator;
		super.ThreadName = "Close Door";
	}

	@Override
	public void run() {
		elevator.door = false;
		try {

			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Cant wait i am in hurry");
		}
	
		IO.print("Close Door Thread has been Stopped");

		GUI.gui.Memory.runningThreads--;
		GUI.gui.Memory.highPriorityProcesses.remove(this);
		GUI.gui.Memory.closeEndProcess = Instant.now();
		ProcessTime.processrunstop.add(GUI.gui.Memory.closeEndProcess);
		ProcessTime.stoppedNow.add("close");
		GUI.gui.Memory.closeDurationProcess = Duration.between(GUI.gui.Memory.closeStartProcess, GUI.gui.Memory.closeEndProcess)
				.toMillis() + GUI.gui.Memory.closeDurationProcess;

		this.stop();
		GUI.gui.Memory.close = null;
	}

}
