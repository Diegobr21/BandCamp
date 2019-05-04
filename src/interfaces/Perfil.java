package interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import sistema.Contacto;
import sistema.Usuario;

/**
 * Interfaz de los componentes visibles y no editables de un perfil ajeno al de la sesión iniciada y 
 * se construye a partir de una instancia de {@code Usuario}. <p>
 * La hereda {@code PerfilPropio}.
 * @see {@link PerfilPropio} <i>(subclase)</i>
 * @see {@link sistema.Usuario}
 */
@SuppressWarnings("serial")
public class Perfil extends JFrame implements WindowListener, ActionListener {
	
	protected JLabel lblFacultad, lblGenero, lblInstrumento, lblNombre, UserPic;
	protected JTextArea txtDescripcion;
	protected JPanel contentPane;
	protected JButton btnContactar;
	
	public boolean is_open = false;
	
	private Usuario scuenta;
	private int id_iniciada;
	/**
	 * Create the frame.
	 */
	public Perfil(int id_propia ,Usuario ssesion) {
		scuenta = ssesion;
		id_iniciada = id_propia;
		
		setTitle(scuenta.getNom_usu());
		setSize(630, 420);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(this);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UserPic = new JLabel("");
		UserPic.setForeground(Color.YELLOW);
		UserPic.setBackground(Color.YELLOW);
		UserPic.setIcon(new ImageIcon(Perfil.class.getResource("/userpic2.png")));
		UserPic.setBounds(20, 43, 128, 128);
		contentPane.add(UserPic);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setText(scuenta.getDes_usu());
		txtDescripcion.setBounds(34, 205, 549, 103);
		txtDescripcion.setEditable(false);
		contentPane.add(txtDescripcion);
		
		lblNombre = new JLabel(scuenta.getNom_usu());
		lblNombre.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNombre.setBackground(Color.WHITE);
		lblNombre.setForeground(Color.YELLOW);
		lblNombre.setBounds(158, 43, 128, 21);
		contentPane.add(lblNombre);
		
		lblFacultad = new JLabel(scuenta.getFac_usu());
		lblFacultad.setForeground(Color.LIGHT_GRAY);
		lblFacultad.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblFacultad.setBounds(158, 156, 62, 14);
		contentPane.add(lblFacultad);
		
		lblGenero = new JLabel(scuenta.getGen_usu());
		lblGenero.setForeground(Color.WHITE);
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGenero.setBounds(391, 112, 88, 21);
		contentPane.add(lblGenero);
		
		lblInstrumento = new JLabel(scuenta.getIns_usu());
		lblInstrumento.setForeground(Color.WHITE);
		lblInstrumento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInstrumento.setBounds(391, 144, 88, 27);
		contentPane.add(lblInstrumento);
		
		btnContactar = new JButton("Contactar");
		btnContactar.setForeground(Color.BLACK);
		btnContactar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnContactar.setBounds(243, 336, 134, 27);
		contentPane.add(btnContactar);
		
		if (Contacto.alreadyContacted(id_iniciada, scuenta.getId())) {
			btnContactar.setEnabled(false);
			
		} else {
			btnContactar.setActionCommand("contactar");
			btnContactar.addActionListener(this);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String actionCommand = event.getActionCommand();
		if (actionCommand.contentEquals("contactar")) {
			Contacto.crearNotificacion(id_iniciada, scuenta.getId());
			btnContactar.setEnabled(false);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		this.is_open = true;
	}
	@Override
	public void windowClosed(WindowEvent e) {
		this.is_open = false;
	}
	
	@Override public void windowClosing(WindowEvent e) {}
	@Override public void windowIconified(WindowEvent e) {}
	@Override public void windowDeiconified(WindowEvent e) {}
	@Override public void windowActivated(WindowEvent e) {}
	@Override public void windowDeactivated(WindowEvent e) {}
}