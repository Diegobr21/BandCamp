package interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import sistema.Registrar;

@SuppressWarnings("serial")
class Registro extends JFrame implements ActionListener {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JTextField txtCorreo, txtNombre;
	private JPasswordField pswdPassword, pswdDuplicate;
	private JToggleButton tglbtnArtista, tglbtnBanda;
	
	private JLabel lblInstrumento;
	private String askInstrument = "¿Qué instrumento ";
	
	private ComboBoxes registroCmbs;
	
	/**
	 * Create the frame.
	 */	
	public Registro() {
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
		
		JLabel lblFacultad = new JLabel("\u00BFA qu\u00E9 facultad perteneces?");
		lblFacultad.setForeground(Color.YELLOW);
		lblFacultad.setFont(new Font("Arial", Font.PLAIN, 13));
		lblFacultad.setBounds(358, 56, 172, 14);
		contentPane.add(lblFacultad);
		
		contentPane.add(registroCmbs.cmbGeneros);
		registroCmbs.cmbGeneros.setLocation(358, 206);
		
		JLabel lblGneroMusical = new JLabel("\u00BFQu\u00E9 g\u00E9nero interpretas?");
		lblGneroMusical.setForeground(Color.YELLOW);
		lblGneroMusical.setFont(new Font("Arial", Font.PLAIN, 13));
		lblGneroMusical.setBounds(358, 181, 158, 14);
		contentPane.add(lblGneroMusical);
		
		contentPane.add(registroCmbs.cmbInstrumentos);
		registroCmbs.cmbInstrumentos.setLocation(358, 274);
		
		lblInstrumento = new JLabel(askInstrument + "tocas?");
		lblInstrumento.setForeground(Color.YELLOW);
		lblInstrumento.setFont(new Font("Arial", Font.PLAIN, 13));
		lblInstrumento.setBounds(358, 249, 191, 14);
		contentPane.add(lblInstrumento);
		
		JButton btnCrearCuenta = new JButton("Registrarse");
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
		
		txtNombre = new JTextField();
		txtNombre.setBounds(54, 137, 166, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
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
		tglbtnArtista.setSelected(true);
		tglbtnArtista.setEnabled(false);
		tglbtnArtista.setFont(new Font("Verdana", Font.BOLD, 11));
		tglbtnArtista.setBackground(Color.WHITE);
		tglbtnArtista.setBounds(473, 143, 89, 27);
		contentPane.add(tglbtnArtista);
		tglbtnArtista.setActionCommand("Artista");
		tglbtnArtista.addActionListener(this);
		
		pswdPassword = new JPasswordField();
		pswdPassword.setBounds(54, 206, 166, 20);
		contentPane.add(pswdPassword);
		
		pswdDuplicate = new JPasswordField();
		pswdDuplicate.setBounds(54, 274, 166, 20);
		contentPane.add(pswdDuplicate);
		
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
	
	private int tipo = 1;
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
		}
		else if (command.contentEquals("Banda")) {
			tipo = 2;
			tglbtnBanda.setEnabled(false);
			
			tglbtnArtista.setEnabled(true);
			tglbtnArtista.setSelected(false);
			
			lblInstrumento.setText(askInstrument + "buscas?");
		}
		else if (command.contentEquals("Crear")) {
			String[] passwords = new String[2];
			passwords[0] = String.valueOf(pswdPassword.getPassword());
			passwords[1] = String.valueOf(pswdDuplicate.getPassword());
			
			String correo = txtCorreo.getText();
			String nombre = txtNombre.getText();
			String genero = registroCmbs.cmbGeneros.getSelectedItem().toString();
			String instrumento = registroCmbs.cmbInstrumentos.getSelectedItem().toString();
			String facultad = registroCmbs.cmbFacultades.getSelectedItem().toString();
			
			boolean cuentaCreada = false;
			try {
				cuentaCreada = new Registrar().createAccount(1, correo, passwords, tipo, nombre, genero, instrumento, facultad);
				cuentaCreada = true;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (cuentaCreada) {
				// falta reemplazar el código de abajo por el frame de la segunda parte de registro 
				
				JOptionPane.showMessageDialog(null, "Se ha registrado correctamente.",
						"Cuenta creada", JOptionPane.INFORMATION_MESSAGE);
				
				UserLogin frameLogin = new UserLogin();
				frameLogin.setLocationRelativeTo(null);
				frameLogin.setVisible(true);
				Registro.this.dispose();
			}
			else {
				pswdPassword.setText("");
				pswdDuplicate.setText("");
			}
		}
		
		else if (command.contentEquals("Regresar")) {
			UserLogin frameLogin = new UserLogin();
			frameLogin.setLocationRelativeTo(null);
			frameLogin.setVisible(true);
			Registro.this.dispose();
		}
	}
}