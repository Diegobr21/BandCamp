package interfaces;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class ComboBoxes {
	static JComboBox<String> cmbFacultades, cmbGeneros, cmbInstrumentos;
	
	public ComboBoxes(){
		cmbFacultades = new JComboBox<String>();
		cmbFacultades.setModel(new DefaultComboBoxModel<String>(
				new String[] {"FIME", "FCQ", "FACPYA", "FCFM", "FARQ", "FACDYC", "FIC", "FAV", "FOD"}));
		cmbFacultades.setBounds(358, 81, 158, 20);
		cmbFacultades.setEditable(false);
		
		cmbGeneros = new JComboBox<String>();
		cmbGeneros.setModel(new DefaultComboBoxModel<String>(
				new String[] {"Rock", "Jazz", "Reggaeton", "Rap", "Metal", "Indie", "K-Pop", "Pop", "Clásica"}));
		cmbGeneros.setBounds(358, 206, 158, 20);
		cmbGeneros.setEditable(false);
				
		cmbInstrumentos = new JComboBox<String>();
		cmbInstrumentos.setModel(new DefaultComboBoxModel<String>(
				new String[] {"Guitarra", "Batería", "Piano", "Xilófono", "Flauta",
						"Violín", "Trompeta", "Oboe", "Ocarina", "Bajo"}));
		cmbInstrumentos.setBounds(358, 274, 158, 20);
		cmbInstrumentos.setEditable(false);
	}
}
