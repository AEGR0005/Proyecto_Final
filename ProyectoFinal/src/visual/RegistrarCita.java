package visual;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logico.Cita;
import logico.Clinica;
import logico.Doctor;
import logico.Paciente;
import utilidad.Formato;

import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JComboBox;

public class RegistrarCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFiltroDoctor;
	private JTextField txtIdCita;
	private Cita auxCita;
	private Paciente auxPaciente;
	private Doctor auxDoctor;
	private JSpinner spnFecha;
	private JLabel lblWarnPat;
	private JLabel lblWarnDoc;
	private JComboBox<String> cbxPaciente;
	private JComboBox<String> cbxDoctor;
	private JComboBox<String> cbxMotivo;
	private JTextField txtNombrePersona;
	private JTextField txtIdPersona;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarCita dialog = new RegistrarCita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarCita() {
		setTitle("Registrar: Cita");
		setBounds(100, 100, 789, 615);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(240, 248, 255));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(35, 28, 695, 463);
			
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Registrar Cita", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(70, 130, 180)));
			contentPanel.add(panel);
			panel.setLayout(null);
			panel.setBackground(Color.WHITE);

			JLabel lblNewLabel = new JLabel("C\u00F3digo:");
			lblNewLabel.setForeground(new Color(70, 130, 180));
			lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 14));
			lblNewLabel.setBounds(31, 45, 78, 30);
			panel.add(lblNewLabel);
			
			
			JLabel lblPaciente = new JLabel("Nombre:");
			lblPaciente.setForeground(new Color(70, 130, 180));
			lblPaciente.setFont(new Font("Verdana", Font.BOLD, 14));
			lblPaciente.setBounds(31, 128, 113, 30);
			panel.add(lblPaciente);

			JLabel lblDoctor = new JLabel("Doctor:");
			lblDoctor.setForeground(new Color(70, 130, 180));
			lblDoctor.setFont(new Font("Verdana", Font.BOLD, 14));
			lblDoctor.setBounds(31, 263, 113, 30);
			panel.add(lblDoctor);

			txtNombrePersona = new JTextField();
			txtNombrePersona.setFont(new Font("Verdana", Font.PLAIN, 14));
			txtNombrePersona.setBackground(new Color(224, 247, 250));
			txtNombrePersona.setBorder(new LineBorder(new Color(70, 130, 180)));
			txtNombrePersona.setBounds(154, 132, 453, 22);
			
			txtNombrePersona.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					//filtrarPacientes(txtFiltroPaciente.getText());

				}
			});
			
			
			
			panel.add(txtNombrePersona);
			txtNombrePersona.setColumns(10);
			
			/*
			cbxPaciente = new JComboBox<>();
			cbxPaciente.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
			cbxPaciente.setBorder(new LineBorder(new Color(65, 105, 225)));
			cbxPaciente.setBounds(154, 130, 453, 26);
			cbxPaciente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seleccionarPaciente();
				}
			});
			panel.add(cbxPaciente);
			cargarPacientes();
			*/

			lblWarnPat = new JLabel("Debe seleccionar un paciente.");
			lblWarnPat.setFont(new Font("Tahoma", Font.PLAIN, 10));
			lblWarnPat.setForeground(Color.RED);
			lblWarnPat.setVisible(false);
			lblWarnPat.setBounds(154, 125, 192, 10);
			//panel.add(lblWarnPat);

			txtFiltroDoctor = new JTextField();
			txtFiltroDoctor.setFont(new Font("Verdana", Font.PLAIN, 14));
			txtFiltroDoctor.setBackground(new Color(224, 247, 250));
			txtFiltroDoctor.setBorder(new LineBorder(new Color(70, 130, 180)));
			txtFiltroDoctor.setBounds(154, 267, 453, 22);
			txtFiltroDoctor.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					filtrarDoctores(txtFiltroDoctor.getText());
				}
			});
			panel.add(txtFiltroDoctor);
			txtFiltroDoctor.setColumns(10);

			cbxDoctor = new JComboBox<>();
			cbxDoctor.setFont(new Font("Verdana", Font.PLAIN, 14));
			cbxDoctor.setBackground(new Color(240, 248, 255));
			cbxDoctor.setBorder(new LineBorder(new Color(70, 130, 180)));
			cbxDoctor.setBounds(154, 289, 453, 26);
			cbxDoctor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seleccionarDoctor();
				}
			});
			panel.add(cbxDoctor);

			lblWarnDoc = new JLabel("Debe seleccionar un doctor.");
			lblWarnDoc.setForeground(Color.RED);
			lblWarnDoc.setFont(new Font("Tahoma", Font.PLAIN, 10));
			lblWarnDoc.setVisible(false);
			lblWarnDoc.setBounds(154, 281, 192, 10);
			//panel.add(lblWarnDoc);

			cargarDoctores();

			txtIdCita = new JTextField();
			txtIdCita.setEnabled(false);
			txtIdCita.setEditable(false);
			txtIdCita.setFont(new Font("Verdana", Font.PLAIN, 16));
			txtIdCita.setBackground(Color.WHITE);
			txtIdCita.setForeground(new Color(0, 0, 0));
			txtIdCita.setBounds(106, 46, 146, 26);
			txtIdCita.setText("CI-"+Clinica.genCodigoCitas);
			panel.add(txtIdCita);
			txtIdCita.setColumns(10);

			spnFecha = new JSpinner();
			spnFecha.setFont(new Font("Verdana", Font.PLAIN, 16));
			spnFecha.setBorder(new LineBorder(new Color(70, 130, 180)));
			spnFecha.setModel(new SpinnerDateModel(new Date(1762574400000L), null, null, Calendar.DAY_OF_YEAR));
			Formato.setSpinner(spnFecha);
			Formato.colorSpinner(spnFecha, new Color(240, 248, 255));
			spnFecha.setBounds(505, 47, 164, 26);
			panel.add(spnFecha);

			JLabel lblFecha = new JLabel("Fecha:");
			lblFecha.setForeground(new Color(70, 130, 180));
			lblFecha.setFont(new Font("Verdana", Font.BOLD, 14));
			lblFecha.setBounds(411, 45, 78, 30);
			panel.add(lblFecha);

			JLabel lblMotivo = new JLabel("Motivo:");
			lblMotivo.setForeground(new Color(70, 130, 180));
			lblMotivo.setFont(new Font("Verdana", Font.BOLD, 14));
			lblMotivo.setBounds(31, 379, 113, 30);
			panel.add(lblMotivo);
			
			lblWarnPat = new JLabel("");
			lblWarnPat.setFont(new Font("Verdana", Font.PLAIN, 10));
			lblWarnPat.setForeground(Color.RED);
			lblWarnPat.setVisible(false);
			lblWarnPat.setBounds(129, 82, 192, 10);
			panel.add(lblWarnPat);

			cbxMotivo = new JComboBox<>();
			cbxMotivo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione el motivo>", "Entrega de resultados", "Consulta"}));
			cbxMotivo.setFont(new Font("Verdana", Font.PLAIN, 14));
			cbxMotivo.setBackground(new Color(240, 248, 255));
			cbxMotivo.setBorder(new LineBorder(new Color(70, 130, 180)));
			cbxMotivo.setBounds(154, 383, 244, 22);
			panel.add(cbxMotivo);
			
			JLabel lblIdentificacin = new JLabel("Identificaci\u00F3n:");
			lblIdentificacin.setForeground(new Color(70, 130, 180));
			lblIdentificacin.setFont(new Font("Verdana", Font.BOLD, 14));
			lblIdentificacin.setBounds(31, 162, 129, 30);
			panel.add(lblIdentificacin);
			
			txtIdPersona = new JTextField();
			txtIdPersona.setFont(new Font("Verdana", Font.PLAIN, 14));
			txtIdPersona.setColumns(10);
			txtIdPersona.setBorder(new LineBorder(new Color(70, 130, 180)));
			txtIdPersona.setBackground(new Color(224, 247, 250));
			txtIdPersona.setBounds(154, 166, 224, 22);
			panel.add(txtIdPersona);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setBorder(new LineBorder(new Color(70, 130, 180)));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						checkEmptyFields();
						
						if(infoCorrecta()) {
							String motivo = (String) cbxMotivo.getSelectedItem();
							Date fecha = (Date)spnFecha.getValue();
							Clinica.getInstancia().crearCita(auxPaciente, auxDoctor, fecha, motivo);
							
							JOptionPane.showMessageDialog(null, "Cita registrada exitosamente.","Información", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}else {
							JOptionPane.showMessageDialog(null, "Faltan campos por completar.","Información", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				okButton.setFont(new Font("Verdana", Font.BOLD, 14));
				okButton.setBackground(new Color(224, 247, 250));
				okButton.setForeground(new Color(70, 130, 180));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Verdana", Font.BOLD, 14));
				cancelButton.setBackground(new Color(224, 247, 250));
				cancelButton.setForeground(new Color(70, 130, 180));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/*
	private void cargarPacientes() {
		cbxPaciente.removeAllItems();
		cbxPaciente.addItem("<Seleccione un paciente>");
		
		for(Paciente p : Clinica.getInstancia().getPacientes()) {
			cbxPaciente.addItem(p.getNombre() + " - " + p.getCedula());
		}
	}*/
	
	private void cargarDoctores() {
		cbxDoctor.removeAllItems();
		cbxDoctor.addItem("<Seleccione un doctor>");
		
		for(Doctor d : Clinica.getInstancia().getDoctores()) {
			cbxDoctor.addItem("Doc. " + d.getNombre());
		}
	}
	
	/*
	private void filtrarPacientes(String filtro) {
		
		cbxPaciente.removeAllItems();
		cbxPaciente.addItem("<Seleccione un paciente>");
		
		String filtroLower = filtro.toLowerCase().trim();
		
		if(!filtroLower.isEmpty()) {
			for(Paciente p : Clinica.getInstancia().getPacientes()) {
				String nombreCompleto = (p.getNombre() + " - " + p.getCedula()).toLowerCase();
				
				
				//if(filtroLower.isEmpty() || nombreCompleto.contains(filtroLower)) {
					//cbxPaciente.addItem(p.getNombre() + " - " + p.getCedula());
				//}
				if(nombreCompleto.contains(filtroLower)) {
					cbxPaciente.addItem(p.getNombre() + " - " + p.getCedula());
				}
			}
		}
		
		if(cbxPaciente.getItemCount() > 1) {
			cbxPaciente.setSelectedIndex(1);
		}
	}*/
	
	private void filtrarDoctores(String filtro) {
		cbxDoctor.removeAllItems();
		cbxDoctor.addItem("<Seleccione un doctor>");
		
		String filtroLower = filtro.toLowerCase().trim();
		
		for(Doctor d : Clinica.getInstancia().getDoctores()) {
			String nombreDoctor = d.getNombre().toLowerCase();
			
			if(filtroLower.isEmpty() || nombreDoctor.contains(filtroLower)) {
				cbxDoctor.addItem("Doc. " + d.getNombre());
			}
		}
		
		if(cbxDoctor.getItemCount() > 1) {
			cbxDoctor.setSelectedIndex(1);
		}
	}
	
	private void seleccionarPaciente() {
		if(cbxPaciente.getSelectedIndex() > 0) {
			String seleccion = (String) cbxPaciente.getSelectedItem();
			String cedula = seleccion.substring(seleccion.lastIndexOf("-") + 1).trim();
			
			auxPaciente = null;
			int i = 0;
			while(auxPaciente == null && i < Clinica.getInstancia().getPacientes().size()) {
				if(Clinica.getInstancia().getPacientes().get(i).getCedula().equals(cedula)) {
					auxPaciente = Clinica.getInstancia().getPacientes().get(i);
				}
				i++;
			}
			
			if(auxPaciente != null) {
				lblWarnPat.setVisible(false);
			}
		} else {
			auxPaciente = null;
		}
	}
	
	private void seleccionarDoctor() {
		if(cbxDoctor.getSelectedIndex() > 0) {
			String seleccion = (String) cbxDoctor.getSelectedItem();
			String nombreDoctor = seleccion.substring(5);
			
			auxDoctor = null;
			int i = 0;
			while(auxDoctor == null && i < Clinica.getInstancia().getDoctores().size()) {
				if(Clinica.getInstancia().getDoctores().get(i).getNombre().equals(nombreDoctor)) {
					auxDoctor = Clinica.getInstancia().getDoctores().get(i);
				}
				i++;
			}
			
			if(auxDoctor != null) {
				lblWarnDoc.setVisible(false);
			}
		} else {
			auxDoctor = null;
		}
	}
	
	private boolean infoCorrecta() {
	    boolean correcto = true;
	    
	    if(lblWarnPat.isVisible() || lblWarnDoc.isVisible() || 
	       auxPaciente == null || auxDoctor == null)
	        correcto = false;
	    
	    return correcto;
	}
	
	private void checkEmptyFields() {
		if(auxPaciente == null || cbxPaciente.getSelectedIndex() == 0)
			lblWarnPat.setVisible(true);
		if(auxDoctor == null || cbxDoctor.getSelectedIndex() == 0)
			lblWarnDoc.setVisible(true);
		
	}
}