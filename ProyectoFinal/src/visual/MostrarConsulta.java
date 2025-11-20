package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Doctor;
import logico.Paciente;

import javax.swing.event.PopupMenuEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MostrarConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPaciente;
	private JComboBox cbxDoctores;
	private boolean showDoctores = false;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private Doctor doctor;

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
		setBounds(100, 100, 619, 577);
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
		panelBarra.setBounds(15, 16, 568, 116);
		getContentPane().add(panelBarra);
		panelBarra.setLayout(null);

		JLabel lblNewLabel = new JLabel("Paciente:");
		lblNewLabel.setBounds(15, 49, 69, 20);
		panelBarra.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		DefaultComboBoxModel mainModel = new DefaultComboBoxModel(new String[] {"<B\u00FAsqueda>", "C\u00F3digo", "Lista"});
		DefaultComboBoxModel codigoModel = new DefaultComboBoxModel(new String[] {"<B\u00FAsqueda>", "", "Lista"});
		DefaultComboBoxModel doctoresModel = new DefaultComboBoxModel(new String[] {"<Elegir>", "A", "B"});


		
		Clinica.getInstancia().crearPacientePrueba("Cristina Rodríguez Fernández", "0315400566");
		Clinica.getInstancia().crearPacientePrueba("Altagracia Rodríguez Fernández", "0315400566");
		Clinica.getInstancia().crearPacientePrueba("María Alejandra Rodríguez Fernández", "0315400566");
		Clinica.getInstancia().crearDoctorPrueba();
		doctor = Clinica.getInstancia().getDoctores().get(0);
		doctor.setPacientes(Clinica.getInstancia().getPacientes());
		

		txtPaciente = new JTextField();
		txtPaciente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				listarPacientes(new String (""+e.getKeyChar()));
				//listarPacientes(txtPaciente.getText());
				
			}
		});
		//txtPaciente.setText("Mar\u00EDa Alejandra Rodr\u00EDguez Fern\u00E1ndez (0315400566)");
		txtPaciente.setBounds(94, 46, 455, 26);
		panelBarra.add(txtPaciente);
		txtPaciente.setVisible(true);
		txtPaciente.setColumns(10);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaInicio.setBounds(15, 80, 96, 20);
		panelBarra.add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin:");
		lblFechaFin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaFin.setBounds(319, 80, 96, 20);
		panelBarra.add(lblFechaFin);
		
		JSpinner spnFecIni = new JSpinner();
		spnFecIni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spnFecIni.setModel(new SpinnerDateModel(new Date(1763265600000L), new Date(1763265600000L), null, Calendar.DAY_OF_YEAR));
		spnFecIni.setBounds(117, 78, 150, 26);
		panelBarra.add(spnFecIni);
		
		JSpinner spnFecFin = new JSpinner();
		spnFecFin.setModel(new SpinnerDateModel(new Date(1763265600000L), new Date(1763265600000L), null, Calendar.DAY_OF_YEAR));
		spnFecFin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spnFecFin.setBounds(399, 78, 150, 26);
		panelBarra.add(spnFecFin);
		
		JLabel lblDoctor = new JLabel("Doctor:");
		lblDoctor.setBounds(15, 14, 78, 20);
		panelBarra.add(lblDoctor);
		lblDoctor.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblNomPac = new JLabel("Liz Marie Torres");
		lblNomPac.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNomPac.setBounds(84, 14, 388, 20);
		panelBarra.add(lblNomPac);
		
		JPanel panelTable = new JPanel();
		panelTable.setBounds(15, 135, 568, 344);
		getContentPane().add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		
		
		model = new DefaultTableModel();
		table = new JTable();
		String[] headers = {"Nombre","Cédula"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

	}
	
	private void listarPacientes(String nombre) {
		
			model.setRowCount(0);
			row = new Object[model.getColumnCount()];
			
			for (Paciente paciente : doctor.getPacientes()) {
				System.out.println(nombre);
				if(paciente.getNombre().indexOf(nombre)!= -1) {
					//System.out.println("Alright");
					row[0] = paciente.getNombre();
					row[1] = paciente.getCedula();
					
					model.addRow(row);
				}
				
			}

		
	}
}
