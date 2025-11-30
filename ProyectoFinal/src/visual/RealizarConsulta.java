package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;

public class RealizarConsulta extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private JComboBox cbxCita;
    private JTextField txtPaciente;
    private JTextField txtDoctor;
    private JTextField txtFechaCita;
    private JTextArea txtSintomas;
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
        setBounds(100, 100, 773, 683);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(240, 248, 255));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        
        JPanel panelGeneral = new JPanel();
        panelGeneral.setBounds(36, 26, 682, 530);
        panelGeneral.setLayout(null);
        
        JScrollPane scrollDatos = new JScrollPane();
        scrollDatos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDatos.setViewportView(panelGeneral);
        contentPanel.add(scrollDatos);
        
        
        JPanel panelCita = new JPanel();
        panelCita.setBounds(25, 16, 680, 205);
        panelGeneral.add(panelCita);
        panelCita.setBackground(Color.WHITE);
        panelCita.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n de la Cita", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(70, 130, 180)));
        panelCita.setLayout(null);
        
        JLabel lblCita = new JLabel("Cita:");
        lblCita.setForeground(new Color(70, 130, 180));
        lblCita.setFont(new Font("Verdana", Font.BOLD, 14));
        lblCita.setBounds(26, 30, 80, 14);
        panelCita.add(lblCita);
        
        cbxCita = new JComboBox();
        cbxCita.setBackground(new Color(224, 247, 250));
        cbxCita.setBorder(new LineBorder(new Color(70, 130, 180)));
        cbxCita.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarDatosCita();
            }
        });
        cbxCita.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>"}));
        cbxCita.setBounds(152, 27, 494, 20);
        panelCita.add(cbxCita);
        
        JLabel lblPaciente = new JLabel("Paciente:");
        lblPaciente.setForeground(new Color(70, 130, 180));
        lblPaciente.setFont(new Font("Verdana", Font.BOLD, 13));
        lblPaciente.setBounds(26, 65, 80, 13);
        panelCita.add(lblPaciente);
        
        txtPaciente = new JTextField();
        txtPaciente.setEditable(false);
        txtPaciente.setBounds(152, 63, 494, 20);
        txtPaciente.setBackground(new Color(224, 247, 250));
        txtPaciente.setBorder(new LineBorder(new Color(70, 130, 180)));
        panelCita.add(txtPaciente);
        txtPaciente.setColumns(10);
        
        JLabel lblDoctor = new JLabel("Doctor:");
        lblDoctor.setForeground(new Color(70, 130, 180));
        lblDoctor.setFont(new Font("Verdana", Font.BOLD, 13));
        lblDoctor.setBounds(26, 95, 80, 14);
        panelCita.add(lblDoctor);
        
        txtDoctor = new JTextField();
        txtDoctor.setEditable(false);
        txtDoctor.setBounds(152, 92, 494, 20);
        txtDoctor.setBackground(new Color(224, 247, 250));
        txtDoctor.setBorder(new LineBorder(new Color(70, 130, 180)));
        panelCita.add(txtDoctor);
        txtDoctor.setColumns(10);
        
        JLabel lblFechaCita = new JLabel("Fecha:");
        lblFechaCita.setForeground(new Color(70, 130, 180));
        lblFechaCita.setFont(new Font("Verdana", Font.BOLD, 13));
        lblFechaCita.setBounds(26, 120, 80, 14);
        panelCita.add(lblFechaCita);
        
        txtFechaCita = new JTextField();
        txtFechaCita.setEditable(false);
        txtFechaCita.setBounds(152, 117, 494, 20);
        txtFechaCita.setBackground(new Color(224, 247, 250));
        txtFechaCita.setBorder(new LineBorder(new Color(70, 130, 180)));
        panelCita.add(txtFechaCita);
        txtFechaCita.setColumns(10);
        
       
        JPanel panelConsulta = new JPanel();
        panelConsulta.setBounds(25, 237, 680, 402);
        panelGeneral.add(panelConsulta);
        panelConsulta.setBackground(new Color(255, 255, 255));
        panelConsulta.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos de la Consulta", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(70, 130, 180)));
        panelConsulta.setLayout(null);
        
        int yPos = panelConsulta.getY()+panelConsulta.getHeight();
        panelGeneral.setPreferredSize(new Dimension(panelConsulta.getWidth(),yPos+20));
        
        JLabel lblSintomas = new JLabel("Síntomas:");
        lblSintomas.setForeground(new Color(70, 130, 180));
        lblSintomas.setFont(new Font("Verdana", Font.BOLD, 12));
        lblSintomas.setBounds(26, 41, 80, 14);
        panelConsulta.add(lblSintomas);
        
        JScrollPane scrollSintomas = new JScrollPane();
        scrollSintomas.setBounds(152, 41, 494, 70);
        panelConsulta.add(scrollSintomas);
        
        txtSintomas = new JTextArea();
        txtSintomas.setLocation(127, 0);
        txtSintomas.setBackground(new Color(240, 248, 255));
        txtSintomas.setBorder(new LineBorder(new Color(70, 130, 180)));
        txtSintomas.setLineWrap(true);
        txtSintomas.setWrapStyleWord(true);
        scrollSintomas.setViewportView(txtSintomas);
        
        JLabel lblDiagnostico = new JLabel("Diagnóstico:");
        lblDiagnostico.setForeground(new Color(70, 130, 180));
        lblDiagnostico.setFont(new Font("Verdana", Font.BOLD, 12));
        lblDiagnostico.setBounds(26, 132, 100, 14);
        panelConsulta.add(lblDiagnostico);
        
        txtDiagnostico = new JTextField();
        txtDiagnostico.setEditable(false);
        txtDiagnostico.setBounds(152, 129, 344, 20);
        txtDiagnostico.setBackground(new Color(224, 247, 250));
        txtDiagnostico.setBorder(new LineBorder(new Color(70, 130, 180)));
        panelConsulta.add(txtDiagnostico);
        txtDiagnostico.setColumns(10);
        
        btnCrearDiagnostico = new JButton("Crear Diagnóstico");
        btnCrearDiagnostico.setForeground(new Color(70, 130, 180));
        btnCrearDiagnostico.setBackground(new Color(255, 245, 238));
        btnCrearDiagnostico.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnCrearDiagnostico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirCrearDiagnostico();
            }
        });
        btnCrearDiagnostico.setBounds(506, 128, 140, 23);
        panelConsulta.add(btnCrearDiagnostico);
        
        JLabel lblTratamiento = new JLabel("Tratamiento:");
        lblTratamiento.setForeground(new Color(70, 130, 180));
        lblTratamiento.setFont(new Font("Verdana", Font.BOLD, 12));
        lblTratamiento.setBounds(26, 161, 100, 14);
        panelConsulta.add(lblTratamiento);
        
        JScrollPane scrollTratamiento = new JScrollPane();
        scrollTratamiento.setBounds(152, 161, 494, 70);
        panelConsulta.add(scrollTratamiento);
        
        txtTratamiento = new JTextArea();
        txtTratamiento.setBackground(new Color(240, 248, 255));
        txtTratamiento.setBorder(new LineBorder(new Color(70, 130, 180)));
        txtTratamiento.setLineWrap(true);
        txtTratamiento.setWrapStyleWord(true);
        scrollTratamiento.setViewportView(txtTratamiento);
        
        JLabel lblObservaciones = new JLabel("Observaciones:");
        lblObservaciones.setForeground(new Color(70, 130, 180));
        lblObservaciones.setFont(new Font("Verdana", Font.BOLD, 12));
        lblObservaciones.setBounds(26, 248, 111, 14);
        panelConsulta.add(lblObservaciones);
        
        JScrollPane scrollObservaciones = new JScrollPane();
        scrollObservaciones.setBounds(152, 248, 494, 70);
        panelConsulta.add(scrollObservaciones);
        
        txtObservaciones = new JTextArea();
        txtObservaciones.setBackground(new Color(240, 248, 255));
        txtObservaciones.setBorder(new LineBorder(new Color(70, 130, 180)));
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        scrollObservaciones.setViewportView(txtObservaciones);
        
        chckEsImportante = new JCheckBox("Marcar como Importante (Agregar al Resumen del Paciente)");
        chckEsImportante.setBackground(Color.WHITE);
        chckEsImportante.setForeground(new Color(70, 130, 180));
        chckEsImportante.setFont(new Font("Tahoma", Font.BOLD, 13));
        chckEsImportante.setBounds(152, 350, 494, 23);
        panelConsulta.add(chckEsImportante);
        panelCita.revalidate();
        panelCita.repaint();

        
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.WHITE);
            buttonPane.setBorder(new LineBorder(new Color(70, 130, 180)));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnRealizar = new JButton("Realizar Consulta");
                btnRealizar.setFont(new Font("Verdana", Font.BOLD, 14));
                btnRealizar.setForeground(new Color(70, 130, 180));
                btnRealizar.setBackground(new Color(255, 245, 238));
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
                btnCancelar.setFont(new Font("Verdana", Font.BOLD, 14));
                btnCancelar.setForeground(new Color(70, 130, 180));
                btnCancelar.setBackground(new Color(255, 245, 238));
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
            	txtDoctor.setText(cita.getDoctor().getNombre());
                txtFechaCita.setText(cita.getFechaHora().toString());
            }
        } else {
            limpiarCampos();
        }
    }
    
    /*
    private void cargarPacientes() {
		cbxPaciente.removeAllItems();
		cbxPaciente.addItem("<Seleccione un paciente>");
		
		for(Paciente p : Clinica.getInstancia().getPacientes()) {
			cbxPaciente.addItem(p.getNombre() + " - " + p.getCedula());
		}
	}
	*/
    
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
        
        if(txtSintomas.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar los síntomas.", "Campo Requerido", JOptionPane.WARNING_MESSAGE);
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