import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Control {

	static Instant start;

	public static void time(Elevator elevator) {

		if (!IO.Memory.noPriorityProcess.contains(IO.Memory.time)) {
			IO.Memory.time = new ScreenTimeProcess(elevator);
			if (IO.Memory.noPriorityProcess.contains(IO.Memory.currentFloorProcess)) {
				IO.Memory.currentFloorProcess.stop();
				IO.Memory.currentFloorEndProcess = Instant.now();
				IO.Memory.currentFloorDurationProcess = Duration
						.between(IO.Memory.currentFloorStartProcess, IO.Memory.currentFloorEndProcess).toMillis()
						+ IO.Memory.currentFloorDurationProcess;
				IO.print(IO.Memory.currentFloorProcess.ThreadName + " Process has been stopped");
				IO.Memory.runningThreads--;
				IO.Memory.noPriorityProcess.remove(IO.Memory.currentFloorProcess);
			}
			start = Instant.now();
			IO.Memory.time.start();
			IO.Memory.runningThreads++;
			IO.print(IO.Memory.time.ThreadName + " Process is running now");
			IO.Memory.noPriorityProcess.add(IO.Memory.time);
		} else
			IO.print("Already running");

	}

	public static void currentfloor(Elevator elevator) {
		if (!IO.Memory.noPriorityProcess.contains(IO.Memory.currentFloorProcess)) {
			IO.Memory.currentFloorProcess = new ScreenCurrentFloorProcess(elevator);
			if (IO.Memory.noPriorityProcess.contains(IO.Memory.time)) {

				IO.Memory.time.stop();
				IO.Memory.timeendprocess = Instant.now();
				IO.Memory.timedurationprocess = Duration.between(IO.Memory.timestartprocess, IO.Memory.timeendprocess)
						.toMillis() + IO.Memory.timedurationprocess;

				IO.print(IO.Memory.time.ThreadName + " Process has been stopped");
				IO.Memory.runningThreads--;
				IO.Memory.noPriorityProcess.remove(IO.Memory.time);
			}
			IO.Memory.currentFloorProcess.start();
			IO.print(IO.Memory.currentFloorProcess.ThreadName + " Process is running now");
			IO.Memory.runningThreads++;
			IO.Memory.noPriorityProcess.add(IO.Memory.currentFloorProcess);

		} else
			IO.print("Already running");

	}

	public static void floor(Elevator elevator, String input) {
		int x = Integer.parseInt(input.substring(6));

		if (IO.Memory.currentFloor != x) {
			if (x <= IO.Memory.maxFloor && x >= IO.Memory.minFloor) {
				if (!IO.Memory.noPriorityProcess.contains(IO.Memory.fan)) {

					IO.Memory.fan = new FanProcess(elevator);
					IO.Memory.fan.start();
					IO.Memory.fanStartProcess = Instant.now();
					IO.print(IO.Memory.fan.ThreadName + " Process is running now");
					IO.Memory.noPriorityProcess.add(IO.Memory.fan);
					IO.Memory.runningThreads++;
				}
				if (!IO.Memory.highPriorityProcesses.contains(IO.Memory.close)) {
					IO.Memory.close = new CloseDoorProcess(elevator);
					IO.Memory.highPriorityProcesses.add(IO.Memory.close);
					IO.print(IO.Memory.close.ThreadName + " Process is running now");
					IO.Memory.closeStartProcess = Instant.now();


					IO.Memory.runningThreads++;
					IO.Memory.close.start();

					try {

						TimeUnit.MILLISECONDS.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("Cant wait i am in hurry");
					}
				}


				elevator.addNewDestinatoin(x);
				if (!IO.Memory.mediumPriorityProcesses.contains(IO.Memory.move)) {
					IO.Memory.move = new MoveProcess(elevator);
					IO.Memory.mediumPriorityProcesses.add(IO.Memory.move);
					while (!IO.Memory.mediumPriorityProcesses.isEmpty()) {

						if (IO.Memory.highPriorityProcesses.isEmpty() && !IO.Memory.move.isAlive()) {
							IO.Memory.moveStartProcess = Instant.now();
							IO.Memory.move.start();
							IO.print(IO.Memory.move.ThreadName + " Process is running now");
							IO.Memory.runningThreads++;
						}
					}
				}
				if (!IO.Memory.lowPriorityProcesses.contains(IO.Memory.open)) {
					IO.Memory.open = new OpenDoorProcess(elevator);
					IO.Memory.lowPriorityProcesses.add(IO.Memory.open);
					IO.Memory.openStartProcess = Instant.now();

					IO.print(IO.Memory.open.ThreadName + " Process is running now");

					try {

						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("Cant wait i am in hurry");
					}
					IO.Memory.open.start();
					try {

						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("Cant wait i am in hurry");
					}
					IO.Memory.runningThreads++;
				}

				while (IO.Memory.fan != null) {
					if (IO.Memory.lowPriorityProcesses.isEmpty()) {
						IO.Memory.runningThreads--;
						IO.Memory.noPriorityProcess.remove(IO.Memory.fan);
						IO.print(IO.Memory.fan.ThreadName + " Process has been stopped");
						IO.Memory.fanEndProcess = Instant.now();
						IO.Memory.fanDurationProcess = Duration
								.between(IO.Memory.fanStartProcess, IO.Memory.fanEndProcess).toMillis()
								+ IO.Memory.fanDurationProcess;
						IO.Memory.fan.stop();
						IO.Memory.fan = null;
					} else {
						break;
					}
				}
			} else {
				IO.print("please enter a valid number from 0 to 7");

			}
		} else
			IO.print("You are already in floor" + " " + x);

	}

}
