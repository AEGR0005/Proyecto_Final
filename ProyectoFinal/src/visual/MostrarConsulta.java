package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Consulta;
import logico.Doctor;
import logico.Paciente;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MostrarConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private Doctor doctor;
	private JSpinner spnFecIni;
	private JSpinner spnFecFin;
	private JTextField txtFiltroPaciente;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarConsulta dialog = new MostrarConsulta(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarConsulta(Doctor selectDoctor) {
		
		if(selectDoctor == null) {
			ArrayList<String> especialidades = new ArrayList<>();
			especialidades.add("Pediatría");
			especialidades.add("Dermatología");
			
			selectDoctor = new Doctor(
				"DOC-"+ 1,
				"Liz Marie Torres",
				20,
				especialidades
			);
		}
		
		setTitle("Listado de Consultas");
		setBounds(100, 100, 900, 577);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		doctor = selectDoctor;
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 0, 0, 0);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		JPanel panelBarra = new JPanel();
		panelBarra.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		panelBarra.setBounds(15, 16, 853, 130);
		getContentPane().add(panelBarra);
		panelBarra.setLayout(null);
		
		JLabel lblDoctor = new JLabel("Doctor:");
		lblDoctor.setBounds(15, 14, 78, 20);
		panelBarra.add(lblDoctor);
		lblDoctor.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblNomDoc = new JLabel(selectDoctor.getNombre());
		lblNomDoc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNomDoc.setBounds(84, 14, 388, 20);
		panelBarra.add(lblNomDoc);
		
		JLabel lblPaciente = new JLabel("Paciente:");
		lblPaciente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPaciente.setBounds(15, 50, 96, 20);
		panelBarra.add(lblPaciente);
		
		txtFiltroPaciente = new JTextField();
		txtFiltroPaciente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFiltroPaciente.setBounds(117, 48, 403, 26);
		txtFiltroPaciente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				aplicarFiltros();
			}
		});
		panelBarra.add(txtFiltroPaciente);
		txtFiltroPaciente.setColumns(10);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaInicio.setBounds(15, 90, 96, 20);
		panelBarra.add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin:");
		lblFechaFin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaFin.setBounds(290, 90, 96, 20);
		panelBarra.add(lblFechaFin);
		
		spnFecIni = new JSpinner();
		spnFecIni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spnFecIni.setModel(new SpinnerDateModel(new Date(1704085200000L), new Date(1704085200000L), null, Calendar.DAY_OF_YEAR));
		spnFecIni.setEditor(new JSpinner.DateEditor(spnFecIni, "dd/MM/yyyy"));
		spnFecIni.setBounds(117, 88, 150, 26);
		panelBarra.add(spnFecIni);
		
		spnFecFin = new JSpinner();
		spnFecFin.setModel(new SpinnerDateModel(new Date(), new Date(1704085200000L), null, Calendar.DAY_OF_YEAR));
		spnFecFin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spnFecFin.setEditor(new JSpinner.DateEditor(spnFecFin, "dd/MM/yyyy"));
		spnFecFin.setBounds(370, 88, 150, 26);
		panelBarra.add(spnFecFin);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aplicarFiltros();
			}
		});
		btnFiltrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnFiltrar.setBounds(550, 88, 100, 26);
		panelBarra.add(btnFiltrar);
		
		JButton btnMostrarTodas = new JButton("Mostrar Todas");
		btnMostrarTodas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFiltroPaciente.setText("");
				listarConsultas(null, null, "");
			}
		});
		btnMostrarTodas.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnMostrarTodas.setBounds(670, 88, 150, 26);
		panelBarra.add(btnMostrarTodas);
		
		JPanel panelTable = new JPanel();
		panelTable.setBounds(15, 150, 853, 330);
		getContentPane().add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; 
			}
		};
		
		table = new JTable();
		String[] headers = {
			"Código", 
			"Paciente", 
			"Fecha", 
			"Diagnóstico", 
			"Importante"
		};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(80);  
		table.getColumnModel().getColumn(1).setPreferredWidth(200); 
		table.getColumnModel().getColumn(2).setPreferredWidth(100); 
		table.getColumnModel().getColumn(3).setPreferredWidth(300); 
		table.getColumnModel().getColumn(4).setPreferredWidth(80);  
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					int row = table.getSelectedRow();
					if(row >= 0) {
						String codigoConsulta = (String) table.getValueAt(row, 0);
						mostrarDetalleConsulta(codigoConsulta);
					}
				}
			}
		});
		
		scrollPane.setViewportView(table);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(15, 485, 853, 40);
		getContentPane().add(panelBotones);
		panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton btnVerDetalle = new JButton("Ver Detalle");
		btnVerDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row >= 0) {
					String codigoConsulta = (String) table.getValueAt(row, 0);
					mostrarDetalleConsulta(codigoConsulta);
				} else {
					javax.swing.JOptionPane.showMessageDialog(null, 
						"Debe seleccionar una consulta.", 
						"Información", 
						javax.swing.JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnVerDetalle.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelBotones.add(btnVerDetalle);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelBotones.add(btnCerrar);
		
		listarConsultas(null, null, "");
	}
	
	private void aplicarFiltros() {
		Date fechaInicio = (Date) spnFecIni.getValue();
		Date fechaFin = (Date) spnFecFin.getValue();
		String nombrePaciente = txtFiltroPaciente.getText().trim();
		listarConsultas(fechaInicio, fechaFin, nombrePaciente);
	}
	
	private void listarConsultas(Date fechaInicio, Date fechaFin, String nombrePaciente) {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		ArrayList<Consulta> consultas = getConsultasXDoctor(doctor);
		
		for(Consulta consulta : consultas) {
			if(fechaInicio != null && fechaFin != null) {
				if(!dentroDelRango(consulta.getFecha(), fechaInicio, fechaFin)) {
					continue;
				}
			}
			
			if(nombrePaciente != null && !nombrePaciente.isEmpty()) {
				String nombreConsulta = consulta.getPaciente().getNombre().toLowerCase();
				if(!nombreConsulta.contains(nombrePaciente.toLowerCase())) {
					continue;
				}
			}
			
			row[0] = consulta.getId();
			row[1] = consulta.getPaciente().getNombre();
			row[2] = formatearFecha(consulta.getFecha());
			row[3] = obtenerResumenDiagnostico(consulta);
			row[4] = consulta.getEsImportante() ? "Sí" : "No";
			model.addRow(row);
		}
	}
	
	private ArrayList<Consulta> getConsultasXDoctor(Doctor doctor) {
		ArrayList<Consulta> consultasDoctor = new ArrayList<>();
		
		for(Paciente paciente : doctor.getPacientes()) {
			for(Consulta consulta : paciente.getHistorialClinico()) {
				if(consulta.getDoctor().getIdDoctor().equals(doctor.getIdDoctor())) {
					consultasDoctor.add(consulta);
				}
			}
		}
		
		return consultasDoctor;
	}
	
	private boolean dentroDelRango(Date fecha, Date inicio, Date fin) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		int fechaInt = Integer.parseInt(sdf.format(fecha));
		int inicioInt = Integer.parseInt(sdf.format(inicio));
		int finInt = Integer.parseInt(sdf.format(fin));
		
		return fechaInt >= inicioInt && fechaInt <= finInt;
	}
	
	private String formatearFecha(Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(fecha);
	}
	
	private String obtenerResumenDiagnostico(Consulta consulta) {
		if(consulta.getDiagnostico() != null) {
			String desc = consulta.getDiagnostico().getDescripcion();
			if(desc != null && !desc.isEmpty()) {
				return desc.length() > 50 ? 
					desc.substring(0, 50) + "..." : desc;
			}
		}
		return "Sin diagnóstico";
	}
	
	private void mostrarDetalleConsulta(String codigo) {
		Consulta consulta = buscarConsultaPorCodigo(codigo);
		if(consulta != null) {
			DetalleConsulta dialogo = new DetalleConsulta(consulta);
			dialogo.setModal(true);
			dialogo.setVisible(true);
		}
	}
	
	private Consulta buscarConsultaPorCodigo(String codigo) {
		for(Paciente paciente : doctor.getPacientes()) {
			for(Consulta consulta : paciente.getHistorialClinico()) {
				if(consulta.getId().equals(codigo)) {
					return consulta;
				}
			}
		}
		return null;
	}
}