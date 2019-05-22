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
		setLocationRelativeTo(null);
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
				+ "¡Gracias por unirte a BandCamp! A continuación verás tu muro, que no es más que una ventana "
				+ "donde podrás conocer bandas y artistas acorde a tus gustos.<br><br>"
				+ "Cuando encuentras un usuario de tu agrado, haz clic en su ficha y selecciona el botón 'Contactar' para "
				+ "hacerle saber que te interesa asociarte con él.<br>"
				+ "Si el usuario acepta tu contacto, tendrán un periodo de 7 días para acordar reuniones y así poder hablar "
				+ "sobre una unión. Si deciden asociarse, pueden hacerlo saber al seleccionar el botón 'Unirse' en el perfil "
				+ "del usuario, quien tendrá 3 días para aceptar. Cuando acepten la unión, sabremos que "
				+ "hicimos posible una unión más en el mundo de la música.<br><br>"
				+ "Cuando hayas tenido éxito en una asociación, el resto de notificaciones que tengas serán rechazadas "
				+ "automáticamente y tu cuenta pasará a estar deshabilitada, para evitar que otros usuarios sigan "
				+ "tratando de contactarte. Si tu cuenta es de una banda y te hacen falta más miembros, puedes habilitar nuevamente "
				+ "tu cuenta iniciando sesión y editando tu perfil."
				+ "</center></html>");
		contentPane.add(lblAyuda);
	}
}