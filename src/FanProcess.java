import javax.swing.ImageIcon;

public class FanProcess extends Process {
	Elevator elevator;

	public FanProcess(Elevator elevator) {
		this.elevator = elevator;
		super.ThreadName = "Fan";
	}

	@Override
	public void run() {

		while (true) {
			elevator.Fan = true;
		}

	}

}
