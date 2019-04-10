package interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sistema.Usuario;

@SuppressWarnings("serial")
public class Perfil extends JFrame implements ActionListener {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Perfil frame = new Perfil(null);
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Usuario cuenta;
	/**
	 * Create the frame.
	 */
	public Perfil(Usuario sesion) {
		// cuenta de ejemplo, cuando el argumento es null
		if (sesion == null) {
			sesion = new Usuario("correo", "pswd", 1, "HAVOK", "Metal", "Guitarra", "FIME");
		}
		cuenta = sesion;
		
		setTitle("Perfil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 420);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel UserPic = new JLabel("");
		UserPic.setForeground(Color.YELLOW);
		UserPic.setBackground(Color.YELLOW);
		UserPic.setIcon(new ImageIcon(Perfil.class.getResource("/img/userpic2.png")));
		UserPic.setBounds(465, 11, 128, 128);
		contentPane.add(UserPic);
		
		TextArea Descrip_txt = new TextArea();
		Descrip_txt.setText("Descripcion...");
		Descrip_txt.setBounds(32, 170, 549, 103);
		contentPane.add(Descrip_txt);
		
		JButton Editarbtn = new JButton("Editar Perfil");
		Editarbtn.setBackground(Color.WHITE);
		Editarbtn.setFont(new Font("Verdana", Font.BOLD, 12));
		Editarbtn.setBounds(445, 321, 148, 35);
		contentPane.add(Editarbtn);
		Editarbtn.addActionListener(this);
		Editarbtn.setActionCommand("Editar");
		
		JButton btnRegresar = new JButton("");
		btnRegresar.setIcon(new ImageIcon(Perfil.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		btnRegresar.setForeground(Color.RED);
		btnRegresar.setBackground(Color.WHITE);
		btnRegresar.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		btnRegresar.setBounds(10, 11, 67, 27);
		contentPane.add(btnRegresar);
		btnRegresar.setActionCommand("Regresar");
		btnRegresar.addActionListener(this);
		
		JLabel lblNombre = new JLabel(sesion.getNom_usu());
		lblNombre.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNombre.setBackground(Color.WHITE);
		lblNombre.setForeground(new Color(255, 255, 0));
		lblNombre.setBounds(327, 11, 128, 21);
		contentPane.add(lblNombre);
		
		JButton btnVer = new JButton("Favoritos");
		btnVer.setFont(new Font("Verdana", Font.BOLD, 12));
		btnVer.setBackground(Color.WHITE);
		btnVer.setBounds(32, 321, 120, 35);
		contentPane.add(btnVer);
		btnVer.setActionCommand("Favoritos");
		
		JLabel lblFacultad = new JLabel(sesion.getFac_usu());
		lblFacultad.setForeground(Color.LIGHT_GRAY);
		lblFacultad.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblFacultad.setBounds(409, 125, 46, 14);
		contentPane.add(lblFacultad);
		
		JLabel lblGenero = new JLabel(sesion.getGen_usu());
		lblGenero.setForeground(Color.WHITE);
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGenero.setBounds(367, 44, 88, 21);
		contentPane.add(lblGenero);
		
		JLabel lblInstrumento = new JLabel(sesion.getIns_usu());
		lblInstrumento.setForeground(Color.WHITE);
		lblInstrumento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInstrumento.setBounds(367, 76, 88, 27);
		contentPane.add(lblInstrumento);
		btnVer.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent o) {
		//---botones
		String command = o.getActionCommand();
		if(command.contentEquals("Editar")){
			PerfilEdit edit = new PerfilEdit(cuenta);
			edit.setVisible(true);
			Perfil.this.dispose();
		}
		else if(command.contentEquals("Regresar")) {
			Feed feedframe = new Feed(cuenta);
			feedframe.setVisible(true);
			Perfil.this.dispose();
		}
		else if(command.contentEquals("Favoritos")) {
			JOptionPane.showMessageDialog(this, "Lista de favoritos: ");
		}
	}
}