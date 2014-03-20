package control;

public class Test {
	public static void main(String[] args) {
		byte b;
		b = Converter.getSerialByte(0, 0, 8+4+2+1);
		System.out.println(b == getByte("11110000"));
		b = Converter.getSerialByte(3, 0, 8+1);
		System.out.println(b == getByte("10010110"));
		b = Converter.getSerialByte(2, 1, 1);
		System.out.println(b == getByte("00011100"));
	}
	
	private static byte getByte(String s) {
		return (byte) Integer.parseInt(s,2);
	}
}
