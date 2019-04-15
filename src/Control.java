import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
public class Control {

	static Instant start;





		public static void time(Elevator elevator){
			
		if (!Memory.noPriorityProcess.contains(Memory.time)) {
			Memory.time = new ScreenTimeProcess(elevator);
			if (Memory.noPriorityProcess.contains(Memory.currentFloorProcess)) {
				Memory.currentFloorProcess.stop();
				Memory.currentFloorEndProcess=Instant.now();
				Memory.currentFloorDurationProcess=Duration.between(Memory.currentFloorStartProcess, Memory.currentFloorEndProcess).toMillis()+Memory.currentFloorDurationProcess;
				IO.print(Memory.currentFloorProcess.ThreadName
						+ " Process has been stopped");
				Memory.runningThreads--;
				Memory.noPriorityProcess.remove(Memory.currentFloorProcess);
			}
			start= Instant.now();
			Memory.time.start();
			Memory.runningThreads++;
			IO.print(Memory.time.ThreadName
					+ " Process is running now");
			Memory.noPriorityProcess.add(Memory.time);
		} else 
			IO.print("Already running");
		
	}
	
	public static void currentfloor(Elevator elevator){
		if (!Memory.noPriorityProcess.contains(Memory.currentFloorProcess)) {
			Memory.currentFloorProcess = new ScreenCurrentFloorProcess(elevator);
			if (Memory.noPriorityProcess.contains(Memory.time)) {
				
				Memory.time.stop();
				Memory.timeendprocess = Instant.now();
				Memory.timedurationprocess=Duration.between(Memory.timestartprocess, Memory.timeendprocess).toMillis()+Memory.timedurationprocess;
				
				IO.print(Memory.time.ThreadName
						+ " Process has been stopped");
				Memory.runningThreads--;
				Memory.noPriorityProcess.remove(Memory.time);
			}
			Memory.currentFloorProcess.start();
			IO.print(Memory.currentFloorProcess.ThreadName
					+ " Process is running now");
			Memory.runningThreads++;
			Memory.noPriorityProcess.add(Memory.currentFloorProcess);

		} else 
			IO.print("Already running");
		

	}
	
	public static void floor(Elevator elevator,String input){
		int x = Integer.parseInt(input.substring(6));
		
		if(Memory.currentFloor!=x){
		if (x <= Memory.maxFloor && x >= Memory.minFloor) {
			if (!Memory.noPriorityProcess.contains(Memory.fan)) {
				
				Memory.fan = new FanProcess(elevator);
				Memory.fan.start();
				Memory.fanStartProcess=Instant.now();
				IO.print(Memory.fan.ThreadName
						+ " Process is running now");
				Memory.noPriorityProcess.add(Memory.fan);
				Memory.runningThreads++;
			}
			if (!Memory.highPriorityProcesses.contains(Memory.close)) {
				Memory.close = new CloseDoorProcess(elevator);
				Memory.highPriorityProcesses.add(Memory.close);
				IO.print(Memory.close.ThreadName
						+ " Process is running now");
				Memory.closeStartProcess=Instant.now();


		Memory.close.start();

				Memory.runningThreads++;
	try {
					
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
System.out.println("Cant wait i am in hurry");
}
			}

			elevator.addNewDestinatoin(x);
			if (!Memory.mediumPriorityProcesses.contains(Memory.move)) {
				Memory.move = new MoveProcess(elevator);
				Memory.mediumPriorityProcesses.add(Memory.move);
				while (!Memory.mediumPriorityProcesses.isEmpty()) {

					if (Memory.highPriorityProcesses.isEmpty()
							&& !Memory.move.isAlive()) {
						Memory.moveStartProcess=Instant.now();
						Memory.move.start();
						IO.print(Memory.move.ThreadName
								+ " Process is running now");
						Memory.runningThreads++;
					}
				}
			}
			if (!Memory.lowPriorityProcesses.contains(Memory.open)) {
				Memory.open = new OpenDoorProcess(elevator);
				Memory.lowPriorityProcesses.add(Memory.open);
				Memory.openStartProcess=Instant.now();
				
				IO.print(Memory.open.ThreadName
						+ " Process is running now");
				
				Memory.open.start();
	try {
					
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
System.out.println("Cant wait i am in hurry");
}
				Memory.runningThreads++;
			}
			
			while (Memory.fan != null) {
				if (Memory.lowPriorityProcesses.isEmpty()) {
					Memory.runningThreads--;
					Memory.noPriorityProcess.remove(Memory.fan);
					IO.print(Memory.fan.ThreadName
							+ " Process has been stopped");
					Memory.fanEndProcess=Instant.now();
					Memory.fanDurationProcess=Duration.between(Memory.fanStartProcess, Memory.fanEndProcess).toMillis()+Memory.fanDurationProcess;
					Memory.fan.stop();
					Memory.fan = null;
				}
				else {
					break;
				}
			}
		}else{
			IO.print("please enter a valid number from 0 to 7");

		}
		}else
			IO.print("You are already in floor"+" "+x);

	}
	
}
