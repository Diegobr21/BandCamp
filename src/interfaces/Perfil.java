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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import sistema.Contacto;
import sistema.Union;
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
	protected JTextArea txtDescripcion, txtContacto;
	protected JPanel contentPane;
	protected JButton btnContactar, btnAceptar, btnRechazar;
	
	public boolean closed = false;
	public boolean cambios = false;
	public boolean deshab = false;
	
	private Usuario scuenta;
	private int id_iniciada;
	/**
	 * Create the frame.
	 */
	public Perfil(int id_propia, Usuario sesion) {
		scuenta = sesion;
		id_iniciada = id_propia;
		
		setTitle(scuenta.getNom_usu());
		setSize(630, 520);
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
		UserPic.setBounds(20, 11, 128, 128);
		contentPane.add(UserPic);
		
		JScrollPane scrDescripcion = new JScrollPane();
		scrDescripcion.setBounds(20, 150, 564, 128);
		scrDescripcion.setVisible(true);
		contentPane.add(scrDescripcion);
		
		txtDescripcion = new JTextArea();
//		txtDescripcion.setFont(null);
		txtDescripcion.setText(scuenta.getDes_usu());
		txtDescripcion.setEditable(false);
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		scrDescripcion.setViewportView(txtDescripcion);
		
		JScrollPane scrContacto = new JScrollPane();
		scrContacto.setVisible(true);
		scrContacto.setBounds(20, 300, 564, 128);
		contentPane.add(scrContacto);
		
		txtContacto = new JTextArea();
		txtContacto.setWrapStyleWord(true);
		txtContacto.setLineWrap(true);
		txtContacto.setForeground(Color.DARK_GRAY);
		txtContacto.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 11));
		txtContacto.setText("Esta información es privada.");
		txtContacto.setEditable(false);
		scrContacto.setViewportView(txtContacto);
		
		lblNombre = new JLabel(scuenta.getNom_usu());
		lblNombre.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNombre.setBackground(Color.WHITE);
		lblNombre.setForeground(Color.YELLOW);
		lblNombre.setBounds(158, 11, 128, 21);
		contentPane.add(lblNombre);
		
		lblFacultad = new JLabel(scuenta.getFac_usu());
		lblFacultad.setForeground(Color.LIGHT_GRAY);
		lblFacultad.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblFacultad.setBounds(158, 119, 62, 14);
		contentPane.add(lblFacultad);
		
		lblGenero = new JLabel(scuenta.getGen_usu());
		lblGenero.setForeground(Color.WHITE);
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGenero.setBounds(391, 80, 88, 21);
		contentPane.add(lblGenero);
		
		lblInstrumento = new JLabel(scuenta.getIns_usu());
		lblInstrumento.setForeground(Color.WHITE);
		lblInstrumento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInstrumento.setBounds(391, 112, 88, 27);
		contentPane.add(lblInstrumento);
		
		btnContactar = new JButton("Contactar");
		btnContactar.setForeground(Color.BLACK);
		btnContactar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnContactar.setBounds(244, 455, 110, 25);
		btnContactar.setActionCommand("contactar");
		btnContactar.addActionListener(this);
		contentPane.add(btnContactar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setVisible(false);
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAceptar.setBounds(134, 455, 110, 25);
		btnAceptar.setForeground(new Color(0, 100, 0));
		btnAceptar.addActionListener(this);
		contentPane.add(btnAceptar);
		
		btnRechazar = new JButton("Rechazar");
		btnRechazar.setVisible(false);
		btnRechazar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRechazar.setBounds(352, 455, 110, 25);
		btnRechazar.addActionListener(this);
		contentPane.add(btnRechazar);
		
		if (id_iniciada != scuenta.getId()) {
			if (Contacto.alreadyContacted(scuenta.getId(), id_iniciada, Contacto.PENDING)) {
				// si la cuenta vista le envió una notificación a la sesión iniciada
				btnContactar.setVisible(false);
				btnAceptar.setVisible(true);
				btnAceptar.setActionCommand("aceptar_not");
				btnRechazar.setVisible(true);
				btnRechazar.setActionCommand("rechazar_not");
				
			} else if (Contacto.alreadyContacted(id_iniciada, scuenta.getId(), Contacto.PENDING)) {
				// si la sesión iniciada le envió una notificación a la cuenta vista
				btnContactar.setEnabled(false);
				
			} else if (Contacto.alreadyContacted(id_iniciada, scuenta.getId(), Contacto.ACCEPT)
					|| Contacto.alreadyContacted(scuenta.getId(), id_iniciada, Contacto.ACCEPT)) {
				// si ambos aceptaron el contacto
				txtContacto.setFont(null);
				txtContacto.setText(scuenta.getCon_usu());
				
				btnContactar.setText("Unirse");
				btnContactar.setActionCommand("unirse");
				btnContactar.setVisible(true);
				btnContactar.setEnabled(true);
				
				if (Union.alreadyContacted(scuenta.getId(), id_iniciada, Contacto.PENDING)) {
					// si la cuenta vista le envió solicitud de unión a la sesión iniciada
					btnContactar.setVisible(false);
					btnAceptar.setVisible(true);
					btnAceptar.setActionCommand("aceptar_uni");
					btnRechazar.setVisible(true);
					btnRechazar.setActionCommand("rechazar_uni");
					
				} else if (Union.alreadyContacted(id_iniciada, scuenta.getId(), Contacto.PENDING)) {
					// si la sesión iniciada le envió solicitud de unión a la cuenta vista
					btnContactar.setEnabled(false);	
				}
			}
			
			else {
				// si no hay ningún contacto
				btnContactar.setVisible(true);
				btnContactar.setEnabled(true);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String actionCommand = event.getActionCommand();
		
		if (actionCommand.contentEquals("contactar")) {
			Contacto.crearNotificacion("Notificaciones", id_iniciada, scuenta.getId());
			btnContactar.setEnabled(false);
			
		} else if (actionCommand.contentEquals("aceptar_not")) {
			int dialog = JOptionPane.showConfirmDialog(this,
					"<html><center>"
					+ "Al aceptar a este usuario, ambos tendrán un periodo de 7 días para contactarse <br>"
					+ "y posteriormente estar de acuerdo sobre la asociación.<br>"
					+ "¿Continuar?</center></html>", 
					"Aceptar contacto", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (dialog == JOptionPane.YES_OPTION) {
				Contacto.replyContact(scuenta.getId(), id_iniciada, Contacto.ACCEPT);
				this.dispose();
			}
		} else if (actionCommand.contentEquals("rechazar_not")) {
			this.cambios = true;
			Contacto.replyContact(scuenta.getId(), id_iniciada, Contacto.REJECT);
			btnAceptar.setVisible(false);
			btnRechazar.setVisible(false);
			btnContactar.setVisible(true);
			btnContactar.setEnabled(true);
		}
		
		else if (actionCommand.contentEquals("unirse")) {
			Contacto.crearNotificacion("Uniones", id_iniciada, scuenta.getId());
			btnContactar.setEnabled(false);
			
		} else if (actionCommand.contentEquals("aceptar_uni")) {
			int dialog = JOptionPane.showConfirmDialog(this,
					"<html><center>"
					+ "Al aceptar a este usuario confirmas que lograron asociarse, por lo tanto,<br>"
					+ "el resto de notificaciones que tengas pendientes pasarán a ser rechazadas.<br>"
					+ "¿Unirse?</center></html>", 
					"Aceptar unión", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (dialog == JOptionPane.YES_OPTION) {
				this.deshab = true;
				this.cambios = true;
				Union.replyContact(scuenta.getId(), id_iniciada, Contacto.ACCEPT);
				Union.deshabilitarCuentas(id_iniciada, scuenta.getId());
				this.dispose();
			}
		} else if (actionCommand.contentEquals("rechazar_uni")) {
			this.cambios = true;
			Union.replyContact(scuenta.getId(), id_iniciada, Contacto.REJECT);
			btnAceptar.setVisible(false);
			btnRechazar.setVisible(false);
			btnContactar.setVisible(true);
			btnContactar.setEnabled(false);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		this.closed = true;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
	}
	
	@Override public void windowIconified(WindowEvent e) {}
	@Override public void windowDeiconified(WindowEvent e) {}
	@Override public void windowActivated(WindowEvent e) {}
	@Override public void windowDeactivated(WindowEvent e) {}
}