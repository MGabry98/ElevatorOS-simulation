import java.util.Scanner;

public class IO {

	public static void main(String[] args) {
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
						+ '\n');
		while (true) {
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();

			if (input.equalsIgnoreCase("time"))
				Control.time(elevator);
			else if (input.equalsIgnoreCase("current floor"))
				Control.currentfloor(elevator);
			else if (input.equalsIgnoreCase("screen"))
				IO.print(elevator.Screen);
			else if (input.equalsIgnoreCase("process"))
				IO.print("Number of currently running threads is: "
						+ Control.runningThreads);
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
