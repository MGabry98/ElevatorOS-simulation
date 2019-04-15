import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Control {

	static int runningThreads = 0;
	static ScreenTimeProcess time;
	static ScreenCurrentFloorProcess currentFloor;
	static FanProcess fan;
	static MoveProcess move;
	static OpenDoorProcess open;
	static CloseDoorProcess close;

		public static void time(Elevator elevator){
		if (!Memory.noPriorityProcess.contains(time)) {
			time = new ScreenTimeProcess(elevator);
			if (Memory.noPriorityProcess.contains(currentFloor)) {
				currentFloor.stop();
				IO.print(currentFloor.ThreadName
						+ " Thread has been stopped");
				runningThreads--;
				Memory.noPriorityProcess.remove(currentFloor);
			}
			time.start();
			runningThreads++;
			IO.print(time.ThreadName
					+ " Thread is running now");
			Memory.noPriorityProcess.add(time);
		} else 
			IO.print("Already running");
		
	}
	
	public static void currentfloor(Elevator elevator){
		if (!Memory.noPriorityProcess.contains(currentFloor)) {
			currentFloor = new ScreenCurrentFloorProcess(elevator);
			if (Memory.noPriorityProcess.contains(time)) {
				time.stop();
				IO.print(time.ThreadName
						+ " Thread has been stopped");
				runningThreads--;
				Memory.noPriorityProcess.remove(time);
			}
			currentFloor.start();
			IO.print(currentFloor.ThreadName
					+ " Thread is running now");
			runningThreads++;
			Memory.noPriorityProcess.add(currentFloor);

		} else 
			IO.print("Already running");
		

	}
	
	public static void floor(Elevator elevator,String input){
		int x = Integer.parseInt(input.substring(6));
		
		if(elevator.currentFloor!=x){
		if (x <= 7 && x >= 0) {
			if (!Memory.noPriorityProcess.contains(fan)) {
				fan = new FanProcess(elevator);
				fan.start();
				IO.print(fan.ThreadName
						+ " Thread is running now");
				Memory.noPriorityProcess.add(fan);
				runningThreads++;
			}
			if (!Memory.highPriorityProcesses.contains(close)) {
				close = new CloseDoorProcess(elevator);
				Memory.highPriorityProcesses.add(close);
				close.start();
				IO.print(close.ThreadName
						+ " Thread is running now");
				runningThreads++;
			}

			elevator.addNewDestinatoin(x);
			if (!Memory.mediumPriorityProcesses.contains(move)) {
				move = new MoveProcess(elevator);
				Memory.mediumPriorityProcesses.add(move);
				while (!Memory.mediumPriorityProcesses.isEmpty()) {

					if (Memory.highPriorityProcesses.isEmpty()
							&& !move.isAlive()) {
						move.start();
						IO.print(move.ThreadName
								+ " Thread is running now");
						runningThreads++;
					}
				}
			}
			if (!Memory.lowPriorityProcesses.contains(open)) {
				open = new OpenDoorProcess(elevator);
				Memory.lowPriorityProcesses.add(open);
				open.start();
				IO.print(open.ThreadName
						+ " Thread is running now");
				runningThreads++;
			}
			while (fan != null) {
				if (Memory.lowPriorityProcesses.isEmpty()) {
					runningThreads--;
					Memory.noPriorityProcess.remove(Control.fan);
					IO.print(Control.fan.ThreadName
							+ " Thread has been stopped");
					fan.stop();
					fan = null;
				}
			}
		}else{
			IO.print("please enter a valid number from 0 to 7");

		}
		}else
			IO.print("You are already in floor"+" "+x);

	}
	
}
