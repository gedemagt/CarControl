package control;

public class Converter {

	/**
	 * LEft to right: power, forwardBackward, direction
	 * @param direction
	 * @param forwardBackward
	 * @param power
	 * @return
	 */
	public static byte getSerialByte(int direction, int forwardBackward, int power) {
		
		String p = getSerialString(direction, forwardBackward, power);
		byte b = (byte) Integer.parseInt(p,2);
		return b;
		
	}
	
	public static String getSerialString(int direction, int forwardBackward, int power) {
		
		String p = "";
		String powerString = Integer.toBinaryString(power);
		String fbString = Integer.toBinaryString(forwardBackward);
		String directionString = Integer.toBinaryString(direction);
		if(directionString.length() == 1) directionString = "0" + directionString;
		while(powerString.length() < 4) powerString = "0" + powerString;
		p = powerString + fbString + directionString + "0";
		return p;
		
	}
	
}
