package componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import interfaces.Perfil;
import sistema.Usuario;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Notificacion extends JPanel {
	protected Dimension dimension = new Dimension(200, 70);
	
	public Notificacion(int id, Usuario usuario) {
		setSize(dimension);
		setVisible(true);
		setLayout(null);
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setBackground(Color.WHITE);
		addMouseListener(
			new MouseAdapter() {
				Perfil otroPerfil = new Perfil(id, usuario);
				
				@Override
				public void mouseClicked(MouseEvent event) {
					if (otroPerfil.is_open) {
						otroPerfil.setLocationRelativeTo(null);
						otroPerfil.toFront();
					} else {
						otroPerfil.setLocationRelativeTo(null);
						otroPerfil.setVisible(true);
					}
				}
				
				@Override
				public void mouseEntered(MouseEvent event) {
					setBackground(new Color(240, 240, 240));
				}
				
				@Override
				public void mouseExited(MouseEvent event) {
					setBackground(Color.WHITE);
				}
			}
		);
		
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
