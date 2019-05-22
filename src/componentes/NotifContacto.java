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
	protected Dimension dimension = new Dimension(200, 70);
	
	public NotifContacto(Usuario usuario) {
		setSize(dimension);
		setVisible(true);
		setLayout(null);
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setBackground(Color.WHITE);
		
		String notifText = "<html>"
			+ "<b><font color='red'>" + usuario.getNom_usu() + "</font></b>"
			+ " quiere contactarse contigo.";
		
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
