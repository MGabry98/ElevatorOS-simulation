
public class CloseDoorThread extends Thread{

	Elevator elevator;
	String ThreadName;
	public CloseDoorThread(Elevator elevator){
		this.elevator=elevator;
		ThreadName="Close Door"; 
	}

	@Override
	public void run() {
		elevator.door=false;
		System.out.println("Close Door Thread has been Stopped");
		Control.runningprocesses.remove(this);
		Control.runningThreads--;
		this.stop();
	}
	
}


