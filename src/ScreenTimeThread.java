import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ScreenTimeThread extends Thread{
	String ThreadName;
	Elevator elevator;
	public  ScreenTimeThread(Elevator elevator){
		ThreadName="Time";
		this.elevator=elevator;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
		 Calendar cal = Calendar.getInstance();
		    Date date=cal.getTime();
		    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		    String formattedDate=dateFormat.format(date);
		    elevator.Screen=("Current time: "+ formattedDate);
		    }
		    }

}
