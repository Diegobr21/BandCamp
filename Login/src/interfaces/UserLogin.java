package interfaces;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 414);
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBandcampUanl = new JLabel("BandCamp UANL");
		lblBandcampUanl.setForeground(Color.ORANGE);
		lblBandcampUanl.setFont(new Font("Tw Cen MT", Font.BOLD, 22));
		lblBandcampUanl.setBounds(204, 11, 175, 33);
		contentPane.add(lblBandcampUanl);
		
		JLabel lblCorreoElectrnico = new JLabel("Correo electr\u00F3nico");
		lblCorreoElectrnico.setForeground(Color.WHITE);
		lblCorreoElectrnico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCorreoElectrnico.setBounds(63, 88, 113, 20);
		contentPane.add(lblCorreoElectrnico);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasea.setBounds(101, 130, 75, 14);
		contentPane.add(lblContrasea);
		
		correotxt = new JTextField();
		correotxt.setBounds(186, 90, 217, 20);
		contentPane.add(correotxt);
		correotxt.setColumns(10);
		correotxt.addActionListener(this);
		
		JButton Iniciarbtn = new JButton("Iniciar sesi\u00F3n");
		Iniciarbtn.setFont(new Font("Verdana", Font.BOLD, 12));
		Iniciarbtn.setBounds(227, 216, 128, 25);
		contentPane.add(Iniciarbtn);
		Iniciarbtn.setActionCommand("Inicio");
		Iniciarbtn.addActionListener(this);
		
		JButton Registrarsebtn = new JButton("Registrarse");
		Registrarsebtn.setFont(new Font("Verdana", Font.BOLD, 12));
		Registrarsebtn.setBounds(227, 260, 128, 25);
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
		}
		else if(command.contentEquals("Registro")) {
		    Registro frameregistro = new Registro();
			frameregistro.setVisible(true);
			UserLogin.this.dispose();
		}
	}
}