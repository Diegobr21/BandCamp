package interfaces;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import sistema.CuentaFiltrada;
import sistema.Muro;
import sistema.Usuario;

@SuppressWarnings("serial")
public class Feed extends JFrame implements ActionListener {
	private JPanel pnlFichas;
	private JScrollPane scrollPane;
	
	private Usuario cuenta;
	
	/**
	 * Create the frame.
	 */
	public Feed(Usuario sesion) {
		cuenta = sesion;
	
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 419);
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
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 24, 624, 355);
		scrollPane.setVisible(true);
		contentPane.add(scrollPane);

		pnlFichas = new JPanel();
		pnlFichas.setLayout(new BoxLayout(pnlFichas, BoxLayout.Y_AXIS));
		pnlFichas.setVisible(true);
		
		scrollPane.setViewportView(pnlFichas);
		
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
			PerfilPropio profileframe = new PerfilPropio(cuenta);
			Point punto = Feed.this.getLocation();
			profileframe.setLocation(punto);
			profileframe.setVisible(true);
			Feed.this.dispose();
		}
	}
	
	/**
	 * Agrega las fichas de las cuentas que se filtraron al {@code JScrollPane} del muro.
	 * @param sesionIniciada {@code Usuario} de la sesión iniciada para filtrar las cuentas.
	 */
	private void agregarFichas(Usuario sesionIniciada) {
		List<CuentaFiltrada> cuentasFiltradas = new Muro().filtrarCuentas(sesionIniciada);
		if (cuentasFiltradas == null || cuentasFiltradas.size() == 0) {
			pnlFichas.add(new JLabel("No hay cuentas que coincidan."));
			return;
		}

		Point punto = new Point(Feed.this.getLocation());
		punto.x += 25;
		punto.y += 64;
		
		for (CuentaFiltrada cuentaFiltrada : cuentasFiltradas) {
			System.out.println("añadiendo");
			
			Ficha ficha = new Ficha(cuentaFiltrada);
			ficha.addMouseListener(
				new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						Perfil otroPerfil = new Perfil(cuentaFiltrada);
						otroPerfil.setLocation(punto);
						otroPerfil.setVisible(true);
					}
				}
			);
			pnlFichas.add(ficha);
			
			scrollPane.repaint();
			scrollPane.revalidate();
		}
	}
}
