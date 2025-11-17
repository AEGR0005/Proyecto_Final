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
import javax.swing.event.PopupMenuEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MostrarConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox cbxSearchOpcs;
	private JTextField txtIdDoctor;
	private JComboBox cbxDoctores;
	private boolean showDoctores = false;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarConsulta dialog = new MostrarConsulta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarConsulta() {
		setBounds(100, 100, 816, 577);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);

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
		panelBarra.setBounds(15, 16, 766, 116);
		getContentPane().add(panelBarra);
		panelBarra.setLayout(null);

		JLabel lblNewLabel = new JLabel("Doctor:");
		lblNewLabel.setBounds(15, 50, 69, 20);
		panelBarra.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		cbxSearchOpcs = new JComboBox();


		cbxSearchOpcs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxSearchOpcs.setBounds(84, 50, 123, 26);
		panelBarra.add(cbxSearchOpcs);

		DefaultComboBoxModel mainModel = new DefaultComboBoxModel(new String[] {"<B\u00FAsqueda>", "C\u00F3digo", "Lista"});
		DefaultComboBoxModel codigoModel = new DefaultComboBoxModel(new String[] {"<B\u00FAsqueda>", "", "Lista"});
		DefaultComboBoxModel doctoresModel = new DefaultComboBoxModel(new String[] {"<Elegir>", "A", "B"});
		cbxSearchOpcs.setModel(mainModel);

		cbxSearchOpcs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(cbxSearchOpcs.getSelectedIndex() != -1) {


					if(cbxSearchOpcs.getModel() == mainModel) {


						if (cbxSearchOpcs.getSelectedIndex() == 1) {

							cbxSearchOpcs.setModel(codigoModel);
							cbxSearchOpcs.setSelectedIndex(1);
							cbxSearchOpcs.setEditable(true);

							cbxSearchOpcs.addPopupMenuListener(new PopupMenuListener() {
								public void popupMenuCanceled(PopupMenuEvent e) {
								}
								public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
								}
								public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
									if(cbxSearchOpcs.getModel() == codigoModel) {
										cbxSearchOpcs.setModel(mainModel);
										cbxSearchOpcs.setSelectedIndex(0);
									}
									
								}
							});


						}else if (cbxSearchOpcs.getSelectedIndex() == 2) {

							if(txtIdDoctor.isVisible())
								txtIdDoctor.setVisible(false);
							
							cbxSearchOpcs.setModel(doctoresModel);	
							cbxSearchOpcs.setSelectedIndex(0);
						}

					}else if(cbxSearchOpcs.getModel() == doctoresModel) {
						if(cbxSearchOpcs.getSelectedIndex() > 0) {
							cbxSearchOpcs.setModel(mainModel);
							cbxSearchOpcs.setSelectedIndex(0);
						}
					}

				}


			}
		});



		txtIdDoctor = new JTextField();
		txtIdDoctor.setBounds(84, 72, 123, 26);
		panelBarra.add(txtIdDoctor);
		txtIdDoctor.setVisible(false);
		txtIdDoctor.setColumns(10);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaInicio.setBounds(247, 50, 96, 20);
		panelBarra.add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin:");
		lblFechaFin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaFin.setBounds(517, 50, 96, 20);
		panelBarra.add(lblFechaFin);
		
		JSpinner spnFecIni = new JSpinner();
		spnFecIni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spnFecIni.setModel(new SpinnerDateModel(new Date(1763265600000L), new Date(1763265600000L), null, Calendar.DAY_OF_YEAR));
		spnFecIni.setBounds(339, 50, 150, 26);
		panelBarra.add(spnFecIni);
		
		JSpinner spnFecFin = new JSpinner();
		spnFecFin.setModel(new SpinnerDateModel(new Date(1763265600000L), new Date(1763265600000L), null, Calendar.DAY_OF_YEAR));
		spnFecFin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spnFecFin.setBounds(589, 50, 150, 26);
		panelBarra.add(spnFecFin);
		
		JLabel lblPaciente = new JLabel("Paciente:");
		lblPaciente.setBounds(15, 14, 78, 20);
		panelBarra.add(lblPaciente);
		lblPaciente.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblNomPac = new JLabel("Liz Marie Torres");
		lblNomPac.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNomPac.setBounds(101, 14, 388, 20);
		panelBarra.add(lblNomPac);
		
		JPanel panelTable = new JPanel();
		panelTable.setBounds(15, 135, 766, 344);
		getContentPane().add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);
		
		
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		table = new JTable();

	}
}
