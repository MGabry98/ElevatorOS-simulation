import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class IO {

	public static void main(String[] args) {
		Memory.startRunning=Instant.now();
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
						+ "write 'process' to show number of currently running thread"
						+ '\n'+"Write 'bye' to turn off the elevator"
						+ '\n');
		while (true) {
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();
			if(input.equalsIgnoreCase("bye"))
			{ 		Memory.endRunning=Instant.now();
				Memory.totaltime=Duration.between(Memory.startRunning,Memory.endRunning).toMillis();
				System.out.println("Bye"+'\n'+"Total time spent running the elevator is: "+Memory.totaltime+ " milliseconds");
				System.out.println(Duration.between(Memory.startRunning,Memory.endRunning).toMillis()/1000 +" Seconds");
				System.out.println(Duration.between(Memory.startRunning,Memory.endRunning).toMinutes() +" Minutes"+'\n');
				System.out.println("Total time for Move Process: "+Memory.moveDurationProcess+" milliseconds"+'\n'+
						"Total time for Open Door Process: "+Memory.openDurationProcess+" milliseconds"+'\n'+
						"Total time for Close Door Process: "+Memory.closeDurationProcess+" milliseconds"+'\n'+
						"Total time for fan Process: "+Memory.fanDurationProcess+" milliseconds"+'\n'+
						"Total time for time Process: "+Memory.timedurationprocess+" milliseconds"+'\n'+
						"Total time for CurrentFloor Process: "+Memory.currentFloorDurationProcess+" milliseconds"+'\n');
				
				
				
				if(Memory.time!=null && Memory.time.isAlive())
				Memory.time.stop();
				if(Memory.currentFloorProcess!=null && Memory.currentFloorProcess.isAlive())
					Memory.currentFloorProcess.stop();
				break;
			}
			else
			if (input.equalsIgnoreCase("time")) {
				Memory.timestartprocess=Instant.now();
				Control.time(elevator);}
			else if (input.equalsIgnoreCase("current floor")) {
				Memory.currentFloorStartProcess=Instant.now();
				Control.currentfloor(elevator);}
			else if (input.equalsIgnoreCase("screen"))
				IO.print(elevator.Screen);
			else if (input.equalsIgnoreCase("process"))
				IO.print("Number of currently running threads is: "
						+ Memory.runningThreads);
			else if (input.startsWith("floor "))
				Control.floor(elevator, input);
			else
				IO.print("please enter a valid word");

		}


	}

	public static void print(String s) {
		System.out.println(s);
	}
}
