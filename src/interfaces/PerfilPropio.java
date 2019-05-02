package interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import componentes.ComboBoxes;
import sistema.EditarPerfil;
import sistema.Usuario;

/**
 * Interfaz que muestra los componentes del perfil de la sesión iniciada y los eventos para editarlos.
 * Se construye a partir de una instancia de {@code Usuario}, generada desde el inicio de sesión. <p>
 * Hereda de {@code Perfil}.
 * @see {@link Perfil} <i>(superclase)</i>
 * @see {@link sistema.Usuario}
 */
@SuppressWarnings("serial")
class PerfilPropio extends Perfil implements ActionListener{
	private ComboBoxes perfilCmbs;
	private JButton btnEditar, btnRegresar, btnCerrarSesion;
	private JTextField txtNombre;
	
	private Usuario cuenta;
	
	PerfilPropio(Usuario sesion) {
		super(sesion);
		setTitle("Mi perfil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cuenta = sesion;
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Verdana", Font.BOLD, 16));
		txtNombre.setForeground(new Color(150, 150, 0));
		txtNombre.setBounds(158, 43, 128, 21);
		txtNombre.setVisible(false);
		contentPane.add(txtNombre);
		
		perfilCmbs = new ComboBoxes();
		
		perfilCmbs.cmbFacultades.setLocation(158, 156);
		perfilCmbs.cmbFacultades.setVisible(false);
		contentPane.add(perfilCmbs.cmbFacultades);
		
		perfilCmbs.cmbGeneros.setLocation(391, 112);
		perfilCmbs.cmbGeneros.setVisible(false);
		contentPane.add(perfilCmbs.cmbGeneros);

		perfilCmbs.cmbInstrumentos.setLocation(391, 144);
		perfilCmbs.cmbInstrumentos.setVisible(false);
		contentPane.add(perfilCmbs.cmbInstrumentos);
		
		btnRegresar = new JButton();
		btnRegresar.setIcon(new ImageIcon(Perfil.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		btnRegresar.setForeground(Color.RED);
		btnRegresar.setBackground(Color.WHITE);
		btnRegresar.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		btnRegresar.setBounds(10, 11, 67, 21);
		contentPane.add(btnRegresar);
		btnRegresar.setActionCommand("Regresar");
		btnRegresar.addActionListener(this);
		
		btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setFont(new Font("Verdana", Font.BOLD, 12));
		btnCerrarSesion.setBackground(Color.WHITE);
		btnCerrarSesion.setBounds(34, 336, 139, 34);
		contentPane.add(btnCerrarSesion);
		btnCerrarSesion.setActionCommand("Cerrar");
		btnCerrarSesion.addActionListener(this);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setFont(new Font("Verdana", Font.BOLD, 12));
		btnEditar.setBounds(479, 336, 104, 34);
		contentPane.add(btnEditar);
		btnEditar.addActionListener(this);
		btnEditar.setActionCommand("Editar");
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
			btnCerrarSesion.setEnabled(false);
		}
		else if (command.contentEquals("Guardar")) {
			String nombre = txtNombre.getText();
			String facultad = perfilCmbs.cmbFacultades.getSelectedItem().toString();
			String genero = perfilCmbs.cmbGeneros.getSelectedItem().toString();
			String instrumento = perfilCmbs.cmbInstrumentos.getSelectedItem().toString();
			String descripcion = txtDescripcion.getText();
			boolean disponible = true;
			
			Usuario editado = new Usuario(cuenta.getId(), cuenta.getCor_usu(), cuenta.getPas_usu(), cuenta.getTip_usu(), nombre,
					genero, instrumento, facultad, descripcion, disponible);
			
			boolean cuentaEditada = EditarPerfil.editarCuenta(cuenta, editado);
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
				txtDescripcion.setText(editado.getDes_usu());
				
				btnEditar.setText("Editar");
				btnEditar.setActionCommand("Editar");
				btnRegresar.setEnabled(true);
				btnCerrarSesion.setEnabled(true);
				
				cuenta = editado;
			}
		}
		
		else if (command.contentEquals("Regresar")) {
			Feed feedframe = new Feed(cuenta);
			Point punto = this.getLocation();
			feedframe.setLocation(punto);
			feedframe.setVisible(true);
			PerfilPropio.this.dispose();
		}
		else if(command.contentEquals("Cerrar")) {
			UserLogin inicio = new UserLogin();
			Point punto = this.getLocation();
			inicio.setLocation(punto);
			inicio.setVisible(true);
			PerfilPropio.this.dispose();
		}
	}
}
