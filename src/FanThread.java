
public class FanThread extends Thread{
	String ThreadName;
	Elevator elevator;
	public FanThread(Elevator elevator) {
		this.elevator=elevator;
		ThreadName="Fan";
	}
	
	
	@Override
	public void run() {
		
		while(true){
			elevator.Fan=true;
		}
		
	}

}
