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

public class A extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox cbxSearchOpcs;
	private JTextField txtIdDoctor;
	private JComboBox cbxDoctores;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			A dialog = new A();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public A() {
		setBounds(100, 100, 654, 528);
		getContentPane().setLayout(new BorderLayout());
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		cbxSearchOpcs = new JComboBox();
		cbxSearchOpcs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(cbxSearchOpcs.getSelectedIndex() != -1) {
					cbxSearchOpcs.setVisible(false);
					
					if (cbxSearchOpcs.getSelectedIndex() == 1)
						txtIdDoctor.setVisible(true);
					else if (cbxSearchOpcs.getSelectedIndex() == 2)
						cbxDoctores.setVisible(true);
						
				}
					
				
				
			}
		});
		

		cbxSearchOpcs.setVisible(false);
		cbxSearchOpcs.setModel(new DefaultComboBoxModel(new String[] {"<B\u00FAsqueda>", "C\u00F3digo", "Lista"}));
		cbxSearchOpcs.setBounds(114, 117, 123, 26);
		contentPanel.add(cbxSearchOpcs);
		
		JLabel lblNewLabel = new JLabel("Doctor");
		lblNewLabel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(!cbxSearchOpcs.isVisible()) {
					cbxSearchOpcs.setSelectedIndex(0);
					cbxSearchOpcs.setVisible(true);
				}
					
			}
		});

		
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(cbxSearchOpcs.isVisible())
					cbxSearchOpcs.setVisible(false);
				
				if(!cbxSearchOpcs.isVisible()) 
					
					if(txtIdDoctor.isVisible() || cbxDoctores.isVisible()) {
						txtIdDoctor.setVisible(false);
						cbxDoctores.setVisible(false);
						cbxSearchOpcs.setSelectedIndex(0);
						cbxSearchOpcs.setVisible(true);
					}
					
				
				
			}
		});

		lblNewLabel.setBounds(114, 96, 69, 20);
		contentPanel.add(lblNewLabel);
		
		txtIdDoctor = new JTextField();
		txtIdDoctor.setVisible(false);
		txtIdDoctor.setBounds(114, 117, 123, 26);
		contentPanel.add(txtIdDoctor);
		txtIdDoctor.setColumns(10);
		
		cbxDoctores = new JComboBox();
		cbxDoctores.setVisible(false);
		cbxDoctores.setModel(new DefaultComboBoxModel(new String[] {"<Elegir>", "A", "B", "C"}));
		cbxDoctores.setBounds(114, 117, 123, 26);
		contentPanel.add(cbxDoctores);
		
		JLabel lblNewLabel_1 = new JLabel("Working...");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(114, 190, 108, 20);
		contentPanel.add(lblNewLabel_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
	}
}
