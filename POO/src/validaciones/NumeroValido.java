package validaciones;

public class NumeroValido {
	public boolean isFloat(String input) {
		try {
			Float.parseFloat(input);
			return true;
		}
		catch (NumberFormatException e) {
			System.err.println("'" + input + "' no es flotante.");
			return false;
		}
	}
	
	public boolean isInt(String input) {
		try {
			Integer.parseInt(input);
			return true;
		}
		catch (NumberFormatException e) {
			System.err.println("'" + input + "' no es entero.");
			return false;
		}
	}
}
