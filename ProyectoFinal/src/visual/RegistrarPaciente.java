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
import javax.swing.SpinnerDateModel;

import java.awt.Color;
import javax.swing.border.TitledBorder;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.swing.UIManager;
import logico.Clinica;
import logico.Paciente;
import java.util.Date;
import java.text.ParseException;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.util.Calendar;

public class RegistrarPaciente extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private JTextField txtNombre;
    private JTextField txtCedula;
    private JTextField txtTelefono;
    private JSpinner spinnerDia;
    private JComboBox<String> cbxMes;
    private JSpinner spinnerAnio;
    private JTextField txtPeso;
    private JTextField txtEstatura;
    private JSpinner spnFecha;
    private JComboBox comboBox;
    private JComboBox cbxTipoSangre;
    private JSpinner spnFechaNacim;
    private SpinnerDateModel dateModel;
    
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
        setBounds(100, 100, 631, 450);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(72, 209, 204));
        //contentPanel.setBackground(Color.DARK_GRAY);
        contentPanel.setForeground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), 
            "Datos del Paciente", TitledBorder.CENTER, TitledBorder.TOP, null, Color.WHITE));
        panel.setForeground(Color.BLACK);
        panel.setBackground(Color.DARK_GRAY);
        panel.setBounds(12, 13, 580, 235);
        contentPanel.add(panel);
        panel.setLayout(null);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(12, 40, 80, 20);
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel.add(lblNombre);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(97, 39, 468, 20);
        panel.add(txtNombre);
        txtNombre.setColumns(10);
        
        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setBounds(382, 91, 60, 20);
        lblCedula.setForeground(Color.WHITE);
        lblCedula.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel.add(lblCedula);
        
        txtCedula = new JTextField();
        txtCedula.setBounds(447, 118, 118, 20);
        panel.add(txtCedula);
        txtCedula.setColumns(10);
        
        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(382, 118, 60, 20);
        lblTelefono.setForeground(Color.WHITE);
        lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel.add(lblTelefono);
        
        txtTelefono = new JTextField();
        txtTelefono.setBounds(447, 91, 118, 20);
        panel.add(txtTelefono);
        txtTelefono.setColumns(10);
        
        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setBounds(258, 104, 43, 20);
        lblSexo.setForeground(Color.WHITE);
        lblSexo.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel.add(lblSexo);
        
        comboBox = new JComboBox();
        comboBox.setBounds(302, 101, 43, 26);
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"F", "M"}));
        panel.add(comboBox);
        
        JLabel lblFechaNacimiento = new JLabel("<html>Fecha de<br>Nacimiento:<html>");
        lblFechaNacimiento.setBounds(12, 100, 92, 29);
        panel.add(lblFechaNacimiento);
        lblFechaNacimiento.setForeground(Color.WHITE);
        lblFechaNacimiento.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
        lblDireccion.setBounds(12, 169, 80, 20);
        lblDireccion.setForeground(Color.WHITE);
        lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 13));
        panel.add(lblDireccion);
        
        spnFechaNacim = new JSpinner();
        //spnFechaNacim.setModel(new SpinnerDateModel(new Date(974260800000L), new Date(-1576697400000L), new Date(1767153600000L), Calendar.DAY_OF_YEAR));
        dateModel = new SpinnerDateModel(new Date(974260800000L), new Date(-1576697400000L), new Date(1767153600000L), Calendar.DAY_OF_YEAR);
        spnFechaNacim.setModel(dateModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spnFechaNacim, "dd/MM/yyyy");
        spnFechaNacim.setEditor(editor);
        spnFechaNacim.setBounds(97, 101, 107, 26);
        panel.add(spnFechaNacim);

        
        JPanel panelCondición = new JPanel();
        panelCondición.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Condici\u00F3n", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
        panelCondición.setBounds(15, 264, 580, 76);
        panelCondición.setBackground(Color.DARK_GRAY);
        contentPanel.add(panelCondición);
        panelCondición.setLayout(null);
        
        JLabel lblPeso = new JLabel("Peso:");
        lblPeso.setBounds(15, 30, 49, 20);
        panelCondición.add(lblPeso);
        lblPeso.setForeground(Color.WHITE);
        lblPeso.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        txtPeso = new JTextField();
        txtPeso.setBounds(60, 27, 49, 26);
        panelCondición.add(txtPeso);
        txtPeso.setColumns(10);
        
        JLabel lblPesoMedida = new JLabel("lb");
        lblPesoMedida.setForeground(Color.WHITE);
        lblPesoMedida.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblPesoMedida.setBounds(115, 30, 33, 20);
        panelCondición.add(lblPesoMedida);
        
        JLabel lblEstatura = new JLabel("Estatura:");
        lblEstatura.setForeground(Color.WHITE);
        lblEstatura.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblEstatura.setBounds(200, 30, 66, 20);
        panelCondición.add(lblEstatura);
        
        txtEstatura = new JTextField();
        txtEstatura.setBounds(270, 27, 41, 26);
        panelCondición.add(txtEstatura);
        txtEstatura.setColumns(10);
        
        JLabel lblEstMedida = new JLabel("ft");
        lblEstMedida.setForeground(Color.WHITE);
        lblEstMedida.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblEstMedida.setBounds(315, 30, 33, 20);
        panelCondición.add(lblEstMedida);
        
        JLabel lblTipoDeSangre = new JLabel("Tipo de Sangre:");
        lblTipoDeSangre.setForeground(Color.WHITE);
        lblTipoDeSangre.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTipoDeSangre.setBounds(383, 30, 114, 20);
        panelCondición.add(lblTipoDeSangre);
        
        cbxTipoSangre = new JComboBox();
        cbxTipoSangre.setModel(new DefaultComboBoxModel(new String[] {"A+", "A\u2212", "B+", "B\u2212", "AB+", "AB\u2212", "O+", "O-"}));
        cbxTipoSangre.setBounds(494, 27, 71, 26);
        panelCondición.add(cbxTipoSangre);
        
        
        int anioActual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        
        //No se puede hacer por separado porque la cantidad de días de cada mes cambia.
        /*
        JPanel miniPanelFecha = new JPanel();
        miniPanelFecha.setBounds(176, 135, 238, 54);
        miniPanelFecha.setBackground(Color.DARK_GRAY);
        panel.add(miniPanelFecha);
        miniPanelFecha.setLayout(null);
        
        
        spinnerDia = new JSpinner();
        spinnerDia.setBounds(15, 16, 50, 20);
        miniPanelFecha.add(spinnerDia);
        spinnerDia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
        
        JLabel lblBarra1 = new JLabel("/");
        lblBarra1.setBounds(69, 16, 10, 20);
        miniPanelFecha.add(lblBarra1);
        lblBarra1.setForeground(Color.WHITE);
        lblBarra1.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        cbxMes = new JComboBox<>();
        cbxMes.setBounds(82, 16, 50, 20);
        miniPanelFecha.add(cbxMes);
        cbxMes.setModel(new DefaultComboBoxModel<>(new String[] {
            "01", "02", "03", "04", "05", "06", 
            "07", "08", "09", "10", "11", "12"
        }));
        
        JLabel lblBarra2 = new JLabel("/");
        lblBarra2.setBounds(138, 16, 10, 20);
        miniPanelFecha.add(lblBarra2);
        lblBarra2.setForeground(Color.WHITE);
        lblBarra2.setFont(new Font("Tahoma", Font.BOLD, 16));
        spinnerAnio = new JSpinner();
        spinnerAnio.setBounds(147, 16, 70, 20);
        miniPanelFecha.add(spinnerAnio);
        spinnerAnio.setModel(new SpinnerNumberModel(2000, 1900, anioActual, 1));
        */
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(new Color(112, 128, 144));
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
        
        if(txtPeso.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el peso del paciente.", 
                "Campo Requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(txtEstatura.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el estatura del paciente.", 
                "Campo Requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        /*
        int dia = (int) spinnerDia.getValue();
        int mes = cbxMes.getSelectedIndex() + 1;
        int año = (int) spinnerAnio.getValue();
        
        String fechaStr = String.format("%04d-%02d-%02d", año, mes, dia);
        Date fechaNacimiento = Date.valueOf(fechaStr);
        */  
        
        String idPaciente = "PAC-" + Clinica.genCodigoPacientes;

        
        Date fecha = (Date)spnFechaNacim.getValue();
        
        if(fecha != null)
        	System.out.println(fecha);
        
        Paciente paciente = new Paciente(
            idPaciente,
            txtNombre.getText().trim(),
            txtCedula.getText().trim(),
            txtTelefono.getText().trim(),
            (Date)spnFechaNacim.getValue(),
            new Float(txtPeso.getText().trim()),
            new Float(txtEstatura.getText().trim()),
            cbxTipoSangre.getSelectedItem().toString()
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
        txtPeso.setText("");
        txtEstatura.setText("");
        cbxTipoSangre.setSelectedIndex(0);
        /*
        spinnerDia.setValue(1);
        cbxMes.setSelectedIndex(0);
        spinnerAnio.setValue(2000);
        */
    }
}