import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JPasswordField;

public class UserLogin extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField correotxt;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin frame = new UserLogin();
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
	public UserLogin() {
		setTitle("Bandcamp - Inicar Sesi\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 414);
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBandcampUanl = new JLabel("BandCamp UANL");
		lblBandcampUanl.setForeground(Color.RED);
		lblBandcampUanl.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblBandcampUanl.setBounds(211, 21, 175, 33);
		contentPane.add(lblBandcampUanl);
		
		JLabel lblCorreoElectrnico = new JLabel("correo electr\u00F3nico: ");
		lblCorreoElectrnico.setForeground(Color.YELLOW);
		lblCorreoElectrnico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCorreoElectrnico.setBounds(20, 86, 119, 25);
		contentPane.add(lblCorreoElectrnico);
		
		JLabel lblContrasea = new JLabel("contrase\u00F1a:");
		lblContrasea.setForeground(Color.YELLOW);
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasea.setBounds(37, 130, 102, 14);
		contentPane.add(lblContrasea);
		
		correotxt = new JTextField();
		correotxt.setBounds(186, 90, 217, 20);
		contentPane.add(correotxt);
		correotxt.setColumns(10);
		correotxt.addActionListener(this);
		
		JButton Iniciarbtn = new JButton("Iniciar Sesi\u00F3n");
		Iniciarbtn.setFont(new Font("Verdana", Font.BOLD, 10));
		Iniciarbtn.setBounds(244, 201, 113, 33);
		contentPane.add(Iniciarbtn);
		Iniciarbtn.setActionCommand("Inicio");
		Iniciarbtn.addActionListener(this);
		
		JButton Registrarsebtn = new JButton("Registrarse");
		Registrarsebtn.setFont(new Font("Verdana", Font.BOLD, 10));
		Registrarsebtn.setBounds(244, 262, 113, 33);
		Registrarsebtn.setActionCommand("Registro");
		contentPane.add(Registrarsebtn);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(186, 129, 219, 20);
		contentPane.add(passwordField);
		Registrarsebtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		//---textfields
		String correo_usu = correotxt.getText();
		String pass_usu= Character.toString(passwordField.getEchoChar());	
		
		
		//---botones
		String command= a.getActionCommand();
		if(command.contentEquals("Inicio")) {
			Feed framefeed = new Feed();
			framefeed.setVisible(true);
			UserLogin.this.dispose();
			
			
			}else if(command.contentEquals("Registro")) {
			    Registro frameregistro = new Registro();
				frameregistro.setVisible(true);
				UserLogin.this.dispose();
				}
		
		
	}
}
