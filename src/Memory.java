import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Memory {
	static Queue<Process> highPriorityProcesses=new LinkedList<Process>();
	static Queue<Process> mediumPriorityProcesses=new LinkedList<Process>();
	static Queue<Process> lowPriorityProcesses=new LinkedList<Process>();
	static ArrayList<Process> noPriorityProcess=new ArrayList<Process>();
}
