
public class ScreenCurrentFloorProcess extends Process{

	int CurrentFloor;
	Elevator elevator;
	public  ScreenCurrentFloorProcess(Elevator elevator) {
		super.ThreadName="Current Floor";
		this.elevator=elevator;
	}
	@Override
	public void run() {
		
		while(true)
			elevator.Screen="Current Floor: "+elevator.currentFloor();
		
	}

}
