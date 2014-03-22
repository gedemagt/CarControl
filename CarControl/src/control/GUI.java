package control;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class GUI extends JPanel {

	private JButton startStop;
	private JComboBox ports;
	private JComboBox baud;
	private Action leftAction, rightAction, upAction, downAction;
	private Communicator c;
	private JLabel j;
	private JScrollPane p;
	private JCheckBox cb;
	private ActionControl control;
	
	public GUI(ActionControl control, Communicator c) {
		this(control.getLeftAction(), control.getRightAction(), control.getUpAction(), control.getDownAction());
		this.c = c;
		this.control = control;
		setupJLabel();
		setupButtons();
		setupTextArea();
		setupComboBox();
		setupCheckBox();
		setupLayout();
	}
	
	private void setupCheckBox() {
		cb = new JCheckBox("Invert signal");
		cb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Converter.setReversed(cb.isSelected());
				Log.log("Sending inverted signal: " + cb.isSelected());
				
			}
		});
	}
	
	public void setBoxesEnabled(boolean b) {
		ports.setEnabled(b);
		baud.setEnabled(b);
	}
	
	private void setupTextArea() {
		p = new JScrollPane(Log.getTextArea());
		p.setBorder(new TitledBorder("Log"));
		p.setPreferredSize(new Dimension(300,200));
		p.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
		    public void adjustmentValueChanged(AdjustmentEvent e) {  
		        e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
		    }
		});
	}

	private void setupJLabel() {
		j = new JLabel();
		
	}

	private GUI(Action leftAction, Action rightAction, Action upAction, Action downAction) {
		this.leftAction = leftAction;
		this.rightAction = rightAction;
		this.upAction = upAction;
		this.downAction = downAction;

		
	}

	private void setupComboBox() {
		ports = new JComboBox(c.getPortNames());
		ports.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				c.setPort((String) e.getItem());
				
			}
		});
		Integer[] baud = new Integer[]{300, 1200, 9600};
		this.baud = new JComboBox(baud);
		this.baud.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) c.setBaud((Integer) e.getItem());
				
			}
		});
		
	}
	public void updateString(String s) {
		j.setText(s);
	}

	private void setupLayout() {
		setLayout(new BorderLayout());
//		add(up, BorderLayout.NORTH);
//		add(down, BorderLayout.SOUTH);
//		add(left, BorderLayout.WEST);
//		add(right, BorderLayout.EAST);
		add(p, BorderLayout.EAST);
		JPanel j = new JPanel();
		j.setLayout(new BoxLayout(j, BoxLayout.Y_AXIS));
		j.add(ports);
		j.add(baud);
		j.add(this.j);
		j.add(Box.createVerticalStrut(70));
		j.add(cb);
		j.add(startStop);
		add(j, BorderLayout.CENTER);
		
	}

	private void setupButtons() {
		startStop = new JButton("Start");
		startStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(startStop.getText().equals("Start")) {
					Main.startTimer();
					startStop.setText("Stop");
				}
				else {
					Main.stopTimer();
					startStop.setText("Start");
				}
			}
		});
		
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "up");
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "down");
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "left");
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "right");
		
		getActionMap().put("up", upAction);
		getActionMap().put("down", downAction);
		getActionMap().put("left", leftAction);
		getActionMap().put("right", rightAction);
		
	}
	
	

}
