package componentes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import sistema.CuentaFiltrada;

@SuppressWarnings("serial")
/**
 * Plantilla de la ficha para las cuentas que pasen los filtros del muro de la sesión iniciada.
 */
public class Ficha extends JPanel {
	
	/**
	 * Create the panel.
	 */
	public Ficha(CuentaFiltrada cuentaFiltrada) {
		setSize(614, 148);
		setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		setLayout(null);
		setVisible(true);
		
		JLabel lblImagen = new JLabel();
		lblImagen.setIcon(new ImageIcon(Ficha.class.getResource("/img/userpic2.png")));
		lblImagen.setBounds(10, 11, 128, 128);
		add(lblImagen);
		
		JLabel lblNombre = new JLabel(cuentaFiltrada.getNombre());
		lblNombre.setForeground(Color.RED);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNombre.setBounds(148, 11, 173, 33);
		add(lblNombre);
		
		JLabel lblFacultad = new JLabel(cuentaFiltrada.getFacultad());
		lblFacultad.setForeground(Color.DARK_GRAY);
		lblFacultad.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblFacultad.setBounds(148, 119, 63, 20);
		add(lblFacultad);
		
		JLabel lblGenero = new JLabel(cuentaFiltrada.getGenero());
		lblGenero.setForeground(Color.BLACK);
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGenero.setBounds(148, 69, 149, 20);
		add(lblGenero);
		
		String info;
		switch (cuentaFiltrada.getTipo()) {
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
		
		JLabel lblInfo = new JLabel(info + cuentaFiltrada.getInstrumento());
		lblInfo.setForeground(new Color(0, 0, 0));
		lblInfo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblInfo.setBounds(331, 11, 269, 128);
		add(lblInfo);
	}
}
