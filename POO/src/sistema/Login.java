package sistema;

import validaciones.MD5;

public class Login {
	
	/**
	 * Verifica que los datos ingresados coincidan con la cuenta deseada para iniciar.
	 * @param correo <code>String</code> del correo electrónico.
	 * @param password <code>String</code> de la contraseña (no encriptada).
	 * @return Si los datos son correctos retorna un instancia de <code>Usuario</code>,
	 * si no coinciden retorna <code>null</code>.
	 */
	public Usuario ingresar(String correo, String password) {
		MD5 hasher = new MD5();
		String hashedPswd = hasher.hashPassword(password);
		
		// query a la base de datos para verificar correo y contraseña:
		
		// si los datos son correctos:
//		if (true)
			// retorna un objeto de tipo Usuario con los datos de la cuenta,
			// que se estará enviando como argumento a las diferentes interfaces
			// para hacer uso de los atributos de la cuenta
//			return new Usuario(0, cor_usu, pas_usu, tip_usu, nom_usu, gen_usu, ins_usu, fac_usu);
		// si los datos no coinciden, se retorna null:
//		else return null;
		
		return null;
	}
}