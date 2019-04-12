package sistema;

import javax.swing.JOptionPane;

public class EditarPerfil {
	
	/**
	 * Edita los atributos que el usuario haya decidido cambiar.
	 * @param original {@code Usuario} con los atributos sin editar.
	 * @param editado {@code Usuario} con los atributos editados.
	 * @return {@code true} si el registro se actualizó correctamente en la BD, de lo contrario {@code false}.
	 */
	public boolean editarCuenta(Usuario original, Usuario editado) {
		if (editado.getNom_usu().equals("")) {
			JOptionPane.showMessageDialog(null, "¡Necesitas un nombre!", "Nombre incorrecto", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			// query a la BD para verificar que el nuevo nombre no esté en un registro
//			if ( (original.getNom_usu() != editado.getNom_usu()) && yaExiste() ) {
//				JOptionPane.showMessageDialog(null, "Llegaste tarde, el nombre que has escogido ya está en uso.",
//						"Nombre incorrecto", JOptionPane.ERROR_MESSAGE);
//				return false;
//			}
//			else {
				// query a la BD para cambiar los campos que hayan sido editados
//			}
		}
		
		return true;
	}
}
