
public class ScreenCurrentFloorThread extends Thread{
	String ThreadName;
	int CurrentFloor;
	Elevator elevator;
	public  ScreenCurrentFloorThread(Elevator elevator) {
		ThreadName="Current Floor";
		this.elevator=elevator;
	}
	@Override
	public void run() {
		
		while(true)
			elevator.Screen="Current Floor: "+elevator.currentFloor();
		
	}

}
