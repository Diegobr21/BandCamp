package interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
public class PerfilEdit extends JFrame implements ActionListener{
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerfilEdit frame = new PerfilEdit();
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
	public PerfilEdit() {
		setTitle("Editar Perfil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 406);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel UserPic = new JLabel("");
		UserPic.setForeground(Color.YELLOW);
		UserPic.setBackground(Color.YELLOW);
		contentPane.setLayout(null);
		UserPic.setIcon(new ImageIcon(PerfilEdit.class.getResource("/img/userpic2.png")));
		UserPic.setBounds(479, 11, 128, 128);
		contentPane.add(UserPic);
		
		TextArea Descrip_txt = new TextArea();
		Descrip_txt.setText("Descripcion...");
		Descrip_txt.setBounds(49, 151, 512, 122);
		contentPane.add(Descrip_txt);
		
		JButton btnRegresar = new JButton("");
		btnRegresar.setIcon(new ImageIcon(PerfilEdit.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		btnRegresar.setForeground(Color.RED);
		btnRegresar.setBackground(Color.WHITE);
		btnRegresar.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		btnRegresar.setBounds(10, 11, 61, 29);
		contentPane.add(btnRegresar);
		btnRegresar.setActionCommand("Regresar");
		btnRegresar.addActionListener(this);
		
		JLabel lblNombreUsuariobanda = new JLabel("Nombre Usuario/Banda");
		lblNombreUsuariobanda.setFont(new Font("Verdana", Font.ITALIC, 16));
		lblNombreUsuariobanda.setBackground(Color.WHITE);
		lblNombreUsuariobanda.setForeground(Color.YELLOW);
		lblNombreUsuariobanda.setBounds(280, 11, 189, 21);
		contentPane.add(lblNombreUsuariobanda);
		
		JFormattedTextField Facultad_usu = new JFormattedTextField();
		Facultad_usu.setBounds(307, 43, 52, 21);
		contentPane.add(Facultad_usu);
		Facultad_usu.setActionCommand("Facultad");
		
		JFormattedTextField Ins_usu = new JFormattedTextField();
		Ins_usu.setBounds(387, 43, 74, 21);
		contentPane.add(Ins_usu);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnGuardar.setBackground(Color.WHITE);
		btnGuardar.setBounds(438, 316, 95, 25);
		contentPane.add(btnGuardar);
		btnGuardar.setActionCommand("Guardar");
		btnGuardar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent u) {
		//---botones
		String command= u.getActionCommand();
		if(command.contentEquals("Guardar")){
			JOptionPane.showMessageDialog(null, "Cambios Guardados");
		
			
			JOptionPane.showMessageDialog(null, "Tipo de Usuario: Artista");
		}
		else if(command.contentEquals("Regresar")) {
			Perfil perfil = new Perfil();
			perfil.setVisible(true);
			PerfilEdit.this.dispose();
		}
	}
}