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
		String p = getString(direction, forwardBackward, power);
		if(reverse) p = switchZeroOnes(p);
		return p;
		
	}
	
	public static String getString(int direction, int forwardBackward, int power) {
		
		String p = "";
		String powerString = Integer.toBinaryString(power);
		String fbString = Integer.toBinaryString(forwardBackward);
		String directionString = Integer.toBinaryString(direction);
		if(directionString.length() == 1) directionString = "0" + directionString;
		while(powerString.length() < 4) powerString = "0" + powerString;
		p = powerString + fbString + directionString + "0";
		return p;
		
	}
	
	private static String switchZeroOnes(String input) {
	
		String result = "";
		for(int i=0; i<input.length(); i++) {
			if(input.charAt(i) == "0".charAt(0)) result += "1";
			else result += "0";
		}
		return result;
	}

	private static boolean reverse;
	
	public static void setReversed(boolean reversed) {
		reverse = reversed;
	}
	
}
