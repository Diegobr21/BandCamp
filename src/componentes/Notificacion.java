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
	protected Dimension dimension = new Dimension(200, 100);
	
	public Notificacion(Usuario usuario) {
		setSize(dimension);
		setVisible(true);
		setLayout(null);
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		addMouseListener(
				new MouseAdapter() {
					Perfil otroPerfil = new Perfil(usuario);
					
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
						setBackground(Color.LIGHT_GRAY);
					}
					
					@Override
					public void mouseExited(MouseEvent event) {
						setBackground(Color.WHITE);
					}
				}
			);
		
		JLabel lblNombre = new JLabel();
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombre.setBounds(10, 11, 180, 78);
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
