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

	public static void main(String[] args) {
		Elevator elevator = new Elevator(0);

		System.out
				.println("Write 'floor+number of floor' to go to the requested floor"
						+ '\n'
						+ "write 'time' to show the time on the screen"
						+ '\n'
						+ "write 'current floor' to show the current floor on the screen"
						+ '\n'
						+ "Write 'screen' to print elevator screen in console"
						+ '\n'
						+ "write 'threads' to show number of currently running thread"
						+ '\n');
		while (true) {
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();

			if (input.equalsIgnoreCase("time")) {
				if (!Memory.noPriorityProcess.contains(time)) {
					time = new ScreenTimeProcess(elevator);
					if (Memory.noPriorityProcess.contains(currentFloor)) {
						currentFloor.stop();
						System.out.println(currentFloor.ThreadName
								+ " Thread has been stopped");
						runningThreads--;
						Memory.noPriorityProcess.remove(currentFloor);
					}
					time.start();
					runningThreads++;
					System.out.println(time.ThreadName
							+ " Thread is running now");
					Memory.noPriorityProcess.add(time);
				} else {
					System.out.println("Already running");
				}
			}

			else if (input.equalsIgnoreCase("current floor")) {
				if (!Memory.noPriorityProcess.contains(currentFloor)) {
					currentFloor = new ScreenCurrentFloorProcess(elevator);
					if (Memory.noPriorityProcess.contains(time)) {
						time.stop();
						System.out.println(time.ThreadName
								+ " Thread has been stopped");
						runningThreads--;
						Memory.noPriorityProcess.remove(time);
					}
					currentFloor.start();
					System.out.println(currentFloor.ThreadName
							+ " Thread is running now");
					runningThreads++;
					Memory.noPriorityProcess.add(currentFloor);

				} else {
					System.out.println("Already running");
				}
			}

			else if (input.equalsIgnoreCase("screen")) {
				System.out.println(elevator.Screen);

			} else if (input.equalsIgnoreCase("threads")) {
				System.out.println("Number of currently running threads is: "
						+ runningThreads);

				// }else if (input.startsWith("request from ")) {
				// if (!Memory.highPriorityProcesses.contains(close)) {
				// close = new CloseDoorProcess(elevator);
				// Memory.highPriorityProcesses.add(close);
				// }
				// if (!Memory.noPriorityProcess.contains(fan)) {
				// fan = new FanProcess(elevator);
				// fan.start();
				// System.out.println(fan.ThreadName
				// + " Thread is running now");
				// Memory.noPriorityProcess.add(fan);
				// runningThreads++;
				// }
				// int x = Integer.parseInt(input.substring(13));
				// elevator.addNewDestinatoin(x);
				// if (!Memory.mediumPriorityProcesses.contains(move)) {
				// move = new MoveProcess(elevator);
				// // move.start();
				// // System.out.println(move.ThreadName
				// // + " Thread is running now");
				// Memory.mediumPriorityProcesses.add(move);
				// // runningThreads++;
				// }
				// if (!Memory.lowPriorityProcesses.contains(open)) {
				// open = new OpenDoorProcess(elevator);
				// Memory.lowPriorityProcesses.add(open);
				// }

			} else if (input.startsWith("floor ")) {
				int x = Integer.parseInt(input.substring(6));
				if (x <= 6 && x >= 0) {
					if (!Memory.noPriorityProcess.contains(fan)) {
						fan = new FanProcess(elevator);
						fan.start();
						System.out.println(fan.ThreadName
								+ " Thread is running now");
						Memory.noPriorityProcess.add(fan);
						runningThreads++;
					}
					if (!Memory.highPriorityProcesses.contains(close)) {
						close = new CloseDoorProcess(elevator);
						Memory.highPriorityProcesses.add(close);
						close.start();
						System.out.println(close.ThreadName
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
								System.out.println(move.ThreadName
										+ " Thread is running now");
								runningThreads++;
							}
						}
					}
					if (!Memory.lowPriorityProcesses.contains(open)) {
						open = new OpenDoorProcess(elevator);
						Memory.lowPriorityProcesses.add(open);
						open.start();
						System.out.println(open.ThreadName
								+ " Thread is running now");
						runningThreads++;
					}
					while (fan != null) {
						if (Memory.lowPriorityProcesses.isEmpty()) {
							runningThreads--;
							Memory.noPriorityProcess.remove(Control.fan);
							System.out.println(Control.fan.ThreadName
									+ " Thread has been stopped");
							fan.stop();
							fan = null;
						}
					}
				}else
					System.out.println("please enter a valid number from 0 to 6");
			}

			else
				System.out.println("please enter a valid word");

		}

	}
}
