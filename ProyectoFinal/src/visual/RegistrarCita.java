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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.TitledBorder;
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

	public static void main(String[] args) {
		try {
			RegistrarCita dialog = new RegistrarCita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegistrarCita() {
		setTitle("Registrar: Cita");
		setBounds(100, 100, 693, 540);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(135, 206, 235), 2), "Registrar Cita", TitledBorder.CENTER, TitledBorder.TOP, new Font("Bahnschrift", Font.BOLD, 14), new Color(70, 130, 180)));
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.setBackground(new Color(240, 248, 255));
		
		JLabel lblNewLabel = new JLabel("Código:");
		lblNewLabel.setForeground(new Color(70, 130, 180));
		lblNewLabel.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		lblNewLabel.setBounds(31, 45, 78, 30);
		panel.add(lblNewLabel);
		
		JLabel lblPaciente = new JLabel("Paciente:");
		lblPaciente.setForeground(new Color(70, 130, 180));
		lblPaciente.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		lblPaciente.setBounds(31, 95, 113, 30);
		panel.add(lblPaciente);
		
		JLabel lblDoctor = new JLabel("Doctor:");
		lblDoctor.setForeground(new Color(70, 130, 180));
		lblDoctor.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		lblDoctor.setBounds(31, 180, 113, 30);
		panel.add(lblDoctor);
		
		txtFiltroPaciente = new JTextField();
		txtFiltroPaciente.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		txtFiltroPaciente.setBackground(new Color(224, 247, 250));
		txtFiltroPaciente.setBorder(new LineBorder(new Color(173, 216, 230), 1));
		txtFiltroPaciente.setBounds(154, 98, 453, 26);
		txtFiltroPaciente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtrarPacientes(txtFiltroPaciente.getText());
			}
		});
		panel.add(txtFiltroPaciente);
		txtFiltroPaciente.setColumns(10);
		
		cbxPaciente = new JComboBox<>();
		cbxPaciente.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		cbxPaciente.setBackground(new Color(224, 247, 250));
		cbxPaciente.setBorder(new LineBorder(new Color(173, 216, 230), 1));
		cbxPaciente.setBounds(154, 130, 453, 26);
		cbxPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarPaciente();
			}
		});
		panel.add(cbxPaciente);
		
		lblWarnPat = new JLabel("Debe seleccionar un paciente.");
		lblWarnPat.setFont(new Font("Bahnschrift", Font.PLAIN, 10));
		lblWarnPat.setForeground(Color.RED);
		lblWarnPat.setVisible(false);
		lblWarnPat.setBounds(154, 159, 192, 10);
		panel.add(lblWarnPat);
		
		cargarPacientes();
		
		txtFiltroDoctor = new JTextField();
		txtFiltroDoctor.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		txtFiltroDoctor.setBackground(new Color(224, 247, 250));
		txtFiltroDoctor.setBorder(new LineBorder(new Color(173, 216, 230), 1));
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
		cbxDoctor.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		cbxDoctor.setBackground(new Color(224, 247, 250));
		cbxDoctor.setBorder(new LineBorder(new Color(173, 216, 230), 1));
		cbxDoctor.setBounds(154, 215, 453, 26);
		cbxDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarDoctor();
			}
		});
		panel.add(cbxDoctor);
		
		lblWarnDoc = new JLabel("Debe seleccionar un doctor.");
		lblWarnDoc.setForeground(Color.RED);
		lblWarnDoc.setFont(new Font("Bahnschrift", Font.PLAIN, 10));
		lblWarnDoc.setVisible(false);
		lblWarnDoc.setBounds(154, 244, 192, 10);
		panel.add(lblWarnDoc);
		
		cargarDoctores();
		
		txtIdCita = new JTextField();
		txtIdCita.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		txtIdCita.setEditable(false);
		txtIdCita.setForeground(new Color(70, 130, 180));
		txtIdCita.setBackground(new Color(224, 247, 250));
		txtIdCita.setBorder(new LineBorder(new Color(173, 216, 230), 1));
		txtIdCita.setBounds(129, 48, 146, 26);
		txtIdCita.setText("CI-"+Clinica.genCodigoCitas);
		panel.add(txtIdCita);
		txtIdCita.setColumns(10);
		
		spnFecha = new JSpinner();
		spnFecha.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		spnFecha.setBackground(new Color(224, 247, 250));
		spnFecha.setBorder(new LineBorder(new Color(173, 216, 230), 1));
		spnFecha.setModel(new SpinnerDateModel(new Date(1762574400000L), null, null, Calendar.DAY_OF_YEAR));
		Formato.setSpinner(spnFecha);
		spnFecha.setBounds(443, 48, 164, 26);
		panel.add(spnFecha);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setForeground(new Color(70, 130, 180));
		lblFecha.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		lblFecha.setBounds(367, 45, 78, 30);
		panel.add(lblFecha);
		
		JLabel lblMotivo = new JLabel("Motivo:");
		lblMotivo.setForeground(new Color(70, 130, 180));
		lblMotivo.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		lblMotivo.setBounds(31, 270, 113, 30);
		panel.add(lblMotivo);
		
		cbxMotivo = new JComboBox<>();
		cbxMotivo.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		cbxMotivo.setBackground(new Color(224, 247, 250));
		cbxMotivo.setBorder(new LineBorder(new Color(173, 216, 230), 1));
		cbxMotivo.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>", "Entrega de resultados", "Consulta"}));
		cbxMotivo.setBounds(154, 273, 244, 26);
		panel.add(cbxMotivo);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(240, 248, 255));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
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
		okButton.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		okButton.setBackground(new Color(176, 224, 230));
		okButton.setForeground(new Color(70, 130, 180));
		okButton.setBorder(new LineBorder(new Color(135, 206, 235), 2));
		okButton.setFocusPainted(false);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		cancelButton.setBackground(new Color(176, 224, 230));
		cancelButton.setForeground(new Color(70, 130, 180));
		cancelButton.setBorder(new LineBorder(new Color(135, 206, 235), 2));
		cancelButton.setFocusPainted(false);
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
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
		if(lblWarnPat.isVisible() || lblWarnDoc.isVisible() || auxPaciente == null || auxDoctor == null)
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