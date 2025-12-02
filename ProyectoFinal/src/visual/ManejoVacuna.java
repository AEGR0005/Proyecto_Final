package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import logico.Clinica;
import logico.Enfermedad;
import logico.Vacuna;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManejoVacuna extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtEdadMin;
	private JTextField txtFabricante;
	private JComboBox<Enfermedad> cbEnfermedad;

	public static void main(String[] args) {
		try {
			ManejoVacuna dialog = new ManejoVacuna();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ManejoVacuna() {
		setTitle("Manejo de Vacunas");
		setBounds(100, 100, 700, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 248, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panelRegistro = new JPanel();
		panelRegistro.setBackground(Color.WHITE);
		panelRegistro.setBorder(new TitledBorder(new LineBorder(new Color(135, 206, 235), 2), "Registro de Vacuna", TitledBorder.CENTER, TitledBorder.TOP, new Font("Bahnschrift", Font.BOLD, 14), new Color(70, 130, 180)));
		panelRegistro.setBounds(12, 13, 664, 225);
		contentPanel.add(panelRegistro);
		panelRegistro.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(70, 130, 180));
		lblNombre.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		lblNombre.setBounds(10, 30, 120, 20);
		panelRegistro.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		txtNombre.setBackground(new Color(224, 247, 250));
		txtNombre.setBorder(new LineBorder(new Color(173, 216, 230), 1));
		txtNombre.setBounds(140, 30, 200, 25);
		panelRegistro.add(txtNombre);
		
		JLabel lblEnfermedad = new JLabel("Enfermedad:");
		lblEnfermedad.setForeground(new Color(70, 130, 180));
		lblEnfermedad.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		lblEnfermedad.setBounds(10, 73, 120, 20);
		panelRegistro.add(lblEnfermedad);
		
		cbEnfermedad = new JComboBox<>();
		cbEnfermedad.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		cbEnfermedad.setBackground(new Color(224, 247, 250));
		cbEnfermedad.setBorder(new LineBorder(new Color(173, 216, 230), 1));
		cbEnfermedad.setBounds(140, 68, 200, 25);
		panelRegistro.add(cbEnfermedad);
		
		if (Clinica.getInstancia().getEnfermedades() != null) {
			for (Enfermedad e : Clinica.getInstancia().getEnfermedades()) {
				cbEnfermedad.addItem(e);
			}
		}
		cbEnfermedad.setSelectedIndex(-1);
		
		JLabel lblEdadMin = new JLabel("Edad mínima:");
		lblEdadMin.setForeground(new Color(70, 130, 180));
		lblEdadMin.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		lblEdadMin.setBounds(10, 117, 120, 20);
		panelRegistro.add(lblEdadMin);
		
		txtEdadMin = new JTextField();
		txtEdadMin.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		txtEdadMin.setBackground(new Color(224, 247, 250));
		txtEdadMin.setBorder(new LineBorder(new Color(173, 216, 230), 1));
		txtEdadMin.setBounds(140, 115, 200, 25);
		panelRegistro.add(txtEdadMin);
		
		JLabel lblFabricante = new JLabel("Fabricante:");
		lblFabricante.setForeground(new Color(70, 130, 180));
		lblFabricante.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		lblFabricante.setBounds(10, 164, 120, 20);
		panelRegistro.add(lblFabricante);
		
		txtFabricante = new JTextField();
		txtFabricante.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		txtFabricante.setBackground(new Color(224, 247, 250));
		txtFabricante.setBorder(new LineBorder(new Color(173, 216, 230), 1));
		txtFabricante.setBounds(140, 162, 200, 25);
		panelRegistro.add(txtFabricante);
		
		JButton btnRegistrar = new JButton("Registrar Vacuna");
		btnRegistrar.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		btnRegistrar.setBackground(new Color(176, 224, 230));
		btnRegistrar.setForeground(new Color(70, 130, 180));
		btnRegistrar.setBorder(new LineBorder(new Color(135, 206, 235), 2));
		btnRegistrar.setFocusPainted(false);
		btnRegistrar.setBounds(400, 65, 200, 40);
		panelRegistro.add(btnRegistrar);
		
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNombre.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe ingresar el nombre de la vacuna.", "Campo Requerido", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (txtEdadMin.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe ingresar la edad mínima.", "Campo Requerido", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int edadMin;
				try {
					edadMin = Integer.parseInt(txtEdadMin.getText().trim());
					if (edadMin < 0) {
						JOptionPane.showMessageDialog(null, "La edad mínima no puede ser negativa.", "Valor Inválido", JOptionPane.WARNING_MESSAGE);
						return;
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "La edad mínima debe ser un número válido.", "Valor Inválido", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (txtFabricante.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe ingresar el fabricante.", "Campo Requerido", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Enfermedad enfermedad = (Enfermedad) cbEnfermedad.getSelectedItem();
				String id = "VAC-" + Clinica.genCodigoVacuna;
				String nombre = txtNombre.getText().trim();
				String fabricante = txtFabricante.getText().trim();
				
				Vacuna vacuna = new Vacuna(id, nombre, enfermedad, edadMin);
				vacuna.setFabricante(fabricante);
				
				Clinica.getInstancia().getVacunas().add(vacuna);
				Clinica.genCodigoVacuna++;
				
				limpiarCampos();
				JOptionPane.showMessageDialog(null, "Vacuna registrada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(240, 248, 255));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		btnCerrar.setBackground(new Color(176, 224, 230));
		btnCerrar.setForeground(new Color(70, 130, 180));
		btnCerrar.setBorder(new LineBorder(new Color(135, 206, 235), 2));
		btnCerrar.setFocusPainted(false);
		btnCerrar.addActionListener(e -> dispose());
		buttonPane.add(btnCerrar);
	}

	private void limpiarCampos() {
		txtNombre.setText("");
		txtEdadMin.setText("");
		txtFabricante.setText("");
		cbEnfermedad.setSelectedIndex(-1);
	}
}