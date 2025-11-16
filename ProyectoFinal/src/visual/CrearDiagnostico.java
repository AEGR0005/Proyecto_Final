package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


import logico.Clinica;
import logico.Diagnostico;
import logico.Enfermedad;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;

public class CrearDiagnostico extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private JTextField txtCodigoDiagnostico;
    private JTextArea txtDescripcion;
    private JComboBox cbxEnfermedad;
    private Diagnostico diagnosticoCreado;
    
    public static void main(String[] args) {
		try {
			CrearDiagnostico dialog = new CrearDiagnostico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public CrearDiagnostico() {
        setTitle("Crear Diagnóstico");
        setModal(true);
        setBounds(100, 100, 550, 350);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n del Diagn\u00F3stico", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBounds(10, 11, 514, 250);
        contentPanel.add(panel);
        panel.setLayout(null);
        
        JLabel lblCodigo = new JLabel("Código Diagnóstico:");
        lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblCodigo.setBounds(10, 30, 130, 14);
        panel.add(lblCodigo);
        
        txtCodigoDiagnostico = new JTextField();
        txtCodigoDiagnostico.setEditable(false);
        txtCodigoDiagnostico.setBounds(150, 27, 344, 20);
        panel.add(txtCodigoDiagnostico);
        txtCodigoDiagnostico.setColumns(10);
        
        txtCodigoDiagnostico.setText("DIAG-"+Clinica.genCodigoDiagnosticos);
        
        JLabel lblEnfermedad = new JLabel("Enfermedad:");
        lblEnfermedad.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblEnfermedad.setBounds(10, 70, 130, 14);
        panel.add(lblEnfermedad);
        
        cbxEnfermedad = new JComboBox();
        cbxEnfermedad.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>"}));
        cbxEnfermedad.setBounds(150, 67, 344, 20);
        panel.add(cbxEnfermedad);
        
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDescripcion.setBounds(10, 110, 130, 14);
        panel.add(lblDescripcion);
        
        JScrollPane scrollDescripcion = new JScrollPane();
        scrollDescripcion.setBounds(150, 110, 344, 120);
        panel.add(scrollDescripcion);
        
        txtDescripcion = new JTextArea();
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        scrollDescripcion.setViewportView(txtDescripcion);
        
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("Crear");
                okButton.setFont(new Font("Tahoma", Font.BOLD, 13));
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        crearDiagnostico();
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancelar");
                cancelButton.setFont(new Font("Tahoma", Font.BOLD, 13));
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
        
        cargarEnfermedades();
    }
    
    
    
    
    private void cargarEnfermedades() {
        cbxEnfermedad.removeAllItems();
        cbxEnfermedad.addItem("<<Seleccione>>");
        
        cbxEnfermedad.addItem("Gripe");
        cbxEnfermedad.addItem("Diabetes");
        cbxEnfermedad.addItem("Hipertensión");
    }
    
    private void crearDiagnostico() {
        if(txtDescripcion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una descripción.", "Campo Requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
        String id = "DIAG-" + Clinica.genCodigoDiagnosticos;
        
      
        diagnosticoCreado = new Diagnostico(id, txtDescripcion.getText().trim(), new Date());
        diagnosticoCreado.setCodigoDiagnostico(txtCodigoDiagnostico.getText().trim());
        
        if(cbxEnfermedad.getSelectedIndex() > 0) {
            // POR AÑADIR TODAVIA
        }
        
        JOptionPane.showMessageDialog(null, 
            "Diagnóstico creado exitosamente.", 
            "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);
        
        dispose();
    }
    
    public Diagnostico getDiagnosticoCreado() {
        return diagnosticoCreado;
    }
}