package control;

import javax.swing.JTextArea;

public class Log {
	
	private static JTextArea area;

	public static JTextArea getTextArea() {
		initArea();
		return area;
	}
	
	private static void initArea() {
		if(area != null) return;
		area = new JTextArea();
	}

	public static void log(String s) {
		initArea();
		area.append(s + "\n");
		area.repaint();
	}
	
}
