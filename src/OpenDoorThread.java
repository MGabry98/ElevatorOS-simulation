
public class OpenDoorThread extends Thread{

	Elevator elevator;
	String ThreadName;
	public OpenDoorThread(Elevator elevator){
		this.elevator=elevator;
		ThreadName="Open Door";
	}

	@Override
	public void run() {
		elevator.door=true;
		System.out.println("Open Door Thread has been Stopped");
		Control.runningprocesses.remove(this);
		Control.runningThreads--;
		this.stop();
	}
	
}
