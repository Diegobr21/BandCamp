import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Registro2 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtcodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro2 frame = new Registro2();
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
	public Registro2() {
		setTitle("Bandcamp - Registro");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 420);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		JButton btnRegresar = new JButton("<----");
		btnRegresar.setForeground(Color.RED);
		btnRegresar.setBackground(Color.LIGHT_GRAY);
		btnRegresar.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		btnRegresar.setBounds(10, 11, 67, 27);
		contentPane.add(btnRegresar);
		btnRegresar.setActionCommand("Regresar");
		btnRegresar.addActionListener(this);
		
		txtcodigo = new JTextField();
		txtcodigo.setBounds(232, 266, 113, 34);
		contentPane.add(txtcodigo);
		txtcodigo.setColumns(4);
		
		
		JButton btnCrearCuenta = new JButton("Crear Cuenta");
		btnCrearCuenta.setForeground(Color.BLACK);
		btnCrearCuenta.setBackground(Color.WHITE);
		btnCrearCuenta.setFont(new Font("Verdana", Font.BOLD, 12));
		btnCrearCuenta.setBounds(219, 331, 141, 35);
		contentPane.add(btnCrearCuenta);
		btnCrearCuenta.setActionCommand("Crear");
		
		JLabel lblcodigo = new JLabel("Ingrese el c\u00F3digo de verificaci\u00F3n de su correo");
		lblcodigo.setFont(new Font("Verdana", Font.BOLD, 11));
		lblcodigo.setForeground(Color.YELLOW);
		lblcodigo.setBounds(130, 231, 341, 24);
		contentPane.add(lblcodigo);
		
		JTextArea txtdescripcion = new JTextArea();
		txtdescripcion.setText("Ej: Originario de Zacatecas, experiencia en BandaEjemplo por 2 a\u00F1os.\r\n\r\nFacebook: JuanPerezGzz\r\nWhatsapp: 8181170378");
		txtdescripcion.setBounds(52, 108, 505, 102);
		contentPane.add(txtdescripcion);
		
		JLabel lblDescripcinYContacto = new JLabel("Descripci\u00F3n y contacto");
		lblDescripcinYContacto.setFont(new Font("Verdana", Font.BOLD, 11));
		lblDescripcinYContacto.setForeground(Color.YELLOW);
		lblDescripcinYContacto.setBounds(52, 70, 206, 27);
		contentPane.add(lblDescripcinYContacto);
		btnCrearCuenta.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command= e.getActionCommand();
		if(command.contentEquals("Crear"))
		{
		JOptionPane.showMessageDialog(null, "Cuenta Creada");
		UserLogin frameLogin = new UserLogin();
		frameLogin.setVisible(true);
		Registro2.this.dispose();
	}else if(command.contentEquals("Regresar")){
		JOptionPane.showMessageDialog(null, "Cuenta Creada");
		UserLogin frameLogin = new UserLogin();
		frameLogin.setVisible(true);
		Registro2.this.dispose();
	}
		}
}
