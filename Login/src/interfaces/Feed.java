package interfaces;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JCheckBoxMenuItem;

public class Feed extends JFrame implements ActionListener {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Feed frame = new Feed();
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
	public Feed() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 24, 624, 355);
		contentPane.add(scrollPane);
		
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
		String command= i.getActionCommand();
		if(command.contentEquals("Ayuda")){
			JOptionPane.showMessageDialog(null, "Links de ayuda: \n www.help.mx \n www.oracle.com");
			
		}else if (command.contentEquals("PerfilUser")) {
			Perfil profileframe = new Perfil();
			profileframe.setVisible(true);
			Feed.this.dispose();
		}else if (command.contentEquals("Cerrar")) {
			UserLogin frameLogin = new UserLogin();
			frameLogin.setVisible(true);
			Feed.this.dispose();
		}
	}
}
