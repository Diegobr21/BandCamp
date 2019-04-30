package validaciones;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase para generar un hash del algoritmo MD5.
 */
public class MD5 {
	
	/**
	 * Crea el hash de una contraseña de acuerdo al algoritmo MD5.
	 * @param message <code>String</code> que almacena la contraseña del usuario
	 * 					y será encriptada.
	 * @return el <code>String</code> del hash encriptado de la contraseña. 
	 */
	public static String hashPassword(String message) {
		try {
			// instancia para usar algoritmo MD5
			MessageDigest msgDigest = MessageDigest.getInstance("MD5");
			
			// se añaden bytes de la contraseña al digest
			msgDigest.update(message.getBytes());
			
			// obtener bytes del hash
			byte[] bytes = msgDigest.digest();
			
			// como los bytes están en decimal hay que pasarlo a hexadecimal
			StringBuilder stringBuilder = new StringBuilder();
			for (byte b : bytes) {
				// cada byte del arreglo se formatea a su equivalente hexadecimal
				String hex = String.format("%x", b);
				// y se va añadiendo
				stringBuilder.append(hex);
			}
			
			// obtener hash en hexadecimal
			return stringBuilder.toString();
		}
		catch (NoSuchAlgorithmException error) {
			 error.printStackTrace();
		}
		
		return null;
	}
}
