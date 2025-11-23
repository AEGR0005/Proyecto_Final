package visual;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Clinica;
import logico.Doctor;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class Main extends JFrame {

	private JPanel contentPane;
	private Doctor auxDoctor = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @return 
	 */
	public Main() {
		if(auxDoctor == null) {
			Clinica.getInstancia().crearDoctorPrueba();
			auxDoctor = Clinica.getInstancia().getDoctores().get(0);
			Clinica.getInstancia().crearPacientePrueba("Cristina Garcia Hernandez", "0315400566");
			Clinica.getInstancia().crearPacientePrueba("Altagracia Rodríguez Fernandez", "0315400566");
			Clinica.getInstancia().crearPacientePrueba("Maria Antonieta De las Nieves", "0315400566");
			auxDoctor.setPacientes(Clinica.getInstancia().getPacientes());
		}
		setTitle("Clinica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 547);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(new Color(25, 24, 59));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 226, 500);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.DARK_GRAY);
		menuBar.setBounds(0, 52, 226, 52);
		panel.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Registro");
		mnNewMenu.setBackground(Color.DARK_GRAY);
		menuBar.add(mnNewMenu);
		mnNewMenu.setForeground(Color.WHITE);
		mnNewMenu.setFont(new Font("Bahnschrift", Font.BOLD, 24));
		mnNewMenu.setBounds(0, 52, 226, 52);
		mnNewMenu.setPreferredSize(new java.awt.Dimension(226, 52));
		
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Paciente");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistrarPaciente regPaciente = new RegistrarPaciente();
				regPaciente.setModal(true);
				regPaciente.setVisible(true);
			}
		});
		mntmNewMenuItem.setPreferredSize(new java.awt.Dimension(226, 40)); 
		mntmNewMenuItem.setBackground(Color.DARK_GRAY);    
		mntmNewMenuItem.setForeground(Color.WHITE);
		mntmNewMenuItem.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Cita");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistrarCita regCita = new RegistrarCita();
				regCita.setModal(true);
				regCita.setVisible(true);
			}
		});
		mntmNewMenuItem_1.setPreferredSize(new java.awt.Dimension(226, 40)); 
		mntmNewMenuItem_1.setBackground(Color.DARK_GRAY);    
		mntmNewMenuItem_1.setForeground(Color.WHITE);
		mntmNewMenuItem_1.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmRegistrarEnfermedad = new JMenuItem("Enfermedad");
		mntmRegistrarEnfermedad.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        RegEnfermedad regEnfermedad = new RegEnfermedad();
		        regEnfermedad.setModal(true);
		        regEnfermedad.setVisible(true);
		    }
		});
		mntmRegistrarEnfermedad.setPreferredSize(new java.awt.Dimension(226, 40)); 
		mntmRegistrarEnfermedad.setBackground(Color.DARK_GRAY);    
		mntmRegistrarEnfermedad.setForeground(Color.WHITE);
		mntmRegistrarEnfermedad.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		mnNewMenu.add(mntmRegistrarEnfermedad);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Consulta");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RealizarConsulta realizarConsulta = new RealizarConsulta();
				realizarConsulta.setModal(true);
				realizarConsulta.setVisible(true);
			}
		});
		
		mntmNewMenuItem_2.setPreferredSize(new java.awt.Dimension(226, 40)); 
		mntmNewMenuItem_2.setBackground(Color.DARK_GRAY);    
		mntmNewMenuItem_2.setForeground(Color.WHITE);
		mntmNewMenuItem_2.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		mnNewMenu.add(mntmNewMenuItem_2);
		
		
		JMenuItem mntmRegistrarVacuna = new JMenuItem("Vacuna");
        mntmRegistrarVacuna.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ManejoVacuna manejoVacuna = new ManejoVacuna();
                manejoVacuna.setModal(true);
                manejoVacuna.setVisible(true);
            }
        });
        mntmRegistrarVacuna.setPreferredSize(new Dimension(226, 40));
        mntmRegistrarVacuna.setBackground(Color.DARK_GRAY);
        mntmRegistrarVacuna.setForeground(Color.WHITE);
        mntmRegistrarVacuna.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
        mnNewMenu.add(mntmRegistrarVacuna);
        
        
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBackground(Color.DARK_GRAY);
		menuBar_1.setBounds(0, 0, 226, 52);
		panel.add(menuBar_1);
		
		JMenu mnNewMenu_1 = new JMenu("Administraci\u00F3n");
		menuBar_1.add(mnNewMenu_1);
		mnNewMenu_1.setBackground(Color.DARK_GRAY);
		mnNewMenu_1.setForeground(Color.WHITE);
		mnNewMenu_1.setFont(new Font("Bahnschrift", Font.BOLD, 24));
		mnNewMenu_1.setPreferredSize(new java.awt.Dimension(226, 52));
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Algo");
		mntmNewMenuItem_3.setPreferredSize(new java.awt.Dimension(226, 40)); 
		mntmNewMenuItem_3.setBackground(Color.DARK_GRAY);    
		mntmNewMenuItem_3.setForeground(Color.WHITE);
		mntmNewMenuItem_3.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuBar menuBar_2 = new JMenuBar();
		menuBar_2.setBackground(Color.DARK_GRAY);
		menuBar_2.setBounds(0, 104, 226, 52);
		panel.add(menuBar_2);
		
		JMenu mnListado = new JMenu("Listado");
		mnListado.setPreferredSize(new Dimension(226, 52));
		mnListado.setForeground(Color.WHITE);
		mnListado.setFont(new Font("Bahnschrift", Font.BOLD, 24));
		mnListado.setBackground(Color.DARK_GRAY);
		menuBar_2.add(mnListado);
		
		JMenuItem mntmListarConsultas = new JMenuItem("Listar consultas");
		mntmListarConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MostrarConsulta mostrarconsulta = new MostrarConsulta(auxDoctor);
				mostrarconsulta.setModal(true);
				mostrarconsulta.setVisible(true);
			}
		});
		mntmListarConsultas.setPreferredSize(new Dimension(226, 40));
		mntmListarConsultas.setForeground(Color.WHITE);
		mntmListarConsultas.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		mntmListarConsultas.setBackground(Color.DARK_GRAY);
		mnListado.add(mntmListarConsultas);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(228, 0, 523, 500);
		/*
		ImageIcon icon = new ImageIcon("recursos/PRECIOUS.jpg");
		Image img = icon.getImage();
		Image imgScale = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(imgScale);
		lblNewLabel.setIcon(scaledIcon);
		*/
		contentPane.add(lblNewLabel);
		
	}
}
