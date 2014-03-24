package control;

import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class Main {

	static JFrame main_frame;
	static GUI gui;
	static Communicator com;
	static ActionControl c;
	@SuppressWarnings({ "serial", "static-access" })
	public static void main(String[] args) {
		c = new ActionControl();
		com = new Communicator();
		gui = new GUI(c, com);
		gui.getInputMap(gui.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("X"), "pressed");
		
		gui.getActionMap().put("pressed", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Log.log("Current state: " + Converter.getString(c.getDirection(), c.getForwardBackwards(), c.getPower()));
				Log.log("Sent to serial: " + Converter.getSerialString(c.getDirection(), c.getForwardBackwards(), c.getPower()));

				
			}
			
		});

		
		main_frame = new JFrame();

		main_frame.add(gui);
		
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_frame.setVisible(true);
		main_frame.pack();
	}
	static Timer t;
	public static void startTimer() {
		t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				com.writeByte(Converter.getSerialByte(c.getDirection(), c.getForwardBackwards(), c.getPower()));
				gui.updateString(Converter.getString(c.getDirection(), c.getForwardBackwards(), c.getPower()));
			}
		}, 0, 100);
		gui.setBoxesEnabled(false);
		Log.log("Data stream started");
	}
	
	public static void stopTimer() {
		t.cancel();
		gui.setBoxesEnabled(true);
		Log.log("Data stream stopped");
	}

	
}
