import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Control {

	static Queue<Thread> runningprocesses=new LinkedList<Thread>();
	static int runningThreads=0;
	static ScreenTimeThread time;
	static ScreenCurrentFloorThread currentFloor;
	static FanThread fan;
	static MoveThread move;
	
	public static void main(String [] args){
		Elevator elevator= new Elevator(0);
		
		
		System.out.println("Write 'floor+number of floor' to go to the requested floor"+'\n'+
				"write 'time' to show the time on the screen"+'\n'+"write 'current floor' to show the current floor on the screen"
				+'\n'+"Write 'screen' to print elevator screen in console"
				+'\n'+"write 'threads' to show number of currently running thread"+'\n'+
				"Write 'request from +number of floor' to call the elevator to requested floor"+'\n');
		while (true){
			Scanner sc= new Scanner(System.in);
			String input = sc.nextLine();
			
//			if(!elevator.destinationFloors.isEmpty()&&!runningprocesses.contains(fan)){
//				fan=new FanThread(elevator);
//				fan.start();
//				System.out.println(fan.ThreadName+" Thread is running now");
//				runningprocesses.add(fan);
//				runningThreads++;
//			}
//			else{
//				if(runningprocesses.contains(fan)){
//					fan.stop();
//					System.out.println(fan.ThreadName+" Thread has been stopped");
//					runningprocesses.remove(fan);
//					runningThreads--;
//			}
//			}
			
			if(input.equalsIgnoreCase("time")){
				if(!runningprocesses.contains(time)){
				time=new ScreenTimeThread(elevator);
				if(runningprocesses.contains(currentFloor)){
						currentFloor.stop();
						System.out.println(currentFloor.ThreadName+" Thread has been stopped");
						runningThreads--;
						runningprocesses.remove(currentFloor);
				}
				time.start();
				runningThreads++;
				System.out.println(time.ThreadName+" Thread is running now");
				runningprocesses.add(time);
			}else{
				System.out.println("Already running");
			}
			}
			
			else if(input.equalsIgnoreCase("current floor")){
				if(!runningprocesses.contains(currentFloor)){
				currentFloor=new ScreenCurrentFloorThread(elevator);
				if(runningprocesses.contains(time)){
						time.stop();
						System.out.println(time.ThreadName+" Thread has been stopped");
						runningThreads--;
						runningprocesses.remove(time);
				}
				currentFloor.start();
				System.out.println(currentFloor.ThreadName+" Thread is running now");
				runningThreads++;
				runningprocesses.add(currentFloor);

				}else{
					System.out.println("Already running");
				}
			}
			
			else if(input.equalsIgnoreCase("screen")){
				System.out.println(elevator.Screen);
			}
			else if(input.equalsIgnoreCase("threads")){
				System.out.println("Number of currently running threads is: "+runningThreads);
			}
			else if(input.startsWith("request from ")){
				if(!runningprocesses.contains(fan)){
					fan=new FanThread(elevator);
					fan.start();
					System.out.println(fan.ThreadName+" Thread is running now");
					runningprocesses.add(fan);
					runningThreads++;
				}
				int x=Integer.parseInt(input.substring(13));
				elevator.addNewDestinatoin(x);
				if(!runningprocesses.contains(move)){
					move=new MoveThread(elevator);
					move.start();
					System.out.println(move.ThreadName+" Thread is running now");
					runningprocesses.add(move);
					runningThreads++;
				}
				
			}
			else if(input.startsWith("floor ")){
				if(!runningprocesses.contains(fan)){
					fan=new FanThread(elevator);
					fan.start();
					System.out.println(fan.ThreadName+" Thread is running now");
					runningprocesses.add(fan);
					runningThreads++;
				}
				int x=Integer.parseInt(input.substring(6));
				elevator.addNewDestinatoin(x);
				if(!runningprocesses.contains(move)){
					move=new MoveThread(elevator);
					move.setPriority(10);
					move.start();
					System.out.println(move.ThreadName+" Thread is running now");
					runningprocesses.add(move);
					runningThreads++;
				}
			}
			else 
				System.out.println("please enter a valid word");
			
			
			
			
			
//			while(!processes.isEmpty()){
//				processes.peek().start();
//				processes.remove();
//			}
			
			
		}
		

	}
}
