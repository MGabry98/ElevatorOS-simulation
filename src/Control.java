import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Control {

	static Instant start;

	public static void time(Elevator elevator) {

		if (!GUI.gui.Memory.noPriorityProcess.contains(GUI.gui.Memory.time)) {
			GUI.gui.Memory.time = new ScreenTimeProcess(elevator);
			if (GUI.gui.Memory.noPriorityProcess.contains(GUI.gui.Memory.currentFloorProcess)) {
				GUI.gui.Memory.currentFloorProcess.stop();
				GUI.gui.Memory.currentFloorEndProcess = Instant.now();
				GUI.gui.Memory.currentFloorDurationProcess = Duration
						.between(GUI.gui.Memory.currentFloorStartProcess, GUI.gui.Memory.currentFloorEndProcess).toMillis()
						+ GUI.gui.Memory.currentFloorDurationProcess;
				IO.print(GUI.gui.Memory.currentFloorProcess.ThreadName + " Process has been stopped");
				GUI.gui.Memory.runningThreads--;
				GUI.gui.Memory.noPriorityProcess.remove(GUI.gui.Memory.currentFloorProcess);
			}
			start = Instant.now();
			GUI.gui.Memory.time.start();
			GUI.gui.Memory.runningThreads++;
			IO.print(GUI.gui.Memory.time.ThreadName + " Process is running now");
			GUI.gui.Memory.noPriorityProcess.add(GUI.gui.Memory.time);
		} else
			IO.print("Already running");

	}

	public static void currentfloor(Elevator elevator) {
		if (!GUI.gui.Memory.noPriorityProcess.contains(GUI.gui.Memory.currentFloorProcess)) {
			GUI.gui.Memory.currentFloorProcess = new ScreenCurrentFloorProcess(elevator);
			if (GUI.gui.Memory.noPriorityProcess.contains(GUI.gui.Memory.time)) {
				
				GUI.gui.Memory.time.stop();
				GUI.gui.Memory.timeendprocess = Instant.now();
				GUI.gui.Memory.timedurationprocess = Duration.between(GUI.gui.Memory.timestartprocess, GUI.gui.Memory.timeendprocess)
						.toMillis() + GUI.gui.Memory.timedurationprocess;

				IO.print(GUI.gui.Memory.time.ThreadName + " Process has been stopped");
				GUI.gui.Memory.runningThreads--;
				GUI.gui.Memory.noPriorityProcess.remove(GUI.gui.Memory.time);
			}
			GUI.gui.Memory.currentFloorProcess.start();
			IO.print(GUI.gui.Memory.currentFloorProcess.ThreadName + " Process is running now");
			GUI.gui.Memory.runningThreads++;
			GUI.gui.Memory.noPriorityProcess.add(GUI.gui.Memory.currentFloorProcess);

		} else
			IO.print("Already running");

	}

	public static void floor(Elevator elevator, String input) {
		int x = Integer.parseInt(input.substring(6));

		if (GUI.gui.Memory.currentFloor != x) {
			if (x <= GUI.gui.Memory.maxFloor && x >= GUI.gui.Memory.minFloor) {
				if (!GUI.gui.Memory.noPriorityProcess.contains(GUI.gui.Memory.fan)) {

					GUI.gui.Memory.fan = new FanProcess(elevator);
//					GUI.gui.screen.setIcon(new ImageIcon("src/fan2.gif"));
//					GUI.gui.fan=new JLabel(new ImageIcon("src/fan2.gif"));
//					GUI.gui.setVisible(true);
					GUI.gui.Memory.fan.start();
					GUI.gui.Memory.fanStartProcess = Instant.now();
					IO.print(GUI.gui.Memory.fan.ThreadName + " Process is running now");
					GUI.gui.Memory.noPriorityProcess.add(GUI.gui.Memory.fan);
					GUI.gui.Memory.runningThreads++;
				}
				if (!GUI.gui.Memory.highPriorityProcesses.contains(GUI.gui.Memory.close)) {
					GUI.gui.Memory.close = new CloseDoorProcess(elevator);
					GUI.gui.Memory.highPriorityProcesses.add(GUI.gui.Memory.close);
					IO.print(GUI.gui.Memory.close.ThreadName + " Process is running now");
					GUI.gui.Memory.closeStartProcess = Instant.now();


					GUI.gui.Memory.runningThreads++;
					GUI.gui.Memory.close.start();

					try {

						TimeUnit.MILLISECONDS.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("Cant wait i am in hurry");
					}
				}


				elevator.addNewDestinatoin(x);
				if (!GUI.gui.Memory.mediumPriorityProcesses.contains(GUI.gui.Memory.move)) {
					GUI.gui.Memory.move = new MoveProcess(elevator);
					GUI.gui.Memory.mediumPriorityProcesses.add(GUI.gui.Memory.move);
					while (!GUI.gui.Memory.mediumPriorityProcesses.isEmpty()) {

						if (GUI.gui.Memory.highPriorityProcesses.isEmpty() && !GUI.gui.Memory.move.isAlive()) {
							GUI.gui.Memory.moveStartProcess = Instant.now();
							GUI.gui.Memory.move.start();
							IO.print(GUI.gui.Memory.move.ThreadName + " Process is running now");
							GUI.gui.Memory.runningThreads++;
						}
					}
				}
				if (!GUI.gui.Memory.lowPriorityProcesses.contains(GUI.gui.Memory.open)) {
					GUI.gui.Memory.open = new OpenDoorProcess(elevator);
					GUI.gui.Memory.lowPriorityProcesses.add(GUI.gui.Memory.open);
					GUI.gui.Memory.openStartProcess = Instant.now();

					IO.print(GUI.gui.Memory.open.ThreadName + " Process is running now");

					try {

						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("Cant wait i am in hurry");
					}
					GUI.gui.Memory.open.start();
					try {

						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("Cant wait i am in hurry");
					}
					GUI.gui.Memory.runningThreads++;
				}

				while (GUI.gui.Memory.fan != null) {
					if (GUI.gui.Memory.lowPriorityProcesses.isEmpty()) {
						GUI.gui.Memory.runningThreads--;
						GUI.gui.Memory.noPriorityProcess.remove(GUI.gui.Memory.fan);
						IO.print(GUI.gui.Memory.fan.ThreadName + " Process has been stopped");
						GUI.gui.Memory.fanEndProcess = Instant.now();
						GUI.gui.Memory.fanDurationProcess = Duration
								.between(GUI.gui.Memory.fanStartProcess, GUI.gui.Memory.fanEndProcess).toMillis()
								+ GUI.gui.Memory.fanDurationProcess;
						GUI.gui.Memory.fan.stop();
						GUI.gui.fan.setText("Fan is off");
						GUI.gui.Memory.fan = null;
					} else {
						break;
					}
				}
			} else {
				IO.print("please enter a valid number from 0 to 7");

			}
		} else{
			IO.print("You are already in floor" + " " + x);
			GUI.gui.fan.setText("Fan is off");
		}
	}

}
