package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Cita;
import logico.Clinica;
import logico.Doctor;
import logico.Paciente;

import java.awt.Font;
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

public class RegistrarCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIdPaciente;
	private JTextField txtIdDoctor;
	private JTextField txtIdCita;
	private JTextField textField;
	private JTextField txtNomPac;
	private JTextField txtNomDoc;
	private Cita auxCita;
	private Paciente auxPaciente;
	private Doctor auxDoctor;
	private JTextArea txtAMotivo;
	private JSpinner spnFecha;
	private JLabel lblWarnPat;

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
		setBounds(100, 100, 693, 473);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("C\u00F3digo:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNewLabel.setBounds(31, 45, 78, 30);
			panel.add(lblNewLabel);
			
			JLabel lblPaciente = new JLabel("Paciente:");
			lblPaciente.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblPaciente.setBounds(31, 95, 113, 30);
			panel.add(lblPaciente);
			
			JLabel lblDoctor = new JLabel("Doctor:");
			lblDoctor.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblDoctor.setBounds(31, 145, 113, 30);
			panel.add(lblDoctor);
			
			txtIdPaciente = new JTextField();
			txtIdPaciente.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					auxPaciente = Clinica.getInstancia().buscarPacienteXId(txtIdPaciente.getText());
					
					if(auxPaciente == null) {
						//JOptionPane.showMessageDialog(null, "El paciente ingresado no existe.", "Paciente", JOptionPane.ERROR_MESSAGE);
						lblWarnPat.setText("*Paciente no existe.");
						lblWarnPat.setVisible(true);
						txtIdPaciente.setText("");
					}else {
						txtNomPac.setText(auxPaciente.getNombre());
					}
				}
			});
			txtIdPaciente.setBounds(129, 98, 146, 26);
			panel.add(txtIdPaciente);
			txtIdPaciente.setColumns(10);
			
			txtIdDoctor = new JTextField();
			txtIdDoctor.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					if(auxDoctor == null) {
						JOptionPane.showMessageDialog(null, "El doctor ingresado no existe.", "Doctor", JOptionPane.ERROR_MESSAGE);
					}else {
						//txtNomDoc.setText(auxDoctor.getNombre());
					}
				}
			});
			txtIdDoctor.setBounds(129, 148, 146, 26);
			panel.add(txtIdDoctor);
			txtIdDoctor.setColumns(10);
			
			txtIdCita = new JTextField();
			txtIdCita.setEnabled(false);
			txtIdCita.setBounds(129, 48, 146, 26);
			txtIdCita.setText("CI-"+Clinica.genCodigoCitas);
			panel.add(txtIdCita);
			txtIdCita.setColumns(10);
			
			spnFecha = new JSpinner();
			spnFecha.setModel(new SpinnerDateModel(new Date(1762574400000L), null, null, Calendar.DAY_OF_YEAR));
			spnFecha.setBounds(109, 230, 164, 26);
			panel.add(spnFecha);
			
			JLabel lblFecha = new JLabel("Fecha:");
			lblFecha.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblFecha.setBounds(31, 227, 78, 30);
			panel.add(lblFecha);
			
			JLabel lblEstado = new JLabel("Estado:");
			lblEstado.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblEstado.setBounds(357, 227, 78, 30);
			panel.add(lblEstado);
			
			textField = new JTextField();
			textField.setEnabled(false);
			textField.setBounds(443, 230, 164, 26);
			panel.add(textField);
			textField.setColumns(10);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(109, 273, 498, 40);
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel_1.add(scrollPane, BorderLayout.CENTER);
			
			txtAMotivo = new JTextArea();
			scrollPane.setViewportView(txtAMotivo);
			txtAMotivo.setLineWrap(true);
			txtAMotivo.setWrapStyleWord(true);
			
			JLabel lblMotivo = new JLabel("Motivo:");
			lblMotivo.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblMotivo.setBounds(31, 273, 78, 30);
			panel.add(lblMotivo);
			
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
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String idCita = txtIdCita.getText();
						String estado = "Programada";
						Date fecha = (Date)spnFecha.getValue();
						String motivo = txtAMotivo.getText();
						auxCita = new Cita(idCita, auxPaciente, auxDoctor, fecha, motivo);
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
}
