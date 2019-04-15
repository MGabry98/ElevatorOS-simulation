import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class MoveProcess extends Process {

	Elevator elevator;


	public MoveProcess(Elevator elevator) {
		this.elevator = elevator;
		super.ThreadName = "Move";
	}

	@Override
	public void run() {
		while (!Memory.destinationFloors.isEmpty()) {

			Direction d;
			int temp = elevator.nextDestionation();
			while (elevator.direction() != Direction.Hold) {
				try {
					
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
System.out.println("Cant wait i am in hurry");
}
				d = elevator.direction();
				if (d == Direction.Up) {
					elevator.moveUp();
					IO.print("Elevator is moving Up  ,Elevator next destination is "
									+ elevator.nextDestionation()
									+ ",current floor " + Memory.currentFloor);
				} else if (d == Direction.Down) {
					elevator.moveDown();
					IO.print("Elevator is moving down  ,Elevator next destination is "
									+ elevator.nextDestionation()
									+ ",current floor " + Memory.currentFloor);
				}

			}

		}

		
		
		
		IO.print(this.ThreadName + " process has been stopped");
		
		Memory.runningThreads--;
	
		Memory.mediumPriorityProcesses.remove(this);
		
		
		Memory.moveEndProcess=Instant.now();
		Memory.moveDurationProcess=Duration.between(Memory.moveStartProcess, Memory.moveEndProcess).toMillis()+Memory.moveDurationProcess;
		this.stop();
		Memory.move=null;

	}

}
