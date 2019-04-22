import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;


public class ProcessTime {
	static Instant startTime;
	static Queue<Instant> processrunstart = new LinkedList<Instant>();
	static Queue<Instant> processrunstop = new LinkedList<Instant>();
	static Queue<Long> processmemorysize=new LinkedList<Long>();
	static Queue<String> runningNow=new LinkedList<String>();
	static Queue<String> stoppedNow=new LinkedList<String>();

	public static void allQueues() {
		IO.print("TOTAL MEMORY USED: "+(ObjectSizeCalculator
						.getObjectSize(	GUI.gui.Memory)));
		while(!processrunstart.isEmpty() && !runningNow.isEmpty()&&!processmemorysize.isEmpty()) {
			IO.print(runningNow.remove()+" is running started at: "+processrunstart.remove()+'\n'+
					"IT used: "+processmemorysize.remove());
		}
		while(!processrunstop.isEmpty()&& !stoppedNow.isEmpty()) {
			IO.print(stoppedNow.remove()+" stopped at instant: "+processrunstop.remove());
		}
	}
}
