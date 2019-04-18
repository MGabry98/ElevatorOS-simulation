import java.util.PriorityQueue;

public class Elevator implements ElevatorFactory {
	String Screen;
	boolean Fan = false;
	boolean door = true;// opened if true

	public Elevator(Integer currentFloor) {
		GUI.gui.Memory.currentFloor = currentFloor;
		GUI.gui.Memory.destinationFloors = new PriorityQueue<Integer>();
		Screen = "";
	}

	public int nextDestionation() {
		return GUI.gui.Memory.destinationFloors.peek();
	}

	public int currentFloor() {
		return GUI.gui.Memory.currentFloor;
	}

	public void popDestination() {
		GUI.gui.Memory.destinationFloors.remove();
	}

	@Override
	public void addNewDestinatoin(int destination) {
		if (!GUI.gui.Memory.destinationFloors.contains((Integer) destination)) {

			GUI.gui.Memory.destinationFloors.add(destination);
		}
	}

	@Override
	public void moveUp() {
		GUI.gui.Memory.currentFloor++;

	}

	@Override
	public void moveDown() {
		GUI.gui.Memory.currentFloor--;
	}

	@Override
	public Direction direction() {
		if (GUI.gui.Memory.destinationFloors.size() > 0) {
			if (GUI.gui.Memory.currentFloor < GUI.gui.Memory.destinationFloors.peek()) {
				return Direction.Up;
			} else if (GUI.gui.Memory.currentFloor > GUI.gui.Memory.destinationFloors.peek()) {
				return Direction.Down;
			} else {
				GUI.gui.Memory.destinationFloors.remove();
				return Direction.Hold;
			}
		}
		return Direction.Hold;

	}

	@Override
	public ElevatorStatus status() {
		return (GUI.gui.Memory.destinationFloors.size() > 0) ? ElevatorStatus.ELEVATOR_OCCUPIED
				: ElevatorStatus.ELEVATOR_EMPTY;
	}
}
