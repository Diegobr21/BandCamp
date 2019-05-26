package interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import componentes.Ficha;
import componentes.NotifContacto;
import sistema.Contacto;
import sistema.Muro;
import sistema.Union;
import sistema.Usuario;

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
		scrNotificaciones.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrNotificaciones.setVisible(false);
		scrNotificaciones.setBounds(127, 21, 200, 200);
		contentPane.add(scrNotificaciones);
		
		pnlNotificaciones = new JPanel();
		pnlNotificaciones.setLayout(new BoxLayout(pnlNotificaciones, BoxLayout.Y_AXIS));
		
		scrNotificaciones.setViewportView(pnlNotificaciones);
		scrNotificaciones.addMouseListener(
			new MouseAdapter() {
				@Override
				public void mouseExited(MouseEvent event) {
					scrNotificaciones.setVisible(false);
				}
			}
		);
		
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
		
	@Override
	public void actionPerformed(ActionEvent i) {
		String command = i.getActionCommand();
		
		if (command.contentEquals("Ayuda")){
			Ayuda ayuda = new Ayuda();
			ayuda.setLocationRelativeTo(null);
			ayuda.setVisible(true);
			
		} else if (command.contentEquals("PerfilUser")) {
			PerfilPropio profileframe = new PerfilPropio(cuenta);
			Point punto = Feed.this.getLocation();
			profileframe.setLocation(punto);
			profileframe.setVisible(true);
			Feed.this.dispose();
			
		} else if (command.contentEquals("abrirNots")) {
			scrNotificaciones.setVisible(true);
			
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
		
		for (Usuario usuario : cuentasFiltradas) {
			System.out.println("añadiendo");
			
			Ficha ficha = new Ficha(usuario);
			ficha.addMouseListener(
				new MouseAdapter() {
					Perfil otroPerfil = new Perfil(cuenta.getId(), usuario);
					
					@Override
					public void mouseClicked(MouseEvent event) {
						otroPerfil.setLocationRelativeTo(Feed.this);
						otroPerfil.setVisible(true);
					}
					
					@Override
					public void mouseEntered(MouseEvent event) {
						ficha.setBackground(Color.LIGHT_GRAY);
					}
					
					@Override
					public void mouseExited(MouseEvent event) {
						ficha.setBackground(Color.WHITE);
						if (otroPerfil.closed && otroPerfil.cambios) {
							System.out.println("\trefresh");
							if (otroPerfil.deshab) {
								cuenta.setDis_usu(false);
							}
							
							Feed.this.dispose();
							Feed feed = new Feed(cuenta);
							feed.setVisible(true);
							
							otroPerfil.closed = false;
							otroPerfil.cambios = false;
						}
					}
				}
			);
			pnlFichas.add(ficha);
		}
		scrFichas.repaint();
		scrFichas.revalidate();
	}
	
	/**
	 * Agrega las notificaciones correspondientes.
	 * @param sesionIniciada {@code Usuario} destinatario de las notificaciones.
	 */
	private void agregarNotificaciones(Usuario sesionIniciada) {
		List<Usuario> remitentes = new ArrayList<Usuario>();
		remitentes.addAll(Contacto.listarRemitentes(sesionIniciada.getId()));
		remitentes.addAll(Union.listarRemitentes(sesionIniciada.getId()));
		
		if (remitentes == null || remitentes.size() == 0) {
			JLabel lblNoNotifs = new JLabel("No tienes notificaciones.");
			lblNoNotifs.setHorizontalAlignment(SwingConstants.CENTER);
			lblNoNotifs.setFont(new Font("Tahoma", Font.BOLD, 12));
			pnlNotificaciones.add(lblNoNotifs);
			
			return;
		}
		
		for (Usuario remitente : remitentes) {
			System.out.println("notificación");
			NotifContacto notifContacto = new NotifContacto(remitente, NotifContacto.CONTACTO);
			notifContacto.addMouseListener(
				new MouseAdapter() {
					Perfil otroPerfil = new Perfil(cuenta.getId(), remitente);
					
					@Override
					public void mouseClicked(MouseEvent event) {
						otroPerfil.setLocationRelativeTo(Feed.this);
						otroPerfil.setVisible(true);
					}
					
					@Override
					public void mouseEntered(MouseEvent event) {
						notifContacto.setBackground(new Color(240, 240, 240));
					}
					
					@Override
					public void mouseExited(MouseEvent event) {
						notifContacto.setBackground(Color.WHITE);
						if (otroPerfil.closed && otroPerfil.cambios) {
							System.out.println("\trefresh");
							if (otroPerfil.deshab) {
								cuenta.setDis_usu(false);
							}
							
							Feed.this.dispose();
							Feed feed = new Feed(cuenta);
							feed.setVisible(true);
							
							otroPerfil.closed = false;
							otroPerfil.cambios = false;
						}
					}
				}
			);
			pnlNotificaciones.add(notifContacto);
		}
		scrNotificaciones.repaint();
		scrNotificaciones.revalidate();
	}
}
