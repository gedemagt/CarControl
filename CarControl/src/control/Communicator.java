package control;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class Communicator {

	private String port;
	private SerialPort serialPort;
	private int baud;
	
	public Communicator() {
		if(getPortNames().length == 0 ) Log.log("No ports detected");
		else {
			port = getPortNames()[0];
			baud = 300;
			restartPort();
		}
	}
	
	public void writeByte(byte b) {
        try {
            if(serialPort != null) serialPort.writeByte(b);
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
	}
	
	public void setPort(String port) {
		this.port = port;
		restartPort();
	}
	
	public void setBaud(int baud) {
		this.baud = baud;
		restartPort();
	}
	
	private void restartPort() {
		if(getPortNames().length == 0 ) {
			Log.log("No ports detected");
			return;
		}
		boolean b = false;
		try {
			if(serialPort != null && serialPort.isOpened()) serialPort.closePort();
			serialPort = new SerialPort(port);
			b = serialPort.openPort();
			serialPort.setParams(baud, 
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
		Log.log("Port opened: " + serialPort.getPortName() + "; Baud = " + baud + " : Status " + b);
	}
	
	public String[] getPortNames() {
		return SerialPortList.getPortNames();
	}
}
