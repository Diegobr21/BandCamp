import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.JPasswordField;
import javax.swing.JSlider;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.List;

public class Registro extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtcorreo;
	private JTextField txtnom_usu;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldrep;

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
	public Registro() {
		setTitle("Bandcamp - Registro");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 420);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBoxfac = new JComboBox();
		comboBoxfac.setModel(new DefaultComboBoxModel(new String[] {"FIME", "FCQ", "FACPYA", "FCFM", "FARQ", "FACDYC"}));
		comboBoxfac.setBounds(358, 66, 158, 20);
		contentPane.add(comboBoxfac);
					//Event handler comboBoxes--
		comboBoxfac.setEditable(true);
		comboBoxfac.addActionListener(new ActionListener() {
			private boolean found = false;
			@Override
			public void actionPerformed(ActionEvent e) {
				
								
				String s = (String) comboBoxfac.getSelectedItem();
				for (int i=0; i<comboBoxfac.getItemCount(); i++) {
					if (comboBoxfac.getItemAt(i).toString().equals(s)) {
						found= true;
						//chequeo de funcionamiento
						System.out.print(comboBoxfac.getItemAt(i));
						
						//todo bien
						break;
					}
				}
				if (!found) {
					JOptionPane.showMessageDialog(null, "Agregado: "+s);
					comboBoxfac.addItem(s);
				}
				found = false;
			}
		});
		//---End
		
		JLabel lblFacultad = new JLabel("Facultad");
		lblFacultad.setForeground(Color.YELLOW);
		lblFacultad.setFont(new Font("Arial", Font.PLAIN, 13));
		lblFacultad.setBounds(398, 41, 89, 14);
		contentPane.add(lblFacultad);
		
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Rock", "Jazz", "Reggaeton", "Rap", "Metal", "Indie", "K-Pop", "Pop", "Cl\u00E1sica"}));
		comboBox_1.setBounds(358, 206, 158, 20);
		contentPane.add(comboBox_1);
		//Event handler comboBoxes--
				comboBox_1.setEditable(true);
				comboBox_1.addActionListener(new ActionListener() {
					private boolean found = false;
					@Override
					public void actionPerformed(ActionEvent e) {
						
										
						String s = (String) comboBox_1.getSelectedItem();
						for (int i=0; i<comboBox_1.getItemCount(); i++) {
							if (comboBox_1.getItemAt(i).toString().equals(s)) {
								found= true;
								//chequeo de funcionamiento
								System.out.print(comboBox_1.getItemAt(i));
								
								//todo bien
								break;
							}
						}
						if (!found) {
							JOptionPane.showMessageDialog(null, "Agregado: "+s);
							comboBox_1.addItem(s);
						}
						found = false;
					}
				});
				//---End
		
		JLabel lblGneroMusical = new JLabel("G\u00E9nero Musical");
		lblGneroMusical.setForeground(Color.YELLOW);
		lblGneroMusical.setFont(new Font("Arial", Font.PLAIN, 13));
		lblGneroMusical.setBounds(358, 181, 127, 14);
		contentPane.add(lblGneroMusical);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Guitarra", "Bater\u00EDa", "Piano", "Xilofono", "Flauta", "Viol\u00EDn", "Trompeta", "Oboe", "Ocarina"}));
		comboBox_2.setBounds(358, 274, 158, 20);
		contentPane.add(comboBox_2);
				//Event handler comboBoxes--
		comboBox_2.setEditable(true);
		comboBox_2.addActionListener(new ActionListener() {
			private boolean found = false;
			@Override
			public void actionPerformed(ActionEvent e) {
				
								
				String s = (String) comboBox_2.getSelectedItem();
				for (int i=0; i<comboBox_2.getItemCount(); i++) {
					if (comboBox_2.getItemAt(i).toString().equals(s)) {
						found= true;
						//chequeo de funcionamiento
						System.out.print(comboBox_2.getItemAt(i));
						
						//todo bien
						break;
					}
				}
				if (!found) {
					JOptionPane.showMessageDialog(null, "Agregado: "+s);
					comboBox_2.addItem(s);
				}
				found = false;
			}
		});
		
	//---End
		
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setForeground(Color.BLACK);
		btnSiguiente.setBackground(Color.WHITE);
		btnSiguiente.setFont(new Font("Verdana", Font.BOLD, 12));
		btnSiguiente.setBounds(211, 330, 141, 35);
		contentPane.add(btnSiguiente);
		btnSiguiente.addActionListener(this);
		btnSiguiente.setActionCommand("Siguiente");
		
		txtcorreo = new JTextField();
		txtcorreo.setBounds(54, 81, 166, 20);
		contentPane.add(txtcorreo);
		txtcorreo.setColumns(10);
		txtcorreo.addActionListener(this);
		
		txtnom_usu = new JTextField();
		txtnom_usu.setBounds(54, 147, 166, 20);
		contentPane.add(txtnom_usu);
		txtnom_usu.setColumns(10);
		txtnom_usu.addActionListener(this);
		
		JLabel lblInstrumentosAUsar = new JLabel("Instrumento a buscar / que usa");
		lblInstrumentosAUsar.setForeground(Color.YELLOW);
		lblInstrumentosAUsar.setFont(new Font("Arial", Font.PLAIN, 13));
		lblInstrumentosAUsar.setBounds(358, 249, 191, 14);
		contentPane.add(lblInstrumentosAUsar);
		
		JLabel lblCorreoElectrnico = new JLabel("Correo Electr\u00F3nico");
		lblCorreoElectrnico.setForeground(Color.YELLOW);
		lblCorreoElectrnico.setBackground(Color.YELLOW);
		lblCorreoElectrnico.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCorreoElectrnico.setBounds(64, 56, 133, 14);
		contentPane.add(lblCorreoElectrnico);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setForeground(Color.YELLOW);
		lblNombreDeUsuario.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNombreDeUsuario.setBounds(64, 112, 133, 14);
		contentPane.add(lblNombreDeUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setForeground(Color.YELLOW);
		lblContrasea.setFont(new Font("Arial", Font.PLAIN, 13));
		lblContrasea.setBounds(64, 181, 89, 14);
		contentPane.add(lblContrasea);
		
		JLabel lblRepetirContrasea = new JLabel("Repetir Contrase\u00F1a");
		lblRepetirContrasea.setForeground(Color.YELLOW);
		lblRepetirContrasea.setFont(new Font("Arial", Font.PLAIN, 13));
		lblRepetirContrasea.setBounds(64, 249, 133, 14);
		contentPane.add(lblRepetirContrasea);
		
		JToggleButton tglbtnBanda = new JToggleButton("Banda");
		tglbtnBanda.setFont(new Font("Verdana", Font.BOLD, 11));
		tglbtnBanda.setBackground(Color.WHITE);
		tglbtnBanda.setForeground(Color.BLACK);
		tglbtnBanda.setBounds(323, 143, 89, 27);
		contentPane.add(tglbtnBanda);
		tglbtnBanda.setActionCommand("Banda");
		tglbtnBanda.addActionListener(this);
		
		JToggleButton tglbtnArtista = new JToggleButton("Artista");
		tglbtnArtista.setFont(new Font("Verdana", Font.BOLD, 11));
		tglbtnArtista.setBackground(Color.WHITE);
		tglbtnArtista.setBounds(473, 143, 89, 27);
		contentPane.add(tglbtnArtista);
		tglbtnArtista.setActionCommand("Artista");
		tglbtnArtista.addActionListener(this);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(54, 206, 166, 20);
		contentPane.add(passwordField);
		passwordField.addActionListener(this);
		
		passwordFieldrep = new JPasswordField();
		passwordFieldrep.setBounds(54, 274, 166, 20);
		contentPane.add(passwordFieldrep);
		
		JButton btnRegresar = new JButton("<----");
		btnRegresar.setForeground(Color.RED);
		btnRegresar.setBackground(Color.LIGHT_GRAY);
		btnRegresar.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		btnRegresar.setBounds(10, 11, 67, 27);
		contentPane.add(btnRegresar);
		btnRegresar.setActionCommand("Regresar");
		btnRegresar.addActionListener(this);
		
		JLabel lblSelecciona = new JLabel("Selecciona...");
		lblSelecciona.setForeground(Color.YELLOW);
		lblSelecciona.setFont(new Font("Arial", Font.PLAIN, 13));
		lblSelecciona.setBounds(398, 112, 89, 14);
		contentPane.add(lblSelecciona);
		passwordFieldrep.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
						//Las comboboxes tienen actionlistener indiv
		
		
						//---Botones---
		String command= e.getActionCommand();
		if(command.contentEquals("Artista")){
			int tipo_usu = 1;
			JOptionPane.showMessageDialog(null, "Tipo de Usuario: Artista");
		}else if (command.contentEquals("Banda")) {
			int tipo_usu = 2;
			JOptionPane.showMessageDialog(null, "Tipo de Usuario: Banda");
		}else if (command.contentEquals("Siguiente")) {
			//objeto cuenta, tipo Usuario
			Registro2 sig = new Registro2();
			sig.setVisible(true);
			Registro.this.dispose();
			
		}else if (command.contentEquals("Regresar")) {
			UserLogin frameLogin = new UserLogin();
			frameLogin.setVisible(true);
			Registro.this.dispose();
		}
						//--Textfields---
		String nom_usu = txtnom_usu.getText();
		String correo_usu = txtcorreo.getText();
		//Validacion con API? Abajo solo es ejemplo de como manejar el texto
		txtcorreo.setText(correo_usu.toUpperCase());
		//Obtener el texto de un pssdtextfield, getEcho te da char por default por eso el casteo
		String pssd = Character.toString( passwordField.getEchoChar());
		String pssdrep= Character.toString( passwordFieldrep.getEchoChar());
		
	}
}
