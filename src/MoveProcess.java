public class MoveProcess extends Process {

	Elevator elevator;


	public MoveProcess(Elevator elevator) {
		this.elevator = elevator;
		super.ThreadName = "Move";
	}

	@Override
	public void run() {
		while (!elevator.destinationFloors.isEmpty()) {

			Direction d;
			int temp = elevator.nextDestionation();
			while (elevator.direction() != Direction.Hold) {
				
				d = elevator.direction();
				if (d == Direction.Up) {
					elevator.moveUp();
					IO.print("Elevator is moving Up  ,Elevator next destination is "
									+ elevator.nextDestionation()
									+ ",current floor " + elevator.currentFloor);
				} else if (d == Direction.Down) {
					elevator.moveDown();
					IO.print("Elevator is moving down  ,Elevator next destination is "
									+ elevator.nextDestionation()
									+ ",current floor " + elevator.currentFloor);
				}

			}

		}

		
		
		
		IO.print(this.ThreadName + " Thread has been stopped");
		
		Control.runningThreads--;
	
		Memory.mediumPriorityProcesses.remove(this);
		this.stop();
		
		
		Control.move=null;

	}

}
