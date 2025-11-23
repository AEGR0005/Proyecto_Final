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
	private JTextField txtFiltroPaciente;

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
		setBounds(100, 100, 693, 540);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Registrar Cita", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 128)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			panel.setBackground(new Color(240, 248, 255));

			JLabel lblNewLabel = new JLabel("C\u00F3digo:");
			lblNewLabel.setForeground(new Color(0, 0, 128));
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNewLabel.setBounds(31, 45, 78, 30);
			panel.add(lblNewLabel);

			JLabel lblPaciente = new JLabel("Paciente:");
			lblPaciente.setForeground(new Color(0, 0, 128));
			lblPaciente.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblPaciente.setBounds(31, 95, 113, 30);
			panel.add(lblPaciente);

			JLabel lblDoctor = new JLabel("Doctor:");
			lblDoctor.setForeground(new Color(0, 0, 128));
			lblDoctor.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblDoctor.setBounds(31, 180, 113, 30);
			panel.add(lblDoctor);

			txtFiltroPaciente = new JTextField();
			txtFiltroPaciente.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
			txtFiltroPaciente.setBorder(new LineBorder(new Color(65, 105, 225)));
			txtFiltroPaciente.setBounds(154, 98, 453, 26);
			txtFiltroPaciente.addKeyListener(new KeyAdapter() {
				@Override
<<<<<<< HEAD
				public void focusLost(FocusEvent e) {
					auxPaciente = Clinica.getInstancia().buscarPacienteXId(txtIdPaciente.getText());
					
					if(auxPaciente == null) {
						//JOptionPane.showMessageDialog(null, "El paciente ingresado no existe.", "Paciente", JOptionPane.ERROR_MESSAGE);
						lblWarnPat.setText("*Paciente no existe.");
						lblWarnPat.setVisible(true);
						txtIdPaciente.setText(" ");
					}else {
						txtNomPac.setText(auxPaciente.getNombre());
					}
=======
				public void keyReleased(KeyEvent e) {
					filtrarPacientes(txtFiltroPaciente.getText());
>>>>>>> branch 'master' of https://github.com/AEGR0005/Proyecto_Final.git
				}
			});
			panel.add(txtFiltroPaciente);
			txtFiltroPaciente.setColumns(10);

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

			lblWarnPat = new JLabel("Debe seleccionar un paciente.");
			lblWarnPat.setFont(new Font("Tahoma", Font.PLAIN, 10));
			lblWarnPat.setForeground(Color.RED);
			lblWarnPat.setVisible(false);
			lblWarnPat.setBounds(154, 159, 192, 10);
			panel.add(lblWarnPat);

			cargarPacientes();

			txtFiltroDoctor = new JTextField();
			txtFiltroDoctor.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
			txtFiltroDoctor.setBorder(new LineBorder(new Color(65, 105, 225)));
			txtFiltroDoctor.setBounds(154, 183, 453, 26);
			txtFiltroDoctor.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					filtrarDoctores(txtFiltroDoctor.getText());
				}
			});
			panel.add(txtFiltroDoctor);
			txtFiltroDoctor.setColumns(10);

			cbxDoctor = new JComboBox<>();
			cbxDoctor.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
			cbxDoctor.setBorder(new LineBorder(new Color(65, 105, 225)));
			cbxDoctor.setBounds(154, 215, 453, 26);
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
			lblWarnDoc.setBounds(154, 244, 192, 10);
			panel.add(lblWarnDoc);

			cargarDoctores();

			txtIdCita = new JTextField();
			txtIdCita.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
			txtIdCita.setEditable(false);
			txtIdCita.setForeground(new Color(0, 0, 0));
			txtIdCita.setBounds(129, 48, 146, 26);
			txtIdCita.setText("CI-"+Clinica.genCodigoCitas);
			panel.add(txtIdCita);
			txtIdCita.setColumns(10);

			spnFecha = new JSpinner();
			spnFecha.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
			spnFecha.setBorder(new LineBorder(new Color(65, 105, 225)));
			spnFecha.setModel(new SpinnerDateModel(new Date(1762574400000L), null, null, Calendar.DAY_OF_YEAR));
			spnFecha.setBounds(443, 48, 164, 26);
			panel.add(spnFecha);

			JLabel lblFecha = new JLabel("Fecha:");
			lblFecha.setForeground(new Color(0, 0, 128));
			lblFecha.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblFecha.setBounds(367, 45, 78, 30);
			panel.add(lblFecha);

			JLabel lblMotivo = new JLabel("Motivo:");
			lblMotivo.setForeground(new Color(0, 0, 128));
			lblMotivo.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblMotivo.setBounds(31, 270, 113, 30);
			panel.add(lblMotivo);
<<<<<<< HEAD
			
			txtNomPac = new JTextField();
			txtNomPac.setEditable(false);
			txtNomPac.setBounds(290, 98, 317, 26);
			panel.add(txtNomPac);
			txtNomPac.setColumns(10);
			
			txtNomDoc = new JTextField();
			txtNomDoc.setEditable(false);
			txtNomDoc.setColumns(10);
			txtNomDoc.setBounds(290, 148, 317, 26);
			panel.add(txtNomDoc);
			
			lblWarnPat = new JLabel("");
			lblWarnPat.setFont(new Font("Tahoma", Font.PLAIN, 10));
			lblWarnPat.setForeground(Color.RED);
			lblWarnPat.setVisible(false);
			lblWarnPat.setBounds(129, 82, 192, 10);
			panel.add(lblWarnPat);
			
			Clinica.getInstancia().createPaciente("Liz Marie", "031019");
=======

			cbxMotivo = new JComboBox<>();
			cbxMotivo.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>", "Entrega de resultados", "Consulta"}));
			cbxMotivo.setBounds(31, 310, 244, 22);
			panel.add(cbxMotivo);
>>>>>>> branch 'master' of https://github.com/AEGR0005/Proyecto_Final.git
		}
		{
			JPanel buttonPane = new JPanel();
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
				okButton.setFont(new Font("Tahoma", Font.BOLD, 16));
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
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 16));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void cargarPacientes() {
		cbxPaciente.removeAllItems();
		cbxPaciente.addItem("<Seleccione un paciente>");
		
		for(Paciente p : Clinica.getInstancia().getPacientes()) {
			cbxPaciente.addItem(p.getNombre() + " - " + p.getCedula());
		}
	}
	
	private void cargarDoctores() {
		cbxDoctor.removeAllItems();
		cbxDoctor.addItem("<Seleccione un doctor>");
		
		for(Doctor d : Clinica.getInstancia().getDoctores()) {
			cbxDoctor.addItem("Doc. " + d.getNombre());
		}
	}
	
	private void filtrarPacientes(String filtro) {
		cbxPaciente.removeAllItems();
		cbxPaciente.addItem("<Seleccione un paciente>");
		
		String filtroLower = filtro.toLowerCase().trim();
		
		for(Paciente p : Clinica.getInstancia().getPacientes()) {
			String nombreCompleto = (p.getNombre() + " - " + p.getCedula()).toLowerCase();
			
			if(filtroLower.isEmpty() || nombreCompleto.contains(filtroLower)) {
				cbxPaciente.addItem(p.getNombre() + " - " + p.getCedula());
			}
		}
		
		if(cbxPaciente.getItemCount() > 1) {
			cbxPaciente.setSelectedIndex(1);
		}
	}
	
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