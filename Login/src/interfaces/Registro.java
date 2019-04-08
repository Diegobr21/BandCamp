package interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class Registro extends JFrame implements ActionListener {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private JPanel contentPane;
	private JTextField txtCorreo, txtNombre;
	private JPasswordField pswdPassword, pswdDuplicate;
	private JToggleButton tglbtnArtista, tglbtnBanda;
	
	private JLabel lblInstrumento;
	private String askInstrument = "¿Qué instrumento ";
	
	private JComboBox<String> comboBoxFacultades;
	private JComboBox<String> comboBoxGeneros;
	private JComboBox<String> comboBoxInstrumentos;
	
	private int tipo = 1;
	
	public Registro() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 420);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBoxFacultades = new JComboBox<String>();
		comboBoxFacultades.setModel(new DefaultComboBoxModel<String>(
				new String[] {"FIME", "FCQ", "FACPYA", "FCFM", "FARQ", "FACDYC", "FIC", "FAV", "FOD"}));
		comboBoxFacultades.setBounds(358, 81, 158, 20);
		contentPane.add(comboBoxFacultades);
					//Event handler comboBoxes--
		comboBoxFacultades.setEditable(false);
		comboBoxFacultades.addActionListener(this);
		//---End
		
		JLabel lblFacultad = new JLabel("\u00BFA qu\u00E9 facultad perteneces?");
		lblFacultad.setForeground(Color.YELLOW);
		lblFacultad.setFont(new Font("Arial", Font.PLAIN, 13));
		lblFacultad.setBounds(358, 56, 172, 14);
		contentPane.add(lblFacultad);
		
		
		comboBoxGeneros = new JComboBox<String>();
		comboBoxGeneros.setModel(new DefaultComboBoxModel<String>(
				new String[] {"Rock", "Jazz", "Reggaeton", "Rap", "Metal", "Indie", "K-Pop", "Pop", "Cl\u00E1sica"}));
		comboBoxGeneros.setBounds(358, 206, 158, 20);
		contentPane.add(comboBoxGeneros);
		//Event handler comboBoxes--
				comboBoxGeneros.setEditable(false);
				comboBoxGeneros.addActionListener(new ActionListener() {
					private boolean found = false;
					@Override
					public void actionPerformed(ActionEvent e) {
						String s = (String) comboBoxGeneros.getSelectedItem();
						for (int i=0; i<comboBoxGeneros.getItemCount(); i++) {
							if (comboBoxGeneros.getItemAt(i).toString().equals(s)) {
								found= true;
								break;
							}
						}
						if (!found) {
							JOptionPane.showMessageDialog(null, "Agregado: "+s);
							comboBoxGeneros.addItem(s);
						}
						found = false;
					}
				});
				//---End
		
		JLabel lblGneroMusical = new JLabel("\u00BFQu\u00E9 g\u00E9nero interpretas?");
		lblGneroMusical.setForeground(Color.YELLOW);
		lblGneroMusical.setFont(new Font("Arial", Font.PLAIN, 13));
		lblGneroMusical.setBounds(358, 181, 158, 14);
		contentPane.add(lblGneroMusical);
		
		comboBoxInstrumentos = new JComboBox<String>();
		comboBoxInstrumentos.setModel(new DefaultComboBoxModel<String>(
				new String[] {"Guitarra", "Bater\u00EDa", "Piano", "Xilófono", "Flauta",
						"Viol\u00EDn", "Trompeta", "Oboe", "Ocarina", "Bajo"}));
		comboBoxInstrumentos.setBounds(358, 274, 158, 20);
		contentPane.add(comboBoxInstrumentos);
				//Event handler comboBoxes--
		comboBoxInstrumentos.setEditable(false);
		comboBoxInstrumentos.addActionListener(new ActionListener() {
			private boolean found = false;
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String) comboBoxInstrumentos.getSelectedItem();
				for (int i=0; i<comboBoxInstrumentos.getItemCount(); i++) {
					if (comboBoxInstrumentos.getItemAt(i).toString().equals(s)) {
						found= true;
						break;
					}
				}
				if (!found) {
					JOptionPane.showMessageDialog(null, "Agregado: "+s);
					comboBoxInstrumentos.addItem(s);
				}
				found = false;
			}
		});
		
		lblInstrumento = new JLabel(askInstrument + "tocas?");
		lblInstrumento.setForeground(Color.YELLOW);
		lblInstrumento.setFont(new Font("Arial", Font.PLAIN, 13));
		lblInstrumento.setBounds(358, 249, 191, 14);
		contentPane.add(lblInstrumento);
		
	//---End
		
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
		txtCorreo.addActionListener(this);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(54, 137, 166, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.addActionListener(this);
		
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
		pswdPassword.addActionListener(this);
		
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
		pswdDuplicate.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Las comboboxes tienen actionlistener indiv
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
			String genero = comboBoxGeneros.getSelectedItem().toString().toLowerCase();
			String instrumento = comboBoxInstrumentos.getSelectedItem().toString().toLowerCase();
			String facultad = comboBoxFacultades.getSelectedItem().toString();
			
			boolean success = new Registrar().createAccount(correo, passwords, tipo, nombre, genero, instrumento, facultad);
			if (success) {
				JOptionPane.showMessageDialog(null, "Se ha registrado correctamente.",
						"Cuenta creada", JOptionPane.INFORMATION_MESSAGE);
				UserLogin frameLogin = new UserLogin();
				frameLogin.setVisible(true);
				Registro.this.dispose();	
			}
		}
		else if (command.contentEquals("Regresar")) {
			UserLogin frameLogin = new UserLogin();
			frameLogin.setVisible(true);
			Registro.this.dispose();
		}
	}
}