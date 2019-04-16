import java.util.PriorityQueue;

public class Elevator implements ElevatorFactory {
	String Screen;
	boolean Fan = false;
	boolean door = true;// opened if true

	public Elevator(Integer currentFloor) {
		IO.Memory.currentFloor = currentFloor;
		IO.Memory.destinationFloors = new PriorityQueue<Integer>();
		Screen = "";
	}

	public int nextDestionation() {
		return IO.Memory.destinationFloors.peek();
	}

	public int currentFloor() {
		return IO.Memory.currentFloor;
	}

	public void popDestination() {
		IO.Memory.destinationFloors.remove();
	}

	@Override
	public void addNewDestinatoin(int destination) {
		if (!IO.Memory.destinationFloors.contains((Integer) destination)) {

			IO.Memory.destinationFloors.add(destination);
		}
	}

	@Override
	public void moveUp() {
		IO.Memory.currentFloor++;

	}

	@Override
	public void moveDown() {
		IO.Memory.currentFloor--;
	}

	@Override
	public Direction direction() {
		if (IO.Memory.destinationFloors.size() > 0) {
			if (IO.Memory.currentFloor < IO.Memory.destinationFloors.peek()) {
				return Direction.Up;
			} else if (IO.Memory.currentFloor > IO.Memory.destinationFloors.peek()) {
				return Direction.Down;
			} else {
				IO.Memory.destinationFloors.remove();
				return Direction.Hold;
			}
		}
		return Direction.Hold;

	}

	@Override
	public ElevatorStatus status() {
		return (IO.Memory.destinationFloors.size() > 0) ? ElevatorStatus.ELEVATOR_OCCUPIED
				: ElevatorStatus.ELEVATOR_EMPTY;
	}
}
