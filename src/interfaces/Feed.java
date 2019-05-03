package interfaces;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import componentes.Ficha;
import componentes.Notificacion;
import sistema.Muro;
import sistema.Usuario;
import java.awt.Font;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
class Feed extends JFrame implements ActionListener {
	private JPanel pnlFichas, pnlNotificaciones;
	private JScrollPane scrFichas, scrNotificaciones;
	private JButton btnNotificaciones;
	
	private Usuario cuenta;
	
	/**
	 * Create the frame.
	 */
	Feed(Usuario sesion) {
		cuenta = sesion;
	
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 650);
		setLocationRelativeTo(null);
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
		
		JButton btnAyuda = new JButton("Ayuda");
		menuBar.add(btnAyuda);
		btnAyuda.setActionCommand("Ayuda");
		btnAyuda.addActionListener(this);
		
		btnNotificaciones = new JButton("Notificaciones");
		btnNotificaciones.setActionCommand("abrirNots");
		btnNotificaciones.addActionListener(this);
		menuBar.add(btnNotificaciones);
		
		scrNotificaciones = new JScrollPane();
		scrNotificaciones.setVisible(false);
		scrNotificaciones.setBounds(127, 21, 200, 200);
		contentPane.add(scrNotificaciones);
		
		pnlNotificaciones = new JPanel();
		pnlNotificaciones.setLayout(new BoxLayout(pnlNotificaciones, BoxLayout.Y_AXIS));
		
		scrNotificaciones.setColumnHeaderView(pnlNotificaciones);
		
		scrFichas = new JScrollPane();
		scrFichas.setBounds(5, 52, 624, 558);
		scrFichas.setVisible(true);
		contentPane.add(scrFichas);

		pnlFichas = new JPanel();
		pnlFichas.setLayout(new BoxLayout(pnlFichas, BoxLayout.Y_AXIS));
		
		scrFichas.setViewportView(pnlFichas);
		
		JLabel lblContador = new JLabel("");
		lblContador.setHorizontalAlignment(SwingConstants.CENTER);
		lblContador.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContador.setBounds(5, 21, 619, 27);
		int count = Muro.countMatches();
		lblContador.setText("BandCamp ha hecho posible el ingreso de " + count + " artistas a nuevas bandas.");
		
		contentPane.add(lblContador);
		
		if (cuenta.isDis_usu()) {
			agregarFichas(cuenta);
			agregarNotificaciones(cuenta);
		} else {
			JLabel lblDeshabilitado = new JLabel("Habilita tu cuenta para ver a otros usuarios.");
			lblDeshabilitado.setHorizontalAlignment(SwingConstants.CENTER);
			lblDeshabilitado.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblDeshabilitado.setSize(624, 27);
			pnlFichas.add(lblDeshabilitado);
		}
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
		String command = i.getActionCommand();
		
		if (command.contentEquals("Ayuda")){
			JOptionPane.showMessageDialog(null, "Links de ayuda: \n www.help.mx \n www.oracle.com");
			
		} else if (command.contentEquals("PerfilUser")) {
			PerfilPropio profileframe = new PerfilPropio(cuenta);
			Point punto = Feed.this.getLocation();
			profileframe.setLocation(punto);
			profileframe.setVisible(true);
			Feed.this.dispose();
			
		} else if (command.contentEquals("abrirNots")) {
			scrNotificaciones.setVisible(true);
			btnNotificaciones.setActionCommand("cerrarNots");
			
		} else if (command.contentEquals("cerrarNots")) {
			scrNotificaciones.setVisible(false);
			btnNotificaciones.setActionCommand("abrirNots");
		}
	}
	
	/**
	 * Agrega las fichas de las cuentas que se filtraron al {@code JScrollPane} del muro.
	 * @param sesionIniciada {@code Usuario} de la sesión iniciada para filtrar las cuentas.
	 */
	private void agregarFichas(Usuario sesionIniciada) {
		List<Usuario> cuentasFiltradas = Muro.filtrarCuentas(sesionIniciada);
		if (cuentasFiltradas == null || cuentasFiltradas.size() == 0) {
			JLabel lblNoMatch = new JLabel("No hay cuentas que coincidan con lo que buscas.");
			lblNoMatch.setHorizontalAlignment(SwingConstants.CENTER);
			lblNoMatch.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNoMatch.setSize(624, 27);
			pnlFichas.add(lblNoMatch);
			
			return;
		}
		
		for (Usuario Usuario : cuentasFiltradas) {
			System.out.println("añadiendo");
			
			Ficha ficha = new Ficha(Usuario);
			pnlFichas.add(ficha);
			
			scrFichas.repaint();
			scrFichas.revalidate();
		}
	}
	
	/**
	 * Agrega las notificaciones correspondientes.
	 * @param sesionIniciada {@code Usuario} destinatario de las notificaciones.
	 */
	private void agregarNotificaciones(Usuario sesionIniciada) {
		List<Usuario> remitentes = Muro.listarRemitentes(sesionIniciada.getId());
		if (remitentes == null || remitentes.size() == 0) {
			JLabel lblNoNotifs = new JLabel("No tienes notificaciones.");
			lblNoNotifs.setHorizontalAlignment(SwingConstants.CENTER);
			lblNoNotifs.setFont(new Font("Tahoma", Font.BOLD, 12));
			pnlNotificaciones.add(lblNoNotifs);
			
			return;
		}
		
		for (Usuario remitente : remitentes) {
			System.out.println("notificación");
			
			Notificacion notificacion = new Notificacion(remitente);
			pnlNotificaciones.add(notificacion);
			
			scrNotificaciones.repaint();
			scrNotificaciones.revalidate();
		}
	}
}
