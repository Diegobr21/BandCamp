package interfaces;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import sistema.CuentaFiltrada;

/**
 * Interfaz de los componentes visibles y no editables de un perfil ajeno al de la sesión iniciada y 
 * se construye a partir de una instancia de {@code CuentaFiltrada}. <p>
 * La hereda {@code PerfilPropio}.
 * @see {@link PerfilPropio} <i>(subclase)</i>
 * @see {@link sistema.CuentaFiltrada}
 */
@SuppressWarnings("serial")
public class Perfil extends JFrame {
	protected JLabel lblFacultad, lblGenero, lblInstrumento, lblNombre, UserPic;

	protected JTextArea txtDescripcion;
	
	protected JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	Perfil(CuentaFiltrada cuenta) {
		setTitle(cuenta.getNombre());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(630, 420);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UserPic = new JLabel("");
		UserPic.setForeground(Color.YELLOW);
		UserPic.setBackground(Color.YELLOW);
		UserPic.setIcon(new ImageIcon(Perfil.class.getResource("/img/userpic2.png")));
		UserPic.setBounds(20, 43, 128, 128);
		contentPane.add(UserPic);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setText(cuenta.getDescripcion());
		txtDescripcion.setBounds(34, 205, 549, 103);
		txtDescripcion.setEditable(false);
		contentPane.add(txtDescripcion);
		
		lblNombre = new JLabel(cuenta.getNombre());
		lblNombre.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNombre.setBackground(Color.WHITE);
		lblNombre.setForeground(new Color(255, 255, 0));
		lblNombre.setBounds(158, 43, 128, 21);
		contentPane.add(lblNombre);
		
		lblFacultad = new JLabel(cuenta.getFacultad());
		lblFacultad.setForeground(Color.LIGHT_GRAY);
		lblFacultad.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblFacultad.setBounds(158, 156, 62, 14);
		contentPane.add(lblFacultad);
		
		lblGenero = new JLabel(cuenta.getGenero());
		lblGenero.setForeground(Color.WHITE);
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGenero.setBounds(391, 112, 88, 21);
		contentPane.add(lblGenero);
		
		lblInstrumento = new JLabel(cuenta.getInstrumento());
		lblInstrumento.setForeground(Color.WHITE);
		lblInstrumento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInstrumento.setBounds(391, 144, 88, 27);
		contentPane.add(lblInstrumento);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Perfil.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		btnNewButton.setBounds(527, 11, 56, 47);
		contentPane.add(btnNewButton);
	}
}