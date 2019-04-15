import java.util.PriorityQueue;

public class Elevator implements ElevatorFactory {
	String Screen;
	boolean Fan = false;
	boolean door = true;// opened if true

	public Elevator(Integer currentFloor) {
		Memory.currentFloor = currentFloor;
		Memory.destinationFloors = new PriorityQueue<Integer>();
		Screen = "";
	}

	public int nextDestionation() {
		return Memory.destinationFloors.peek();
	}

	public int currentFloor() {
		return Memory.currentFloor;
	}

	public void popDestination() {
		Memory.destinationFloors.remove();
	}

	@Override
	public void addNewDestinatoin(int destination) {
		if (!Memory.destinationFloors.contains((Integer) destination)) {

			Memory.destinationFloors.add(destination);
		}
	}

	@Override
	public void moveUp() {
		Memory.currentFloor++;

	}

	@Override
	public void moveDown() {
		Memory.currentFloor--;
	}

	@Override
	public Direction direction() {
		if (Memory.destinationFloors.size() > 0) {
			if (Memory.currentFloor < Memory.destinationFloors.peek()) {
				return Direction.Up;
			} else if (Memory.currentFloor > Memory.destinationFloors.peek()) {
				return Direction.Down;
			} else {
				Memory.destinationFloors.remove();
				return Direction.Hold;
			}
		}
		return Direction.Hold;

	}

	@Override
	public ElevatorStatus status() {
		return (Memory.destinationFloors.size() > 0) ? ElevatorStatus.ELEVATOR_OCCUPIED
				: ElevatorStatus.ELEVATOR_EMPTY;
	}
}
