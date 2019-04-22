package interfaces;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import componentes.Ficha;
import sistema.CuentaFiltrada;
import sistema.Muro;
import sistema.Usuario;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Feed extends JFrame implements ActionListener {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Feed frame = new Feed(null);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Usuario cuenta;
	private JScrollPane scrollPane;
	/**
	 * Create the frame.
	 */
	public Feed(Usuario sesion) {
		cuenta = sesion;
	
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 419);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(5, -1, 619, 21);
		contentPane.add(menuBar);
		
		JButton btnPerfil = new JButton("Perfil");
		menuBar.add(btnPerfil);
		btnPerfil.setActionCommand("PerfilUser");
		btnPerfil.addActionListener(this);
		
		JButton btnCerrarSesin = new JButton("Cerrar Sesi\u00F3n");
		menuBar.add(btnCerrarSesin);
		btnCerrarSesin.setActionCommand("Cerrar");
		btnCerrarSesin.addActionListener(this);
		
		JButton btnAyuda = new JButton("Ayuda");
		menuBar.add(btnAyuda);
		btnAyuda.setActionCommand("Ayuda");
		btnAyuda.addActionListener(this);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 24, 624, 355);
		scrollPane.setLayout(null);
		contentPane.add(scrollPane);
		
		//scrollPane.add(new Ficha(new CuentaFiltrada(2, "l", "l", "l", "l", "l")));
		
		agregarFichas(cuenta);
	}	
		
		/*	Ayuda ayuda = new Ayuda();
		ayuda.getContentPane();
		ayuda.pack();
		Feed muro = new Feed();
		
		btnAyuda.addActionListener(new ActionListener() { 	
			public void actionPerformed(ActionEvent e) { 	
				muro.setVisible(false); 
				ayuda.setVisible(true);	}
			}); 
	
		// Hacer que al cerrarse la secundaria con la x de arriba a la  derecha, se muestre la primaria
		ayuda.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) { 
				muro.setVisible(true); 	
				ayuda.setVisible(false);
				} 
			public void windowClosed(WindowEvent e) { 
				muro.setVisible(true); 
				ayuda.setVisible(false); 
				}
			}); 	
		// Mostrar la ventana principal
		ayuda.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
		muro.setVisible(true); 	*/
		
	@Override
	public void actionPerformed(ActionEvent i) {
				//--botones
		String command = i.getActionCommand();
		if(command.contentEquals("Ayuda")){
			JOptionPane.showMessageDialog(null, "Links de ayuda: \n www.help.mx \n www.oracle.com");
		}
		else if (command.contentEquals("PerfilUser")) {
			Perfil profileframe = new Perfil(cuenta);
			Point punto = this.getLocation();
			profileframe.setLocation(punto);
			profileframe.setVisible(true);
			Feed.this.dispose();
		}
		else if (command.contentEquals("Cerrar")) {
			UserLogin frameLogin = new UserLogin();
			Point punto = this.getLocation();
			frameLogin.setLocation(punto);
			frameLogin.setVisible(true);
			Feed.this.dispose();
		}
	}
	
	/**
	 * Agrega las fichas de las cuentas que se filtraron al {@code JScrollPane} del muro.
	 * @param sesionIniciada {@code Usuario} de la sesi�n iniciada para filtrar las cuentas.
	 */
	private void agregarFichas(Usuario sesionIniciada) {
		List<CuentaFiltrada> cuentasFiltradas = new Muro().filtrarCuentas(sesionIniciada);
		if (cuentasFiltradas.size() == 0) {
			scrollPane.add(new JLabel("No hay cuentas que coincidan."));
			return;
		}
		
		for (CuentaFiltrada cuentaFiltrada : cuentasFiltradas) {
			scrollPane.add(new Ficha(cuentaFiltrada));
		}
	}
}
