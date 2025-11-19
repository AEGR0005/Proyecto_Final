package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import jdk.nashorn.internal.runtime.regexp.JoniRegExp.Factory;
import logico.Cita;
import logico.Clinica;
import logico.Doctor;
import logico.Paciente;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JComboBox;

public class RegistrarCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIdPaciente;
	private JTextField txtIdDoctor;
	private JTextField txtIdCita;
	private JTextField txtNomPac;
	private JTextField txtNomDoc;
	private JTextArea txtASintomas;
	private Cita auxCita;
	private Paciente auxPaciente;
	private Doctor auxDoctor;
	private JSpinner spnFecha;
	private JLabel lblWarnPat;
	private JLabel lblWarnDoc;

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
		setBounds(100, 100, 693, 448);
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
			lblDoctor.setBounds(31, 145, 113, 30);
			panel.add(lblDoctor);

			txtIdPaciente = new JTextField();
			txtIdPaciente.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
			txtIdPaciente.setBorder(new LineBorder(new Color(65, 105, 225)));

			txtIdPaciente.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					
					txtIdPaciente.setText(txtIdPaciente.getText().toUpperCase());
					auxPaciente = Clinica.getInstancia().buscarPacienteXId(txtIdPaciente.getText());

					if(auxPaciente != null) {
						txtNomPac.setText(auxPaciente.getNombre());
						lblWarnPat.setVisible(false);
					}else {
						//JOptionPane.showMessageDialog(null, "El paciente ingresado no existe.", "Información", JOptionPane.ERROR_MESSAGE);
						lblWarnPat.setVisible(true);
						txtIdPaciente.setText("");
						txtNomPac.setText("");
					}
				}
				
			});
			txtIdPaciente.setBounds(129, 98, 146, 26);
			panel.add(txtIdPaciente);
			txtIdPaciente.setColumns(10);

			Clinica.getInstancia().crearPacientePrueba("Liz Marie", "1016");

			txtIdDoctor = new JTextField();
			txtIdDoctor.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
			txtIdDoctor.setBorder(new LineBorder(new Color(65, 105, 225)));
			txtIdDoctor.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					txtIdDoctor.setText(txtIdDoctor.getText().toUpperCase());
					auxDoctor = Clinica.getInstancia().buscarDoctorXId(txtIdDoctor.getText());

					if(auxDoctor != null) {
						txtNomDoc.setText(auxDoctor.getNombre());
						lblWarnDoc.setVisible(false);
					}else {
						//JOptionPane.showMessageDialog(null, "El doctor ingresado no existe.","Información", JOptionPane.ERROR_MESSAGE);
						lblWarnDoc.setVisible(true);
						txtIdDoctor.setText("");
						txtNomDoc.setText("");
					}
				}
			});
			txtIdDoctor.setBounds(129, 148, 146, 26);
			panel.add(txtIdDoctor);
			txtIdDoctor.setColumns(10);

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
			lblMotivo.setBounds(31, 210, 113, 30);
			panel.add(lblMotivo);

			txtNomPac = new JTextField();
			txtNomPac.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
			txtNomPac.setEditable(false);
			txtNomPac.setBounds(290, 98, 317, 26);
			panel.add(txtNomPac);
			txtNomPac.setColumns(10);

			txtNomDoc = new JTextField();
			txtNomDoc.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
			txtNomDoc.setEditable(false);
			txtNomDoc.setColumns(10);
			txtNomDoc.setBounds(290, 148, 317, 26);
			panel.add(txtNomDoc);

			lblWarnPat = new JLabel("Paciente no existe.");
			lblWarnPat.setFont(new Font("Tahoma", Font.PLAIN, 10));
			lblWarnPat.setForeground(Color.RED);
			lblWarnPat.setVisible(false);
			lblWarnPat.setBounds(129, 82, 192, 10);
			panel.add(lblWarnPat);

			lblWarnDoc = new JLabel("Doctor no existe.");
			lblWarnDoc.setForeground(Color.RED);
			lblWarnDoc.setFont(new Font("Tahoma", Font.PLAIN, 10));
			lblWarnDoc.setVisible(false);
			lblWarnDoc.setBounds(129, 129, 89, 20);
			panel.add(lblWarnDoc);
			
		
			JComboBox<String> cbxMotivo = new JComboBox<>();
			cbxMotivo.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>", "Entrega de resultados", "Consulta"}));
			cbxMotivo.setBounds(31, 255, 244, 22);
			panel.add(cbxMotivo);
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
							String idCita = txtIdCita.getText();
							String estado = "Programada";
							Date fecha = (Date)spnFecha.getValue();
							String sintomas = txtASintomas.getText();
							auxCita = new Cita(idCita, auxPaciente, auxDoctor, fecha, sintomas);
							//System.out.println("ID: "+auxCita.getId()+" NomPac: "+auxCita.getPaciente().getNombre());
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
	
	private boolean infoCorrecta() {
	    boolean correcto = true;
	    
	    if(lblWarnPat.isVisible() || lblWarnDoc.isVisible() || txtASintomas.getText().trim().isEmpty())
	        correcto = false;
	    
	    return correcto;
	}
	
	private void checkEmptyFields() {
		if(txtIdPaciente.getText().trim() == "")
			lblWarnPat.setVisible(true);
		if(txtIdDoctor.getText().trim() == "")
			lblWarnDoc.setVisible(true);
	}
}
