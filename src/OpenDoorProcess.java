
public class OpenDoorProcess extends Process{

	Elevator elevator;
	public OpenDoorProcess(Elevator elevator){
		this.elevator=elevator;
		super.ThreadName="Open Door";
	}

	@Override
	public void run() {
		elevator.door=true;
		System.out.println("Open Door Thread has been Stopped");

		Control.runningThreads--;
		Memory.lowPriorityProcesses.remove(this);
		this.stop();
		
		Control.open=null;

	}
	
}
