package interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import componentes.ComboBoxes;
import sistema.Registrar;
import sistema.Usuario;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@SuppressWarnings("serial")
class Registro extends JFrame implements ActionListener, KeyListener {
	
	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 9000;

	private JTextField txtCorreo, txtNombre;
	private JPasswordField pswdPassword, pswdDuplicate;
	private JToggleButton tglbtnArtista, tglbtnBanda;
	private JButton btnCrearCuenta;
	
	private JLabel lblInstrumento;
	private String askInstrument = "¿Qué instrumento ";
	
	private ComboBoxes registroCmbs;
	
	private Usuario nuevaCuenta;
	/**
	 * Create the frame.
	 */	
	Registro(Usuario cuentaParam) {
		if (cuentaParam == null) {
			cuentaParam = new Usuario(0, "", "", 1, "", "Rock", "Guitarra", "FIME", "", true, "");
		}
		nuevaCuenta = cuentaParam;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 420);
		
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		registroCmbs = new ComboBoxes();
		
		contentPane.add(registroCmbs.cmbFacultades);
		registroCmbs.cmbFacultades.setLocation(358, 81);
		registroCmbs.cmbFacultades.setSelectedItem(nuevaCuenta.getFac_usu());
		
		JLabel lblFacultad = new JLabel("\u00BFA qu\u00E9 facultad perteneces?");
		lblFacultad.setForeground(Color.YELLOW);
		lblFacultad.setFont(new Font("Arial", Font.PLAIN, 13));
		lblFacultad.setBounds(358, 56, 172, 14);
		contentPane.add(lblFacultad);
		
		contentPane.add(registroCmbs.cmbGeneros);
		registroCmbs.cmbGeneros.setLocation(358, 206);
		registroCmbs.cmbGeneros.setSelectedItem(nuevaCuenta.getGen_usu());
		
		JLabel lblGneroMusical = new JLabel("\u00BFQu\u00E9 g\u00E9nero interpretas?");
		lblGneroMusical.setForeground(Color.YELLOW);
		lblGneroMusical.setFont(new Font("Arial", Font.PLAIN, 13));
		lblGneroMusical.setBounds(358, 181, 158, 14);
		contentPane.add(lblGneroMusical);
		
		contentPane.add(registroCmbs.cmbInstrumentos);
		registroCmbs.cmbInstrumentos.setLocation(358, 274);
		registroCmbs.cmbInstrumentos.setSelectedItem(nuevaCuenta.getIns_usu());
		
		lblInstrumento = new JLabel(askInstrument + "tocas?");
		lblInstrumento.setForeground(Color.YELLOW);
		lblInstrumento.setFont(new Font("Arial", Font.PLAIN, 13));
		lblInstrumento.setBounds(358, 249, 191, 14);
		contentPane.add(lblInstrumento);
		
		btnCrearCuenta = new JButton("Continuar");
		btnCrearCuenta.setForeground(Color.BLACK);
		btnCrearCuenta.setBackground(Color.WHITE);
		btnCrearCuenta.setFont(new Font("Verdana", Font.BOLD, 12));
		btnCrearCuenta.setBounds(211, 330, 141, 35);
		contentPane.add(btnCrearCuenta);
		btnCrearCuenta.setActionCommand("Crear");
		btnCrearCuenta.addActionListener(this);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(54, 81, 166, 20);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		txtCorreo.setText(nuevaCuenta.getCor_usu());
		txtCorreo.addKeyListener(this);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(54, 137, 166, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.setText(nuevaCuenta.getNom_usu());
		txtNombre.addKeyListener(this);
		
		JLabel lblCorreoElectrnico = new JLabel("Correo electr\u00F3nico");
		lblCorreoElectrnico.setForeground(Color.YELLOW);
		lblCorreoElectrnico.setBackground(Color.YELLOW);
		lblCorreoElectrnico.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCorreoElectrnico.setBounds(54, 56, 119, 14);
		contentPane.add(lblCorreoElectrnico);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario");
		lblNombreDeUsuario.setForeground(Color.YELLOW);
		lblNombreDeUsuario.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNombreDeUsuario.setBounds(54, 112, 119, 14);
		contentPane.add(lblNombreDeUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setForeground(Color.YELLOW);
		lblContrasea.setFont(new Font("Arial", Font.PLAIN, 13));
		lblContrasea.setBounds(54, 181, 119, 14);
		contentPane.add(lblContrasea);
		
		JLabel lblRepetirContrasea = new JLabel("Confirma contrase\u00F1a");
		lblRepetirContrasea.setForeground(Color.YELLOW);
		lblRepetirContrasea.setFont(new Font("Arial", Font.PLAIN, 13));
		lblRepetirContrasea.setBounds(54, 249, 119, 14);
		contentPane.add(lblRepetirContrasea);
		
		tglbtnBanda = new JToggleButton("Banda");
		tglbtnBanda.setFont(new Font("Verdana", Font.BOLD, 11));
		tglbtnBanda.setBackground(Color.WHITE);
		tglbtnBanda.setForeground(Color.BLACK);
		tglbtnBanda.setBounds(323, 143, 89, 27);
		contentPane.add(tglbtnBanda);
		tglbtnBanda.setActionCommand("Banda");
		tglbtnBanda.addActionListener(this);
		
		tglbtnArtista = new JToggleButton("Artista");
		tglbtnArtista.setFont(new Font("Verdana", Font.BOLD, 11));
		tglbtnArtista.setBackground(Color.WHITE);
		tglbtnArtista.setForeground(Color.BLACK);
		tglbtnArtista.setBounds(473, 143, 89, 27);
		contentPane.add(tglbtnArtista);
		tglbtnArtista.setActionCommand("Artista");
		tglbtnArtista.addActionListener(this);
		
		switch (nuevaCuenta.getTip_usu()) {
			case 1:
				tglbtnArtista.doClick();
				break;
			case 2:
				tglbtnBanda.doClick();
				break;
		}
		
		pswdPassword = new JPasswordField();
		pswdPassword.setBounds(54, 206, 166, 20);
		contentPane.add(pswdPassword);
		pswdPassword.addKeyListener(this);
		
		pswdDuplicate = new JPasswordField();
		pswdDuplicate.setBounds(54, 274, 166, 20);
		contentPane.add(pswdDuplicate);
		pswdDuplicate.addKeyListener(this);
		
		JButton btnRegresar = new JButton("");
		btnRegresar.setIcon(new ImageIcon(Registro.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		btnRegresar.setForeground(Color.RED);
		btnRegresar.setBackground(Color.LIGHT_GRAY);
		btnRegresar.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		btnRegresar.setBounds(10, 11, 67, 27);
		contentPane.add(btnRegresar);
		btnRegresar.setActionCommand("Regresar");
		btnRegresar.addActionListener(this);
		
		JLabel lblSelecciona = new JLabel("Eres...");
		lblSelecciona.setForeground(Color.YELLOW);
		lblSelecciona.setFont(new Font("Arial", Font.PLAIN, 13));
		lblSelecciona.setBounds(418, 123, 47, 14);
		contentPane.add(lblSelecciona);
	}
	
	private int tipo;
	
	private void continuarRegistro() throws Exception {
		Socket s = new Socket(SERVER_IP,SERVER_PORT);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		
		oos.writeInt(2);
		
		String[] passwords = new String[2];
		passwords[0] = String.valueOf(pswdPassword.getPassword());
		passwords[1] = String.valueOf(pswdDuplicate.getPassword());
		
		oos.writeObject(txtCorreo.getText());
		oos.writeInt(tipo);
		oos.writeObject(txtNombre.getText());
		oos.writeObject(registroCmbs.cmbGeneros.getSelectedItem().toString());
		oos.writeObject(registroCmbs.cmbInstrumentos.getSelectedItem().toString());
		oos.writeObject(registroCmbs.cmbFacultades.getSelectedItem().toString());
		
		nuevaCuenta = (Usuario)ois.readObject();
		
		boolean cuentaValida = Registrar.checkAccount(nuevaCuenta, passwords);
		
		if (cuentaValida) {
			
			oos.writeObject(passwords[0]);
			
			Registro2 registro2 = new Registro2(nuevaCuenta);
			Point punto = this.getLocation();
			registro2.setLocation(punto);
			registro2.setVisible(true);
			Registro.this.dispose();
		} else {
			pswdPassword.setText("");
			pswdDuplicate.setText("");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//---Botones---
		String command = e.getActionCommand();
		if (command.contentEquals("Artista")){
			tipo = 1;
			tglbtnArtista.setEnabled(false);
			
			tglbtnBanda.setEnabled(true);
			tglbtnBanda.setSelected(false);
			
			lblInstrumento.setText(askInstrument + "tocas?");
		} else if (command.contentEquals("Banda")) {
			tipo = 2;
			tglbtnBanda.setEnabled(false);
			
			tglbtnArtista.setEnabled(true);
			tglbtnArtista.setSelected(false);
			
			lblInstrumento.setText(askInstrument + "buscas?");
		} else if (command.contentEquals("Crear")) {
			try {
				continuarRegistro();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else if (command.contentEquals("Regresar")) {
			UserLogin frameLogin = new UserLogin();
			Point punto = this.getLocation();
			frameLogin.setLocation(punto);
			frameLogin.setVisible(true);
			Registro.this.dispose();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			try {
				continuarRegistro();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	@Override public void keyTyped(KeyEvent e) {}
	@Override public void keyReleased(KeyEvent e) {}
}