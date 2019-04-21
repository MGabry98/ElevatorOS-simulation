

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;



import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI extends JFrame  implements ActionListener {
	Memory Memory = new Memory();
	Elevator elevator;
	static GUI gui;
	JLabel screen;
	JLabel fan;
	JPanel pb1;
	JLabel door;
	JLabel doorimage;
	JLabel destElev;
	JPanel middle;
	public GUI() {
		Memory.startRunning = Instant.now();
		JButton[] floorsbuttons = new JButton[5];
		for (int i = 0; i < floorsbuttons.length; i++) {
			JButton x = new JButton();
			x.addActionListener(this);
			x.setActionCommand("floor "+i);
			x.setIcon(new ImageIcon("src/"+i+".png"));
			floorsbuttons[i] = x;
		}

		setTitle("ElevatorOS");
//		setUndecorated(true);
		
		
		setSize(700, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		Image icon = Toolkit.getDefaultToolkit().getImage("src/Icon.png");
		setIconImage(icon);
		setContentPane(new JLabel(new ImageIcon("src/background.png")));
		
		setLayout(new BorderLayout());
		pb1 = new JPanel();
		pb1.setBackground(new Color(0,0,0,0));
		pb1.setPreferredSize(new Dimension(70, 490));
		destElev = new JLabel(new ImageIcon("src/0floor.png"));
		destElev.setPreferredSize(new Dimension(80,500));
		pb1.add(destElev);
		middle = new JPanel(new FlowLayout());
//		middle.add (new JLabel(new ImageIcon("src/middle.png")));
		middle.setBackground(Color.white);
		
		middle.setPreferredSize(new Dimension(200,420));
		screen = new JLabel();
		screen.setFont(new Font("Freestyle Script", Font.BOLD, 25));
		screen.setForeground(Color.BLACK);
		middle.add(screen);
//		fan = new JLabel(new ImageIcon("src/fan2.jpg"));
		fan = new JLabel("Fan is off");
		fan.setFont(new Font("Freestyle Script", Font.BOLD, 35));
		fan.setForeground(Color.CYAN);
		JPanel j = new JPanel();
		j.setPreferredSize(new Dimension(200,100));
		j.setBackground(new Color(0,0,0,0));
		j.setForeground(new Color(0,0,0,0));
		middle.add(j);
		middle.add(fan);
		
		door = new JLabel("Door is open  ");
		doorimage = new JLabel();
		
		ImageIcon A= new ImageIcon("src/door open.jpg");
		Icon s= resizeIcon(A,140,180);
		doorimage.setIcon(s);

	    door.setFont(new Font("Freestyle Script", Font.BOLD, 35));
		middle.add(door);
		middle.add(doorimage);
		middle.setBackground(Color.DARK_GRAY);

//		middle.setOpaque(false);
		JPanel fadya = new JPanel();
		fadya.setBackground(new Color(0,0,0,0));
		fadya.setPreferredSize(new Dimension(120,100));
		JPanel left = new JPanel(new FlowLayout());
		left.add(pb1);
		left.add(fadya);
		left.add(middle);
		left.setBackground(new Color(0,0,0,0));
		add(left,BorderLayout.WEST);
		JPanel floors = new JPanel(new GridLayout(3, 2));
		for (int i = 0; i < floorsbuttons.length; i++)
			floors.add(floorsbuttons[i]);
		floors.setPreferredSize(new Dimension (100,400));
		floors.setBackground(new Color(0,0,0,0));
		JPanel timeandfloor = new JPanel(new GridLayout(2,1));
		JButton time = new JButton("Show Time");
		time.setFont(new Font("Snap ITC", Font.PLAIN, 18));
		time.setForeground(Color.WHITE);
		time.setBackground(Color.DARK_GRAY);
		time.setPreferredSize(new Dimension(144,51));
		time.addActionListener(this);
		time.setActionCommand("time");
		JButton CurrentFloor = new JButton("Show floor");
		CurrentFloor.setFont(new Font("Snap ITC", Font.PLAIN, 17));
		CurrentFloor.setForeground(Color.WHITE);
		CurrentFloor.setBackground(Color.DARK_GRAY);
		CurrentFloor.setPreferredSize(new Dimension(145,51));
		CurrentFloor.addActionListener(this);
		CurrentFloor.setActionCommand("currentfloor");
		timeandfloor.add(time);
		timeandfloor.add(CurrentFloor);
		JPanel Right = new JPanel(new FlowLayout());
		Right.setPreferredSize(new Dimension(200,500));
		Right.add(timeandfloor);
		Right.add(floors);
		Right.setBackground(new Color(0,0,0,0));
		add(Right,BorderLayout.EAST);
		setSize(399, 399);
		setSize(700, 550);

		setVisible(true);
	}

	public static void main(String[] args) {
		 try (FileWriter writer = new FileWriter("src/logging.txt");
	             BufferedWriter bw = new BufferedWriter(writer)) {

	            bw.write("");

	        } catch (IOException e) {
	            System.err.format("IOException: %s%n", e);
	        }
		gui = new GUI();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(elevator==null)
			elevator = new Elevator(0);
		
		
		if(e.getActionCommand().startsWith("floor")){
//			Icon icon = new ImageIcon("src/fan2.gif");
//			fan.setIcon(new ImageIcon("src/fan2.gif"));
			fan.setText("Fan is on");
			fan.paintImmediately(fan.getVisibleRect());
			middle.paintImmediately(middle.getVisibleRect());
//			System.out.println(fan.getText());
//			this.setVisible(false);
//			fan.revalidate();
//			fan.repaint();
			door.setText("Door is closed");
			door.paintImmediately(door.getVisibleRect());
			middle.paintImmediately(middle.getVisibleRect());

			
			ImageIcon A2= new ImageIcon("src/door closed.jpg");
			Icon s1= resizeIcon(A2,140,180);
			doorimage.setIcon(s1);
			doorimage.paintImmediately(doorimage.getVisibleRect());
			middle.paintImmediately(middle.getVisibleRect());

			
//			doorimage.setIcon(new ImageIcon("src/door close.jpg"));
			Control.floor(elevator, e.getActionCommand(
					));
			
			door.setText("Door is open");
			
			ImageIcon A= new ImageIcon("src/door open.jpg");
			Icon s= resizeIcon(A,140,180);
			doorimage.setIcon(s);
			
//			destElev.setIcon(new ImageIcon("src/"+elevator.currentFloor()+"floor.png"));
//			destElev.setPreferredSize(new Dimension(80,500));
		}
		
		else if(e.getActionCommand()=="time"){
			Memory.timestartprocess = Instant.now();
			Control.time(elevator);
		}
		else if(e.getActionCommand()=="currentfloor"){
			Memory.currentFloorStartProcess = Instant.now();
			Control.currentfloor(elevator);
			
		}
		
		
	}
	private static Icon resizeIcon (ImageIcon icon, int resizedWidth, int resizedHeight){
		Image img= icon.getImage();
	    Image resizedImage= img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}
}
