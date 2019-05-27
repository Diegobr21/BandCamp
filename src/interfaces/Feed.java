package interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
import sistema.Credenciales;
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
	 * @throws Exception 
	 */
	Feed(Usuario sesion) throws Exception {
		cuenta = sesion;
		
		int id_iniciada= cuenta.getId();
	
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
		if(id_iniciada == Credenciales.ID_ADMIN) {			
			btnNotificaciones.setVisible(false);
		}

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
			try {
				agregarFichas(cuenta);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			PerfilPropio profileframe;
			try {
				profileframe = new PerfilPropio(cuenta);
				Point punto = Feed.this.getLocation();
				profileframe.setLocation(punto);
				profileframe.setVisible(true);
				Feed.this.dispose();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.contentEquals("abrirNots")) {
			scrNotificaciones.setVisible(true);
			
		}
	}
	
	/**
	 * Agrega las fichas de las cuentas que se filtraron al {@code JScrollPane} del muro.
	 * @param sesionIniciada {@code Usuario} de la sesi�n iniciada para filtrar las cuentas.
	 * @throws Exception 
	 */
	private void agregarFichas(Usuario sesionIniciada) throws Exception {
		Socket s = new Socket(Credenciales.SERVER_IP, Credenciales.SERVER_PORT);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		
		oos.writeInt(3);
		oos.writeObject(sesionIniciada);
		
		List<Usuario> cuentasFiltradas = (List<Usuario>)ois.readObject();
		if (cuentasFiltradas == null || cuentasFiltradas.size() == 0) {
			JLabel lblNoMatch = new JLabel("No hay cuentas que coincidan con lo que buscas.");
			lblNoMatch.setHorizontalAlignment(SwingConstants.CENTER);
			lblNoMatch.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNoMatch.setSize(624, 27);
			pnlFichas.add(lblNoMatch);
			
			return;
		}
		
		for (Usuario usuario : cuentasFiltradas) {
			System.out.println("a�adiendo");
			
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
							Feed feed;
							try {
								feed = new Feed(cuenta);
								feed.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
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
		
		oos.close();
		ois.close();
		s.close();
	}
	
	/**
	 * Agrega las notificaciones correspondientes.
	 * @param sesionIniciada {@code Usuario} destinatario de las notificaciones.
	 * @throws Exception 
	 */
	private void agregarNotificaciones(Usuario sesionIniciada) throws Exception {
		Socket s = new Socket(SERVER_IP,SERVER_PORT);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		
		oos.writeInt(6);
		oos.writeObject(sesionIniciada);
		
		List<Usuario> contactos = (List<Usuario>) ois.readObject();
		List<Usuario> uniones = (List<Usuario>) ois.readObject();
		
		List<Usuario> remitentes = new ArrayList<Usuario>();
		remitentes.addAll(contactos);
		remitentes.addAll(uniones);
		if (remitentes == null || remitentes.size() == 0) {
			JLabel lblNoNotifs = new JLabel("No tienes notificaciones.");
			lblNoNotifs.setHorizontalAlignment(SwingConstants.CENTER);
			lblNoNotifs.setFont(new Font("Tahoma", Font.BOLD, 12));
			pnlNotificaciones.add(lblNoNotifs);
			
			return;
		}
		
		loopList(uniones, NotifContacto.UNION);
		loopList(contactos, NotifContacto.CONTACTO);
		
		scrNotificaciones.repaint();
		scrNotificaciones.revalidate();
	}
	
	private void loopList(List<Usuario> lista, short tipo) throws Exception {
		for (Usuario remitente : lista) {
			System.out.println("notificaci�n");
			NotifContacto notifContacto = new NotifContacto(remitente, tipo);
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
							Feed feed;
							try {
								feed = new Feed(cuenta);
								feed.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							otroPerfil.closed = false;
							otroPerfil.cambios = false;
						}
					}
				}
			);
			pnlNotificaciones.add(notifContacto);
		}
	}
}
