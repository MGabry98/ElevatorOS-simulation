public class MoveThread extends Thread {

	Elevator elevator;
	String ThreadName;

	public MoveThread(Elevator elevator) {
		this.elevator = elevator;
		ThreadName = "Move";
	}

	@Override
	public void run() {
		while (!elevator.destinationFloors.isEmpty()) {
			CloseDoorThread close = new CloseDoorThread(elevator);
			close.setPriority(2);
			if (!Control.runningprocesses.contains(close)&&elevator.door) {
				close.start();
				System.out.println(close.ThreadName + " Thread is running now");
				Control.runningprocesses.add(close);
				Control.runningThreads++;
				if(!elevator.destinationFloors.isEmpty())
				{
					try {
						this.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("error bdan");
					}
				}
			}
			Direction d;
			int temp = elevator.nextDestionation();
			while (elevator.direction() != Direction.Hold) {
				
//				if (elevator.door) {
//					close = new CloseDoorThread(elevator);
//					close.start();
//					System.out.println(close.ThreadName
//							+ " Thread is running now");
//					Control.runningprocesses.add(close);
//					Control.runningThreads++;
//					
//				}
				d = elevator.direction();
				if (d == Direction.Up) {
					elevator.moveUp();
					System.out
							.println("Elevator is moving Up  ,Elevator next destination is "
									+ elevator.nextDestionation()
									+ ",current floor " + elevator.currentFloor);
				} else if (d == Direction.Down) {
					elevator.moveDown();
					System.out
							.println("Elevator is moving down  ,Elevator next destination is "
									+ elevator.nextDestionation()
									+ ",current floor " + elevator.currentFloor);
				}

			}
			if (elevator.currentFloor == temp) {
				OpenDoorThread open = new OpenDoorThread(elevator);
				open.setPriority(2);
				open.start();
				
				System.out.println(open.ThreadName
						+ " Thread is running now");
				Control.runningprocesses.add(open);
				Control.runningThreads++;
				if(!elevator.destinationFloors.isEmpty())
				{
					try {
						this.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("error bdan");
					}
				}
			}
		}

		Control.runningprocesses.remove(this);
		Control.runningThreads--;
		System.out.println(this.ThreadName + " Thread has been stopped");
		Control.fan.stop();
		Control.runningprocesses.remove(Control.fan);
		Control.runningThreads--;
		System.out.println(Control.fan.ThreadName + " Thread has been stopped");
		this.stop();
	}

}
