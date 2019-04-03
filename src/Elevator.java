
import java.util.PriorityQueue;




public class Elevator implements ElevatorFactory {
  int currentFloor;
  PriorityQueue<Integer> destinationFloors;
  String Screen;
  boolean Fan=false;
  boolean door=true;//opened if true 
  
  public Elevator(Integer currentFloor) {
    this.currentFloor = currentFloor;
    this.destinationFloors = new PriorityQueue<Integer>();
//    destinationFloors.add(2);
//    destinationFloors.add(5);
    Screen="";
  }

  public int nextDestionation(){
    return this.destinationFloors.peek();
  }

  public int currentFloor(){
    return this.currentFloor;
  }

  public void popDestination(){
    this.destinationFloors.remove();
  }
  @Override
  public void addNewDestinatoin(int destination) {
	  if(!destinationFloors.contains((Integer)destination)){
		  
	  
    this.destinationFloors.add(destination);
	  }
  }

  @Override
  public void moveUp() {
    currentFloor++;
    
  }

  @Override
  public void moveDown() {
    currentFloor--;
  }

  @Override
  public Direction direction() {
    if (destinationFloors.size() > 0){
      if (currentFloor < destinationFloors.peek()){
        return Direction.Up;
      } else if (currentFloor > destinationFloors.peek()) {
        return Direction.Down;
      }
      else{
    	  destinationFloors.remove();
    	  return Direction.Hold;
      }
    }
    return Direction.Hold;
    
    
  }

  @Override
  public ElevatorStatus status() {
    return (destinationFloors.size() > 0)?ElevatorStatus.ELEVATOR_OCCUPIED:ElevatorStatus.ELEVATOR_EMPTY;
  }
}
