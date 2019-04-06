package validaciones;

public class BoolIsNumber {
	protected static boolean isFloat(String input) {
		try {
			Float.parseFloat(input);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}
	
	protected static boolean isInt(String input) {
		try {
			Integer.parseInt(input);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}
}
