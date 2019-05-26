package interfaces;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

@SuppressWarnings("serial")
class Ayuda extends JDialog {

	/**
	 * Create the dialog.
	 */
	public Ayuda() {
		setTitle("Bienvenido");
		setSize(450, 400);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblAyuda = new JLabel();
		lblAyuda.setLocation(0, 0);
		lblAyuda.setSize(new Dimension(444, 371));
		lblAyuda.setHorizontalAlignment(SwingConstants.CENTER);
		lblAyuda.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAyuda.setText("<html><center>"
				+ "�Gracias por unirte a BandCamp! A continuaci�n ver�s tu muro, que no es m�s que una ventana "
				+ "donde podr�s conocer bandas y artistas acorde a tus gustos.<br><br>"
				+ "Cuando encuentras un usuario de tu agrado, haz clic en su ficha y selecciona el bot�n 'Contactar' para "
				+ "hacerle saber que te interesa asociarte con �l.<br>"
				+ "Si el usuario acepta tu contacto, tendr�n un periodo de 7 d�as para acordar reuniones y as� poder hablar "
				+ "sobre una uni�n. Si deciden asociarse, pueden hacerlo saber al seleccionar el bot�n 'Unirse' en el perfil "
				+ "del usuario, quien tendr� 3 d�as para aceptar. Cuando acepten la uni�n, sabremos que "
				+ "hicimos posible una uni�n m�s en el mundo de la m�sica.<br><br>"
				+ "Cuando hayas tenido �xito en una asociaci�n, el resto de notificaciones que tengas ser�n rechazadas "
				+ "autom�ticamente y tu cuenta pasar� a estar deshabilitada, para evitar que otros usuarios sigan "
				+ "tratando de contactarte. Si tu cuenta es de una banda y te hacen falta m�s miembros, puedes habilitar nuevamente "
				+ "tu cuenta iniciando sesi�n y editando tu perfil."
				+ "</center></html>");
		contentPane.add(lblAyuda);
	}
}