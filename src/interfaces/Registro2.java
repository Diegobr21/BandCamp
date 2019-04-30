package interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sistema.Login;
import sistema.Registrar;
import sistema.Usuario;

@SuppressWarnings("serial")
/**
 * Solicita llenar la descripción de la cuenta e ingresar un código para verificar el correo electrónico ingresado.
 */
class Registro2 extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTextField txtcodigo;
	
	private JTextArea txtdescripcion;
	private Usuario nuevaCuenta;
	/**
	 * Create the frame.
	 */
	Registro2(Usuario cuentaParam) {
		nuevaCuenta = cuentaParam;
		
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
		txtcodigo.addActionListener(this);
		
		JButton btnCrearCuenta = new JButton("Crear cuenta");
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
		
		txtdescripcion = new JTextArea();
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
		
		if (command.contentEquals("Crear")){
			nuevaCuenta.setDes_usu(txtdescripcion.getText());
			String codigo = txtcodigo.getText();
			
			boolean cuentaCreada = new Registrar().createAccount(nuevaCuenta, codigo);
			if (cuentaCreada) {
				Point punto = this.getLocation();
				
				Usuario sesionIniciada = Login.ingresar(nuevaCuenta.getCor_usu(), nuevaCuenta.getPas_usu());
				
				Feed framefeed = new Feed(sesionIniciada);
				framefeed.setLocation(punto);
				framefeed.setVisible(true);
				Registro2.this.dispose();
			}
			
		} else if(command.contentEquals("Regresar")){
			Registro reg = new Registro(nuevaCuenta);
			Point punto = this.getLocation();
			reg.setLocation(punto);
			reg.setVisible(true);
			Registro2.this.dispose();
		}
	}
}
