package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import logico.Clinica;
import logico.Vacuna;

public class ListarVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static JButton btnEliminar;
	private static JButton btnModificar;
	private static DefaultTableModel model;
	private static Object[] row;
	private Vacuna auxVacuna = null;

	public static void main(String[] args) {
		try {
			ListarVacuna dialog = new ListarVacuna();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListarVacuna() {
		setTitle("Listado de Vacunas");
		setBounds(100, 100, 800, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 248, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
			new LineBorder(new Color(135, 206, 235), 2), 
			"Vacunas Registradas", 
			TitledBorder.CENTER, 
			TitledBorder.TOP, 
			new Font("Bahnschrift", Font.BOLD, 14), 
			new Color(70, 130, 180)
		));
		panel.setBackground(Color.WHITE);
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(173, 216, 230), 1));
		panel.add(scrollPane, BorderLayout.CENTER);
		
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		table.setBackground(Color.WHITE);
		table.setSelectionBackground(new Color(176, 224, 230));
		table.setSelectionForeground(new Color(70, 130, 180));
		table.setGridColor(new Color(173, 216, 230));
		table.getTableHeader().setFont(new Font("Bahnschrift", Font.BOLD, 13));
		table.getTableHeader().setBackground(new Color(135, 206, 235));
		table.getTableHeader().setForeground(new Color(70, 130, 180));
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				if(index > -1) {
					btnEliminar.setEnabled(true);
					btnModificar.setEnabled(true);
					String id = table.getValueAt(index, 0).toString();
					
					for(Vacuna v : Clinica.getInstancia().getVacunas()) {
						if(v.getId().equals(id)) {
							auxVacuna = v;
							break;
						}
					}
				}
			}
		});
		
		String[] headers = {"Código", "Nombre", "Enfermedad", "Edad Mínima", "Fabricante"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(240, 248, 255));
		buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		btnEliminar.setBackground(new Color(176, 224, 230));
		btnEliminar.setForeground(new Color(70, 130, 180));
		btnEliminar.setBorder(new LineBorder(new Color(135, 206, 235), 2));
		btnEliminar.setFocusPainted(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(auxVacuna != null) {
					int option = JOptionPane.showConfirmDialog(
						null, 
						"¿Está seguro que desea eliminar la vacuna: " + auxVacuna.getNombre() + "?",
						"Confirmación", 
						JOptionPane.WARNING_MESSAGE
					);
					if(option == JOptionPane.OK_OPTION) {
						Clinica.getInstancia().getVacunas().remove(auxVacuna);
						btnEliminar.setEnabled(false);
						btnModificar.setEnabled(false);
						loadVacunas();
						JOptionPane.showMessageDialog(null, 
							"Vacuna eliminada exitosamente.", 
							"Éxito", 
							JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnEliminar.setEnabled(false);
		buttonPane.add(btnEliminar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		btnModificar.setBackground(new Color(176, 224, 230));
		btnModificar.setForeground(new Color(70, 130, 180));
		btnModificar.setBorder(new LineBorder(new Color(135, 206, 235), 2));
		btnModificar.setFocusPainted(false);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(auxVacuna != null) {
					ManejoVacuna modVacuna = new ManejoVacuna(auxVacuna);
					modVacuna.setModal(true);
					modVacuna.setVisible(true);
				}
			}
		});
		btnModificar.setEnabled(false);
		btnModificar.setActionCommand("OK");
		buttonPane.add(btnModificar);
		getRootPane().setDefaultButton(btnModificar);
		
		JButton btnCancelar = new JButton("Cerrar");
		btnCancelar.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		btnCancelar.setBackground(new Color(176, 224, 230));
		btnCancelar.setForeground(new Color(70, 130, 180));
		btnCancelar.setBorder(new LineBorder(new Color(135, 206, 235), 2));
		btnCancelar.setFocusPainted(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setActionCommand("Cancel");
		buttonPane.add(btnCancelar);
		
		loadVacunas();
	}

	public static void loadVacunas() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Vacuna vacuna : Clinica.getInstancia().getVacunas()) {
			row[0] = vacuna.getId();
			row[1] = vacuna.getNombre();
			row[2] = vacuna.getEnfermedad() != null ? vacuna.getEnfermedad().getNombre() : "N/A";
			row[3] = vacuna.getEdadMinima();
			row[4] = vacuna.getFabricante() != null ? vacuna.getFabricante() : "N/A";
			model.addRow(row);
		}
		
		if(btnEliminar != null) btnEliminar.setEnabled(false);
		if(btnModificar != null) btnModificar.setEnabled(false);
	}
}