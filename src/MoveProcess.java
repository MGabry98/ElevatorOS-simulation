import java.awt.Dimension;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MoveProcess extends Process {

	Elevator elevator;

	public MoveProcess(Elevator elevator) {
		this.elevator = elevator;
		super.ThreadName = "Move";
	}

	@Override
	public void run() {
		while (!GUI.gui.Memory.destinationFloors.isEmpty()) {

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
					GUI.gui.destElev.setIcon(new ImageIcon("src/"+elevator.currentFloor()+"floor.png"));
					GUI.gui.destElev.setPreferredSize(new Dimension(80,500));
					GUI.gui.destElev.paintImmediately(GUI.gui.destElev.getVisibleRect());

					IO.print("Elevator is moving Up  ,Elevator next destination is " + elevator.nextDestionation()
							+ ",current floor " + GUI.gui.Memory.currentFloor);
//					GUI.gui.setContentPane(new JLabel(new ImageIcon("src/background"+GUI.gui.Memory.currentFloor+".png")));
				} else if (d == Direction.Down) {
					elevator.moveDown();
					GUI.gui.destElev.setIcon(new ImageIcon("src/"+elevator.currentFloor()+"floor.png"));
					GUI.gui.destElev.setPreferredSize(new Dimension(80,500));
					GUI.gui.destElev.paintImmediately(GUI.gui.destElev.getVisibleRect());

					IO.print("Elevator is moving down  ,Elevator next destination is " + elevator.nextDestionation()
							+ ",current floor " + GUI.gui.Memory.currentFloor);
				}

			}

		}

		IO.print(this.ThreadName + " process has been stopped");

		GUI.gui.Memory.runningThreads--;

		GUI.gui.Memory.mediumPriorityProcesses.remove(this);

		GUI.gui.Memory.moveEndProcess = Instant.now();
		GUI.gui.Memory.moveDurationProcess = Duration.between(GUI.gui.Memory.moveStartProcess, GUI.gui.Memory.moveEndProcess)
				.toMillis() + GUI.gui.Memory.moveDurationProcess;
		this.stop();
		GUI.gui.Memory.move = null;

	}

}
