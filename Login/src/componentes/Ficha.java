package componentes;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
/**
 * Plantilla de la ficha para las cuentas que pasen los filtros del muro de la sesión iniciada.
 */
public class Ficha extends JPanel {

	/**
	 * Create the panel.
	 */
	public Ficha(int tipo, String nombre, String genero, String instrumento, String facultad) {
		setBounds(100, 100, 624, 148);
		setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		setLayout(null);
		
		JLabel lblImagen = new JLabel();
		lblImagen.setIcon(new ImageIcon(Ficha.class.getResource("/img/userpic2.png")));
		lblImagen.setBounds(10, 11, 128, 128);
		add(lblImagen);
		
		JLabel lblNombre = new JLabel(nombre);
		lblNombre.setForeground(Color.RED);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNombre.setBounds(148, 11, 128, 25);
		add(lblNombre);
		
		JLabel lblFacultad = new JLabel(facultad);
		lblFacultad.setForeground(Color.DARK_GRAY);
		lblFacultad.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblFacultad.setBounds(148, 125, 34, 14);
		add(lblFacultad);
		
		JLabel lblGenero = new JLabel(genero);
		lblGenero.setForeground(Color.BLACK);
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGenero.setBounds(148, 69, 63, 14);
		add(lblGenero);
		
		String info;
		switch (tipo) {
		case 1:
			info = "Busco bandas que les haga falta: ";
			break;
		case 2:
			info = "Buscamos artistas que toquen: ";
			break;
		default:
			info = "";
			break;
		}
		
		JLabel lblInfo = new JLabel(info + instrumento);
		lblInfo.setForeground(new Color(0, 0, 0));
		lblInfo.setFont(new Font("Arial", Font.PLAIN, 22));
		lblInfo.setBounds(286, 28, 280, 87);
		add(lblInfo);
	}
}
