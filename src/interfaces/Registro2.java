package interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 9000;
	
	private JPanel contentPane;
	private JTextField txtcodigo;
	
	private JTextArea txtDescripcion, txtContacto;
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
		txtcodigo.setBounds(234, 286, 113, 34);
		contentPane.add(txtcodigo);
		txtcodigo.setColumns(4);
		txtcodigo.addActionListener(this);
		
		JButton btnCrearCuenta = new JButton("Crear cuenta");
		btnCrearCuenta.setForeground(Color.BLACK);
		btnCrearCuenta.setBackground(Color.WHITE);
		btnCrearCuenta.setFont(new Font("Verdana", Font.BOLD, 12));
		btnCrearCuenta.setBounds(222, 345, 141, 35);
		contentPane.add(btnCrearCuenta);
		btnCrearCuenta.setActionCommand("Crear");
		
		JLabel lblcodigo = new JLabel("Ingrese el c\u00F3digo de verificaci\u00F3n de su correo");
		lblcodigo.setFont(new Font("Verdana", Font.BOLD, 11));
		lblcodigo.setForeground(Color.YELLOW);
		lblcodigo.setBounds(133, 251, 341, 24);
		contentPane.add(lblcodigo);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n (informaci\u00F3n p\u00FAblica)");
		lblDescripcion.setFont(new Font("Verdana", Font.BOLD, 11));
		lblDescripcion.setForeground(Color.YELLOW);
		lblDescripcion.setBounds(190, 9, 233, 28);
		contentPane.add(lblDescripcion);
		
		JScrollPane scrDescripcion = new JScrollPane();
		scrDescripcion.setBounds(58, 49, 526, 68);
		scrDescripcion.setVisible(true);
		contentPane.add(scrDescripcion);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setWrapStyleWord(true);
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setText("ejemplo: Rockero desde peque\u00F1o, he estado en 4 bandas y participado en varios conciertos menores.");
		scrDescripcion.setViewportView(txtDescripcion);
		
		JLabel lblContacto = new JLabel("Contacto (informaci\u00F3n privada)");
		lblContacto.setForeground(Color.YELLOW);
		lblContacto.setFont(new Font("Verdana", Font.BOLD, 11));
		lblContacto.setBounds(190, 127, 210, 28);
		contentPane.add(lblContacto);
		
		JScrollPane scrContacto = new JScrollPane();
		scrContacto.setBounds(58, 166, 526, 74);
		scrContacto.setVisible(true);
		contentPane.add(scrContacto);
		
		txtContacto = new JTextArea();
		txtContacto.setLineWrap(true);
		txtContacto.setWrapStyleWord(true);
		txtContacto.setText("ejemplo: WhatsApp: 8125456789");
		scrContacto.setViewportView(txtContacto);
		
		btnCrearCuenta.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command= e.getActionCommand();
		try {
		Socket s = new Socket(SERVER_IP,SERVER_PORT);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		
		if (command.contentEquals("Crear")){
			nuevaCuenta.setDes_usu(txtDescripcion.getText());
			nuevaCuenta.setCon_usu(txtContacto.getText());
			String codigo = txtcodigo.getText();


			oos.writeInt(2);
			oos.writeObject(nuevaCuenta);
			oos.writeObject(codigo);
			boolean cuentaCreada = ois.readBoolean();
			
			if (cuentaCreada) {
				Point punto = this.getLocation();
				
				Usuario sesionIniciada = (Usuario)ois.readObject();
				
				Feed framefeed = new Feed(sesionIniciada);
				framefeed.setLocation(punto);
				framefeed.setVisible(true);
				Registro2.this.dispose();
				Ayuda ayuda = new Ayuda();
				ayuda.setLocationRelativeTo(null);
				ayuda.setVisible(true);
				}
			
		} else if(command.contentEquals("Regresar")){
				Registro reg = new Registro(nuevaCuenta);
				Point punto = this.getLocation();
				reg.setLocation(punto);
				reg.setVisible(true);
				Registro2.this.dispose();
			}
		oos.close();
		ois.close();
		s.close();
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
