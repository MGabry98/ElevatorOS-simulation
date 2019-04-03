

public interface ElevatorFactory {
  public void moveUp();
  public void moveDown();
  public void addNewDestinatoin(int destination);
  public Direction direction();
  public ElevatorStatus status();

}
