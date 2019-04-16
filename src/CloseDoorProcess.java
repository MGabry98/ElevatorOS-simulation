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

		IO.Memory.runningThreads--;
		IO.Memory.highPriorityProcesses.remove(this);
		IO.Memory.closeEndProcess = Instant.now();
		IO.Memory.closeDurationProcess = Duration.between(IO.Memory.closeStartProcess, IO.Memory.closeEndProcess)
				.toMillis() + IO.Memory.closeDurationProcess;

		this.stop();
		IO.Memory.close = null;
	}

}
