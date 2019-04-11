package interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sistema.EditarPerfil;
import sistema.Usuario;

@SuppressWarnings("serial")
public class Perfil extends JFrame implements ActionListener {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Perfil frame = new Perfil(null);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private ComboBoxes perfilCmbs;
	private JLabel lblFacultad, lblGenero, lblInstrumento, lblNombre;
	
	private JButton btnEditar, btnRegresar;
	private JTextArea txtDescripcion;
	private JTextField txtNombre;
	
	private Usuario cuenta;
	/**
	 * Create the frame.
	 */
	public Perfil(Usuario sesion) {
		// cuenta de ejemplo, cuando el argumento es null
		if (sesion == null) {
			sesion = new Usuario("correo", "pswd", 1, "HAVOK", "Rock", "Guitarra", "FAV");
		}
		cuenta = sesion;
		
		perfilCmbs = new ComboBoxes();
		
		setTitle("Perfil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 420);
		
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel UserPic = new JLabel("");
		UserPic.setForeground(Color.YELLOW);
		UserPic.setBackground(Color.YELLOW);
		UserPic.setIcon(new ImageIcon(Perfil.class.getResource("/img/userpic2.png")));
		UserPic.setBounds(20, 43, 128, 128);
		contentPane.add(UserPic);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setText("Descripción...\n");
		txtDescripcion.setBounds(34, 205, 549, 103);
		txtDescripcion.setEditable(false);
		contentPane.add(txtDescripcion);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnEditar.setBounds(479, 336, 104, 34);
		contentPane.add(btnEditar);
		btnEditar.addActionListener(this);
		btnEditar.setActionCommand("Editar");
		
		btnRegresar = new JButton();
		btnRegresar.setIcon(new ImageIcon(Perfil.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		btnRegresar.setForeground(Color.RED);
		btnRegresar.setBackground(Color.WHITE);
		btnRegresar.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		btnRegresar.setBounds(10, 11, 67, 21);
		contentPane.add(btnRegresar);
		btnRegresar.setActionCommand("Regresar");
		btnRegresar.addActionListener(this);
		
		lblNombre = new JLabel(sesion.getNom_usu());
		lblNombre.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNombre.setBackground(Color.WHITE);
		lblNombre.setForeground(new Color(255, 255, 0));
		lblNombre.setBounds(158, 43, 128, 21);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Verdana", Font.BOLD, 16));
		txtNombre.setForeground(new Color(150, 150, 0));
		txtNombre.setBounds(158, 43, 128, 21);
		txtNombre.setVisible(false);
		contentPane.add(txtNombre);
		
		lblFacultad = new JLabel(sesion.getFac_usu());
		lblFacultad.setForeground(Color.LIGHT_GRAY);
		lblFacultad.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblFacultad.setBounds(158, 156, 62, 14);
		contentPane.add(lblFacultad);
		
		contentPane.add(perfilCmbs.cmbFacultades);
		perfilCmbs.cmbFacultades.setLocation(158, 156);
		perfilCmbs.cmbFacultades.setVisible(false);
		
		lblGenero = new JLabel(sesion.getGen_usu());
		lblGenero.setForeground(Color.WHITE);
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGenero.setBounds(391, 112, 88, 21);
		contentPane.add(lblGenero);
		
		contentPane.add(perfilCmbs.cmbGeneros);
		perfilCmbs.cmbGeneros.setLocation(391, 112);
		perfilCmbs.cmbGeneros.setVisible(false);
		
		lblInstrumento = new JLabel(sesion.getIns_usu());
		lblInstrumento.setForeground(Color.WHITE);
		lblInstrumento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInstrumento.setBounds(391, 144, 88, 27);
		contentPane.add(lblInstrumento);
		
		contentPane.add(perfilCmbs.cmbInstrumentos);
		perfilCmbs.cmbInstrumentos.setLocation(391, 144);
		perfilCmbs.cmbInstrumentos.setVisible(false);
		
		JButton btnContacto = new JButton("Contacto");
		btnContacto.setFont(new Font("Verdana", Font.BOLD, 12));
		btnContacto.setBackground(Color.WHITE);
		btnContacto.setActionCommand("Contacto");
		btnContacto.setBounds(34, 336, 104, 34);
		contentPane.add(btnContacto);
		btnContacto.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent o) {
		String command = o.getActionCommand();
		
		if (command.contentEquals("Editar")){
			lblNombre.setVisible(false);
			txtNombre.setVisible(true);
			String nombre = cuenta.getNom_usu();
			txtNombre.setText(nombre);
			
			lblFacultad.setVisible(false);
			perfilCmbs.cmbFacultades.setVisible(true);
			String facultad = cuenta.getFac_usu();
			perfilCmbs.cmbFacultades.setSelectedItem(facultad);
			
			lblGenero.setVisible(false);
			perfilCmbs.cmbGeneros.setVisible(true);
			String genero = cuenta.getGen_usu();
			perfilCmbs.cmbGeneros.setSelectedItem(genero);
			
			lblInstrumento.setVisible(false);
			perfilCmbs.cmbInstrumentos.setVisible(true);
			String instrumento = cuenta.getIns_usu();
			perfilCmbs.cmbInstrumentos.setSelectedItem(instrumento);
			
			txtDescripcion.setEditable(true);
			
			btnEditar.setText("Guardar");
			btnEditar.setActionCommand("Guardar");
			btnRegresar.setEnabled(false);
			
//			PerfilEdit edit = new PerfilEdit(cuenta);
//			edit.setLocationRelativeTo(null);
//			edit.setVisible(true);
//			Perfil.this.dispose();
		}
		else if (command.contentEquals("Guardar")) {
			String nombre = txtNombre.getText();
			String facultad = perfilCmbs.cmbFacultades.getSelectedItem().toString();
			String genero = perfilCmbs.cmbGeneros.getSelectedItem().toString();
			String instrumento = perfilCmbs.cmbInstrumentos.getSelectedItem().toString();
			
			Usuario editado = new Usuario(cuenta.getCor_usu(), cuenta.getPas_usu(), cuenta.getTip_usu(), nombre,
					genero, instrumento, facultad);
			boolean cuentaEditada = new EditarPerfil().editarCuenta(cuenta, editado);
			if (cuentaEditada) {
				lblNombre.setVisible(true);
				lblNombre.setText(editado.getNom_usu());
				txtNombre.setVisible(false);
				
				lblFacultad.setVisible(true);
				lblFacultad.setText(editado.getFac_usu());
				perfilCmbs.cmbFacultades.setVisible(false);
				
				lblGenero.setVisible(true);
				lblGenero.setText(editado.getGen_usu());
				perfilCmbs.cmbGeneros.setVisible(false);
				
				lblInstrumento.setVisible(true);
				lblInstrumento.setText(editado.getIns_usu());
				perfilCmbs.cmbInstrumentos.setVisible(false);
				
				txtDescripcion.setEditable(false);
				
				btnEditar.setText("Editar");
				btnEditar.setActionCommand("Editar");
				btnRegresar.setEnabled(true);
				
				cuenta = editado;
			}
		}
		
		else if (command.contentEquals("Regresar")) {
			Feed feedframe = new Feed(cuenta);
			feedframe.setLocationRelativeTo(null);
			feedframe.setVisible(true);
			Perfil.this.dispose();
		}
	}
}