package interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
					Perfil frame = new Perfil();
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
	public Perfil() {
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
		
		JLabel lblNombreUsuariobanda = new JLabel("Nombre Usuario/Banda");
		lblNombreUsuariobanda.setFont(new Font("Verdana", Font.ITALIC, 16));
		lblNombreUsuariobanda.setBackground(Color.WHITE);
		lblNombreUsuariobanda.setForeground(Color.YELLOW);
		lblNombreUsuariobanda.setBounds(250, 26, 205, 21);
		contentPane.add(lblNombreUsuariobanda);
		
		JFormattedTextField Facultad_usu = new JFormattedTextField();
		Facultad_usu.setBounds(260, 58, 61, 21);
		contentPane.add(Facultad_usu);
		Facultad_usu.setActionCommand("Facultad");
		
		JFormattedTextField Ins_usu = new JFormattedTextField();
		Ins_usu.setBounds(359, 58, 78, 21);
		contentPane.add(Ins_usu);
		
		JButton btnVer = new JButton("Favoritos");
		btnVer.setFont(new Font("Verdana", Font.BOLD, 12));
		btnVer.setBackground(Color.WHITE);
		btnVer.setBounds(32, 321, 120, 35);
		contentPane.add(btnVer);
		btnVer.setActionCommand("Favoritos");
		btnVer.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent o) {
		//---botones
		String command= o.getActionCommand();
		if(command.contentEquals("Editar")){
			PerfilEdit edit = new PerfilEdit();
			edit.setVisible(true);
			Perfil.this.dispose();
			
			JOptionPane.showMessageDialog(null, "Tipo de Usuario: Artista");
		}
		else if(command.contentEquals("Regresar")) {
			Feed feedframe = new Feed();
			feedframe.setVisible(true);
			Perfil.this.dispose();
		}
		else if(command.contentEquals("Favoritos")) {
			JOptionPane.showMessageDialog(null, "Lista de favoritos: ");
		}
		
	}
}