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

		Control.runningThreads--;
		Memory.highPriorityProcesses.remove(this);
		this.stop();

		Control.close = null;
	}

}
