package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import logico.Clinica;
import logico.Cita;
import logico.Consulta;
import logico.Diagnostico;
import logico.EstadoCita;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;

public class RealizarConsulta extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private JComboBox cbxCita;
    private JTextField txtPaciente;
    private JTextField txtDoctor;
    private JTextField txtFechaCita;
    private JTextField txtSintomas;
    private JTextField txtDiagnostico;
    private JButton btnCrearDiagnostico;
    private JTextArea txtTratamiento;
    private JTextArea txtObservaciones;
    private JCheckBox chckEsImportante;
    private Diagnostico diagnosticoActual;
    
    
    public static void main(String[] args) {
		try {
			RealizarConsulta dialog = new RealizarConsulta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public RealizarConsulta() {
        setTitle("Realizar Consulta");
        setBounds(100, 100, 650, 570);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.DARK_GRAY);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JPanel panelCita = new JPanel();
        panelCita.setBackground(Color.DARK_GRAY);
        panelCita.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n de la Cita", TitledBorder.CENTER, TitledBorder.TOP, null, Color.WHITE));
        panelCita.setBounds(10, 11, 614, 180);
        contentPanel.add(panelCita);
        panelCita.setLayout(null);
        
        JLabel lblCita = new JLabel("Cita:");
        lblCita.setForeground(Color.WHITE);
        lblCita.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblCita.setBounds(10, 30, 80, 14);
        panelCita.add(lblCita);
        
        cbxCita = new JComboBox();
        cbxCita.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarDatosCita();
            }
        });
        cbxCita.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>"}));
        cbxCita.setBounds(100, 27, 494, 20);
        panelCita.add(cbxCita);
        
        JLabel lblPaciente = new JLabel("Paciente:");
        lblPaciente.setForeground(Color.WHITE);
        lblPaciente.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPaciente.setBounds(10, 65, 80, 14);
        panelCita.add(lblPaciente);
        
        txtPaciente = new JTextField();
        txtPaciente.setEditable(false);
        txtPaciente.setBounds(100, 62, 494, 20);
        panelCita.add(txtPaciente);
        txtPaciente.setColumns(10);
        
        JLabel lblDoctor = new JLabel("Doctor:");
        lblDoctor.setForeground(Color.WHITE);
        lblDoctor.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDoctor.setBounds(10, 95, 80, 14);
        panelCita.add(lblDoctor);
        
        txtDoctor = new JTextField();
        txtDoctor.setEditable(false);
        txtDoctor.setBounds(100, 92, 494, 20);
        panelCita.add(txtDoctor);
        txtDoctor.setColumns(10);
        
        JLabel lblFechaCita = new JLabel("Fecha/Hora:");
        lblFechaCita.setForeground(Color.WHITE);
        lblFechaCita.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblFechaCita.setBounds(10, 125, 80, 14);
        panelCita.add(lblFechaCita);
        
        txtFechaCita = new JTextField();
        txtFechaCita.setEditable(false);
        txtFechaCita.setBounds(100, 122, 200, 20);
        panelCita.add(txtFechaCita);
        txtFechaCita.setColumns(10);
        
        JLabel lblSintomas = new JLabel("Síntomas:");
        lblSintomas.setForeground(Color.WHITE);
        lblSintomas.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblSintomas.setBounds(320, 125, 80, 14);
        panelCita.add(lblSintomas);
        
        txtSintomas = new JTextField();
        txtSintomas.setEditable(false);
        txtSintomas.setBounds(390, 122, 204, 20);
        panelCita.add(txtSintomas);
        txtSintomas.setColumns(10);
        
       
        JPanel panelConsulta = new JPanel();
        panelConsulta.setBackground(Color.DARK_GRAY);
        panelConsulta.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos de la Consulta", TitledBorder.CENTER, TitledBorder.TOP, null, Color.WHITE));
        panelConsulta.setBounds(10, 202, 614, 280);
        contentPanel.add(panelConsulta);
        panelConsulta.setLayout(null);
        
        JLabel lblDiagnostico = new JLabel("Diagnóstico:");
        lblDiagnostico.setForeground(Color.WHITE);
        lblDiagnostico.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblDiagnostico.setBounds(10, 25, 80, 14);
        panelConsulta.add(lblDiagnostico);
        
        txtDiagnostico = new JTextField();
        txtDiagnostico.setEditable(false);
        txtDiagnostico.setBounds(100, 22, 344, 20);
        panelConsulta.add(txtDiagnostico);
        txtDiagnostico.setColumns(10);
        
        btnCrearDiagnostico = new JButton("Crear Diagnóstico");
        btnCrearDiagnostico.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnCrearDiagnostico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirCrearDiagnostico();
            }
        });
        btnCrearDiagnostico.setBounds(454, 21, 140, 23);
        panelConsulta.add(btnCrearDiagnostico);
        
        JLabel lblTratamiento = new JLabel("Tratamiento:");
        lblTratamiento.setForeground(Color.WHITE);
        lblTratamiento.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblTratamiento.setBounds(10, 65, 80, 14);
        panelConsulta.add(lblTratamiento);
        
        JScrollPane scrollTratamiento = new JScrollPane();
        scrollTratamiento.setBounds(100, 65, 494, 60);
        panelConsulta.add(scrollTratamiento);
        
        txtTratamiento = new JTextArea();
        txtTratamiento.setSize(492, 23);
        txtTratamiento.setLocation(0, -14);
        txtTratamiento.setLineWrap(true);
        txtTratamiento.setWrapStyleWord(true);
        scrollTratamiento.setViewportView(txtTratamiento);
        
        JLabel lblObservaciones = new JLabel("Observaciones:");
        lblObservaciones.setForeground(Color.WHITE);
        lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblObservaciones.setBounds(10, 140, 90, 14);
        panelConsulta.add(lblObservaciones);
        
        JScrollPane scrollObservaciones = new JScrollPane();
        scrollObservaciones.setBounds(100, 140, 494, 60);
        panelConsulta.add(scrollObservaciones);
        
        txtObservaciones = new JTextArea();
        txtObservaciones.setSize(492, 23);
        txtObservaciones.setLocation(0, -14);
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        scrollObservaciones.setViewportView(txtObservaciones);
        
        chckEsImportante = new JCheckBox("Marcar como Importante (Agregar al Resumen del Paciente)");
        chckEsImportante.setBackground(Color.DARK_GRAY);
        chckEsImportante.setForeground(Color.WHITE);
        chckEsImportante.setFont(new Font("Tahoma", Font.BOLD, 13));
<<<<<<< HEAD
        chckEsImportante.setBounds(100, 210, 425, 23);
=======
        chckEsImportante.setBounds(100, 210, 494, 23);
>>>>>>> branch 'master' of https://github.com/AEGR0005/Proyecto_Final.git
        panelConsulta.add(chckEsImportante);
        
        
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.DARK_GRAY);
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnRealizar = new JButton("Realizar Consulta");
                btnRealizar.setFont(new Font("Tahoma", Font.BOLD, 13));
                btnRealizar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        realizarConsulta();
                    }
                });
                btnRealizar.setActionCommand("OK");
                buttonPane.add(btnRealizar);
                getRootPane().setDefaultButton(btnRealizar);
            }
            {
                JButton btnCancelar = new JButton("Cancelar");
                btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
                btnCancelar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                btnCancelar.setActionCommand("Cancel");
                buttonPane.add(btnCancelar);
            }
        }
        
        cargarCitas();
    }
    
    private void cargarCitas() {
        cbxCita.removeAllItems();
        cbxCita.addItem("<<Seleccione>>");
        
        
        for(Cita cita : Clinica.getInstancia().getCitas()) {
            if(cita.getEstado() == EstadoCita.PROGRAMADA) {
                String item = cita.getId() + " - " + cita.getPaciente().getNombre() + " (" + cita.getFechaHora() + ")";
                cbxCita.addItem(item);
            }
        }
    }
    
    private void cargarDatosCita() {
        if(cbxCita.getSelectedIndex() > 0) {
            String codigo = cbxCita.getSelectedItem().toString().split(" ")[0];
            Cita cita = Clinica.getInstancia().buscarCitaXId(codigo);
            
            if(cita != null) {
                txtPaciente.setText(cita.getPaciente().getNombre() + " - " + cita.getPaciente().getCedula());
                txtDoctor.setText(cita.getDoctor().getNombre());
                txtFechaCita.setText(cita.getFechaHora().toString());
                txtSintomas.setText(cita.getSintomas());
            }
        } else {
            limpiarCampos();
        }
    }
    
    private void abrirCrearDiagnostico() {
        CrearDiagnostico dialogo = new CrearDiagnostico();
        dialogo.setVisible(true);
        
        
        Diagnostico diag = dialogo.getDiagnosticoCreado();
        if(diag != null) {
            diagnosticoActual = diag;
            txtDiagnostico.setText(diag.getCodigoDiagnostico() + " - " + diag.getDescripcion().substring(0, Math.min(30, diag.getDescripcion().length())) + "...");
        }
    }
    
    private void realizarConsulta() {
        if(cbxCita.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una cita.", "Campo Requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(diagnosticoActual == null) {
            JOptionPane.showMessageDialog(null, "Debe crear un diagnóstico.", "Campo Requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(txtTratamiento.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un tratamiento.", "Campo Requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String codigo = cbxCita.getSelectedItem().toString().split(" ")[0];
        Cita cita = Clinica.getInstancia().buscarCitaXId(codigo);
        
        if(cita != null) {
            
            Consulta consulta = Clinica.getInstancia().realizarConsulta(cita);
            
            if(consulta != null) {
                
                consulta.setSintomas(txtSintomas.getText());
                consulta.setDiagnostico(diagnosticoActual);
                consulta.setTratamiento(txtTratamiento.getText());
                consulta.setObservaciones(txtObservaciones.getText());
                consulta.setEsImportante(chckEsImportante.isSelected());
                
                
                if(chckEsImportante.isSelected()) {
                    cita.getPaciente().getResumen().add(consulta);
                }
                
                JOptionPane.showMessageDialog(null, 
                    "Consulta realizada con éxito.\nCódigo: " + consulta.getId(), 
                    "Consulta Exitosa", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Error al realizar la consulta. Verifique que la cita esté programada.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void limpiarCampos() {
        txtPaciente.setText("");
        txtDoctor.setText("");
        txtFechaCita.setText("");
        txtSintomas.setText("");
        txtDiagnostico.setText("");
        txtTratamiento.setText("");
        txtObservaciones.setText("");
        chckEsImportante.setSelected(false);
        diagnosticoActual = null;
    }
}