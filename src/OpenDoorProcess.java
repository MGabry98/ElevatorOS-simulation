import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class OpenDoorProcess extends Process{

	Elevator elevator;
	public OpenDoorProcess(Elevator elevator){
		this.elevator=elevator;
		super.ThreadName="Open Door";
	}

	@Override
	public void run() {
		elevator.door=true;

		IO.print("Open Door Thread has been Stopped");

		Memory.runningThreads--;
		Memory.lowPriorityProcesses.remove(this);
	
		Memory.openEndProcess=Instant.now();
		Memory.openDurationProcess=Duration.between(Memory.openStartProcess, Memory.openEndProcess).toMillis()+Memory.openDurationProcess;
		this.stop();
		Memory.open=null;

	}
	
}
