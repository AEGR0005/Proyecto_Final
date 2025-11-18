package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import logico.Clinica;
import logico.Paciente;
import java.sql.Date;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegistrarPaciente extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private JTextField txtNombre;
    private JTextField txtCedula;
    private JTextField txtTelefono;
    private JSpinner spinnerDia;
    private JComboBox<String> cbxMes;
    private JSpinner spinnerAnio;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            RegistrarPaciente dialog = new RegistrarPaciente();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Create the dialog.
     */
    public RegistrarPaciente() {
        setTitle("Registrar Paciente");
        setBounds(100, 100, 650, 400);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.DARK_GRAY);
        contentPanel.setForeground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), 
            "Datos del Paciente", TitledBorder.CENTER, TitledBorder.TOP, null, Color.WHITE));
        panel.setForeground(Color.BLACK);
        panel.setBackground(Color.DARK_GRAY);
        panel.setBounds(12, 13, 608, 280);
        contentPanel.add(panel);
        panel.setLayout(null);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNombre.setBounds(12, 40, 80, 20);
        panel.add(lblNombre);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(120, 40, 468, 20);
        panel.add(txtNombre);
        txtNombre.setColumns(10);
        
        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setForeground(Color.WHITE);
        lblCedula.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblCedula.setBounds(12, 85, 80, 20);
        panel.add(lblCedula);
        
        txtCedula = new JTextField();
        txtCedula.setBounds(120, 85, 468, 20);
        panel.add(txtCedula);
        txtCedula.setColumns(10);
        
        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setForeground(Color.WHITE);
        lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTelefono.setBounds(12, 130, 80, 20);
        panel.add(lblTelefono);
        
        txtTelefono = new JTextField();
        txtTelefono.setBounds(120, 130, 468, 20);
        panel.add(txtTelefono);
        txtTelefono.setColumns(10);
        
        JLabel lblFechaNacimiento = new JLabel("Fecha Nacim.:");
        lblFechaNacimiento.setForeground(Color.WHITE);
        lblFechaNacimiento.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblFechaNacimiento.setBounds(12, 175, 110, 20);
        panel.add(lblFechaNacimiento);
        
        spinnerDia = new JSpinner();
        spinnerDia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
        spinnerDia.setBounds(120, 175, 50, 20);
        panel.add(spinnerDia);
        
        JLabel lblBarra1 = new JLabel("/");
        lblBarra1.setForeground(Color.WHITE);
        lblBarra1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblBarra1.setBounds(175, 175, 10, 20);
        panel.add(lblBarra1);
        
        cbxMes = new JComboBox<>();
        cbxMes.setModel(new DefaultComboBoxModel<>(new String[] {
            "01", "02", "03", "04", "05", "06", 
            "07", "08", "09", "10", "11", "12"
        }));
        cbxMes.setBounds(190, 175, 50, 20);
        panel.add(cbxMes);
        
        JLabel lblBarra2 = new JLabel("/");
        lblBarra2.setForeground(Color.WHITE);
        lblBarra2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblBarra2.setBounds(245, 175, 10, 20);
        panel.add(lblBarra2);
        
        int anioActual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        spinnerAnio = new JSpinner();
        spinnerAnio.setModel(new SpinnerNumberModel(2000, 1900, anioActual, 1));
        spinnerAnio.setBounds(260, 175, 70, 20);
        panel.add(spinnerAnio);
        
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.DARK_GRAY);
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnRegistrar = new JButton("Registrar");
                btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 13));
                btnRegistrar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        registrarPaciente();
                    }
                });
                btnRegistrar.setActionCommand("OK");
                buttonPane.add(btnRegistrar);
                getRootPane().setDefaultButton(btnRegistrar);
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
    }
    
    private void registrarPaciente() {
        if(txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del paciente.", 
                "Campo Requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(txtCedula.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar la cédula del paciente.", 
                "Campo Requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(txtTelefono.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el teléfono del paciente.", 
                "Campo Requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int dia = (int) spinnerDia.getValue();
        int mes = cbxMes.getSelectedIndex() + 1;
        int año = (int) spinnerAnio.getValue();
        
        
        
        String idPaciente = "PAC-" + Clinica.genCodigoPacientes;
        String fechaStr = String.format("%04d-%02d-%02d", año, mes, dia);
        Date fechaNacimiento = Date.valueOf(fechaStr);
        
        Paciente paciente = new Paciente(
            idPaciente,
            txtNombre.getText().trim(),
            txtCedula.getText().trim(),
            txtTelefono.getText().trim(),
            fechaNacimiento
        );
        
        Clinica.getInstancia().regPaciente(paciente);
        
        JOptionPane.showMessageDialog(null, 
            "Paciente registrado con éxito.\nCódigo: " + idPaciente, 
            "Registro Exitoso", 
            JOptionPane.INFORMATION_MESSAGE);
        
        limpiarCampos();
    }
    
    private void limpiarCampos() {
        txtNombre.setText("");
        txtCedula.setText("");
        txtTelefono.setText("");
        spinnerDia.setValue(1);
        cbxMes.setSelectedIndex(0);
        spinnerAnio.setValue(2000);
    }
    
}