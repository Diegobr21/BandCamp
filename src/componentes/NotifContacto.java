package componentes;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import sistema.Usuario;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class NotifContacto extends JPanel {
	
	public static final short CONTACTO = 1;
	public static final short UNION = 2;
	
	private Dimension dimension = new Dimension(200, 70);
	
	public NotifContacto(Usuario usuario, short tipo) {
		setSize(dimension);
		setVisible(true);
		setLayout(null);
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setBackground(Color.WHITE);
		
		String color = "", texto = "";
		if (tipo == CONTACTO) {
			color = "red";
			texto = "quiere contactarse contigo";
		} else if (tipo == UNION) {
			color = "green";
			texto = "solicita una unión";
		}
		
		String notifText = String.format("<html>"
			+ "<b><font color='%s'>%s</font></b> %s.", color, usuario.getNom_usu(), texto);
		
		JLabel lblNombre = new JLabel(notifText);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(10, 11, 180, 48);
		add(lblNombre);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return dimension;
	}
	
	@Override
	public Dimension getMaximumSize() {
		return dimension;
	}
	
	@Override
	public Dimension getMinimumSize() {
		return dimension;
	}
}
