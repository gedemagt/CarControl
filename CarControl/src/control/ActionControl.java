package control;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

public class ActionControl {

	private Action leftAction, rightAction, upAction, downAction;

	private int throttle = 0;
	private int leftRight = 0;
	
	@SuppressWarnings("serial")
	public ActionControl() {
		leftAction = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				leftRight--;
				if(leftRight < -1) leftRight = -1;
			}
		};
		
		rightAction = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				leftRight ++;
				if(leftRight > 1) leftRight = 1;
			}
		};
		
		upAction = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				throttle++;
				if(throttle > 15) throttle = 15;
			}
		};
		
		downAction = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				throttle--;
				if(throttle < -15) throttle = -15;
			}
		};
	}
	
	public int getDirection() {
		if(leftRight == 1) return 2;
		if(leftRight == 0) return 0;
		if(leftRight == -1) return 1;
		else return 3;
	}
	
	public int getForwardBackwards() {
		if(throttle >= 0) return 1;
		else return 0;
	}
	
	public int getPower() {
		return Math.abs(throttle);
	}
	
	public Action getLeftAction() {
		return leftAction;
	}

	public Action getRightAction() {
		return rightAction;
	}

	public Action getUpAction() {
		return upAction;
	}

	public Action getDownAction() {
		return downAction;
	}
	
}
