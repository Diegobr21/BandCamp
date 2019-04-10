package interfaces;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * Contiene los comboboxes de las facultades, de los géneros y de los instrumentos.
 */
public class ComboBoxes {
	JComboBox<String> cmbFacultades, cmbGeneros, cmbInstrumentos;
	
	/**
	 * Define a los comboboxes, les agrega las opciones y deshabilita la edición.
	 */
	public ComboBoxes(){
		JComboBox<String> cmbFacultades = new JComboBox<String>();
		cmbFacultades.setModel(new DefaultComboBoxModel<String>(
				new String[] {"FIME", "FCQ", "FACPYA", "FCFM", "FARQ", "FACDYC", "FIC", "FAV", "FOD"}));
		cmbFacultades.setEditable(false);
		
		JComboBox<String> cmbGeneros = new JComboBox<String>();
		cmbGeneros.setModel(new DefaultComboBoxModel<String>(
				new String[] {"Rock", "Jazz", "Reggaeton", "Rap", "Metal", "Indie", "K-Pop", "Pop", "Clásica"}));
		cmbGeneros.setEditable(false);
				
		JComboBox<String> cmbInstrumentos = new JComboBox<String>();
		cmbInstrumentos.setModel(new DefaultComboBoxModel<String>(
				new String[] {"Guitarra", "Batería", "Piano", "Xilófono", "Flauta",
						"Violín", "Trompeta", "Oboe", "Ocarina", "Bajo"}));
		cmbInstrumentos.setEditable(false);
	}
}
