package visual;
import java.awt.EventQueue;

import javax.print.attribute.standard.Media;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Clinica;
import logico.Doctor;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class Main extends JFrame {
	private JPanel contentPane;
	private Doctor auxDoctor = null;
	private JLabel lblImagen;
	private JPanel panelLateral;
	private Dimension dim;
	
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
	 */
	public Main() {
		/*
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream writeFile;
				ObjectOutputStream writeObjeto;
				
				try {
					writeFile = new FileOutputStream("clinica.dat");
					writeObjeto = new ObjectOutputStream(writeFile);
					
					Clinica.getInstancia().guardarContadores();
					writeObjeto.writeObject(Clinica.getInstancia());
					
					writeFile.close();
					writeObjeto.close();
					
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
					
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				
			}
		});
		*/
		
		setTitle("Sistema de Gestión Clínica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dim = getToolkit().getScreenSize();
		setSize(dim.width - 40, dim.height - 60);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(new Color(25, 24, 59));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		panelLateral = new JPanel();
		panelLateral.setBackground(new Color(30, 30, 100));
		panelLateral.setPreferredSize(new Dimension(280, dim.height));
		panelLateral.setBorder(new LineBorder(new Color(100, 100, 150), 2));
		contentPane.add(panelLateral, BorderLayout.WEST);
		panelLateral.setLayout(null);

		JLabel lblTitulo = new JLabel("CLÍNICA");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Bahnschrift", Font.BOLD, 28));
		lblTitulo.setForeground(new Color(100, 200, 255));
		lblTitulo.setBounds(0, 20, 280, 40);
		panelLateral.add(lblTitulo);
		
		JLabel lblSubtitulo = new JLabel("Sistema de Gestión");
		lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblSubtitulo.setForeground(new Color(180, 180, 200));
		lblSubtitulo.setBounds(0, 60, 280, 20);
		panelLateral.add(lblSubtitulo);

		JMenuBar menuBarAdmin = new JMenuBar();
		menuBarAdmin.setBackground(new Color(40, 40, 80));
		menuBarAdmin.setBorder(new LineBorder(new Color(100, 100, 150), 1));
		menuBarAdmin.setBounds(20, 110, 240, 52);
		panelLateral.add(menuBarAdmin);
		
		JMenu mnAdmin = new JMenu("  Administración");
		mnAdmin.setIcon(cargarIcono("recursos/admin.png", 40, 40));
		mnAdmin.setBackground(new Color(40, 40, 80));
		mnAdmin.setForeground(Color.WHITE);
		mnAdmin.setFont(new Font("Bahnschrift", Font.BOLD, 18));
		mnAdmin.setPreferredSize(new Dimension(240, 52));
		mnAdmin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mnAdmin.setHorizontalTextPosition(SwingConstants.RIGHT);
		mnAdmin.setIconTextGap(10);
		menuBarAdmin.add(mnAdmin);
		
		JMenuItem mntmSignOut = new JMenuItem("  Cerrar sesión");
		mntmSignOut.setIcon(cargarIcono("recursos/signout.png", 24, 24));
		mntmSignOut.setPreferredSize(new Dimension(240, 40));
		mntmSignOut.setBackground(new Color(40, 40, 80));
		mntmSignOut.setForeground(Color.WHITE);
		mntmSignOut.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		mnAdmin.add(mntmSignOut);
		
		JMenuBar menuBarReg = new JMenuBar();
		menuBarReg.setBackground(new Color(40, 40, 80));
		menuBarReg.setBorder(new LineBorder(new Color(100, 100, 150), 1));
		menuBarReg.setBounds(20, 175, 240, 52);
		panelLateral.add(menuBarReg);
		
		JMenu mnRegistro = new JMenu("  Registro");
		mnRegistro.setIcon(cargarIcono("recursos/registro.png", 40, 40));
		mnRegistro.setBackground(new Color(40, 40, 80));
		mnRegistro.setForeground(Color.WHITE);
		mnRegistro.setFont(new Font("Bahnschrift", Font.BOLD, 18));
		mnRegistro.setPreferredSize(new Dimension(240, 52));
		mnRegistro.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mnRegistro.setHorizontalTextPosition(SwingConstants.RIGHT);
		mnRegistro.setIconTextGap(10);
		menuBarReg.add(mnRegistro);
		
		JMenuItem mntmPaciente = new JMenuItem("  Paciente");
		mntmPaciente.setIcon(cargarIcono("recursos/paciente.png", 24, 24));
		mntmPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistrarPaciente regPaciente = new RegistrarPaciente();
				regPaciente.setModal(true);
				regPaciente.setVisible(true);
			}
		});
		mntmPaciente.setPreferredSize(new Dimension(240, 40));
		mntmPaciente.setBackground(new Color(40, 40, 80));
		mntmPaciente.setForeground(Color.WHITE);
		mntmPaciente.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		mnRegistro.add(mntmPaciente);
		
		JMenuItem mntmCita = new JMenuItem("  Cita");
		mntmCita.setIcon(cargarIcono("recursos/cita.png", 24, 24));
		mntmCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistrarCita regCita = new RegistrarCita();
				regCita.setModal(true);
				regCita.setVisible(true);
			}
		});
		mntmCita.setPreferredSize(new Dimension(240, 40));
		mntmCita.setBackground(new Color(40, 40, 80));
		mntmCita.setForeground(Color.WHITE);
		mntmCita.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		mnRegistro.add(mntmCita);
		
		JMenuItem mntmEnfermedad = new JMenuItem("  Enfermedad");
		mntmEnfermedad.setIcon(cargarIcono("recursos/enfermedad.png", 24, 24));
		mntmEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegEnfermedad regEnfermedad = new RegEnfermedad();
				regEnfermedad.setModal(true);
				regEnfermedad.setVisible(true);
			}
		});
		mntmEnfermedad.setPreferredSize(new Dimension(240, 40));
		mntmEnfermedad.setBackground(new Color(40, 40, 80));
		mntmEnfermedad.setForeground(Color.WHITE);
		mntmEnfermedad.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		mnRegistro.add(mntmEnfermedad);
		
		JMenuItem mntmConsulta = new JMenuItem("  Consulta");
		mntmConsulta.setIcon(cargarIcono("recursos/consulta.png", 24, 24));
		mntmConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RealizarConsulta realizarConsulta = new RealizarConsulta();
				realizarConsulta.setModal(true);
				realizarConsulta.setVisible(true);
			}
		});
		mntmConsulta.setPreferredSize(new Dimension(240, 40));
		mntmConsulta.setBackground(new Color(40, 40, 80));
		mntmConsulta.setForeground(Color.WHITE);
		mntmConsulta.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		mnRegistro.add(mntmConsulta);
		
		JMenuItem mntmVacuna = new JMenuItem("  Vacuna");
		mntmVacuna.setIcon(cargarIcono("recursos/vacuna.png", 24, 24));
		mntmVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManejoVacuna manejoVacuna = new ManejoVacuna();
				manejoVacuna.setModal(true);
				manejoVacuna.setVisible(true);
			}
		});
		mntmVacuna.setPreferredSize(new Dimension(240, 40));
		mntmVacuna.setBackground(new Color(40, 40, 80));
		mntmVacuna.setForeground(Color.WHITE);
		mntmVacuna.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		mnRegistro.add(mntmVacuna);
		
		JMenuBar menuBarList = new JMenuBar();
		menuBarList.setBackground(new Color(40, 40, 80));
		menuBarList.setBorder(new LineBorder(new Color(100, 100, 150), 1));
		menuBarList.setBounds(20, 240, 240, 52);
		panelLateral.add(menuBarList);
		
		JMenu mnListado = new JMenu("  Listado");
		mnListado.setIcon(cargarIcono("recursos/listado.png", 40, 40));
		mnListado.setPreferredSize(new Dimension(240, 52));
		mnListado.setForeground(Color.WHITE);
		mnListado.setFont(new Font("Bahnschrift", Font.BOLD, 18));
		mnListado.setBackground(new Color(40, 40, 80));
		mnListado.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mnListado.setHorizontalTextPosition(SwingConstants.RIGHT);
		mnListado.setIconTextGap(10);
		menuBarList.add(mnListado);
		
		JMenuItem mntmListarConsultas = new JMenuItem("  Listar Consultas");
		mntmListarConsultas.setIcon(cargarIcono("recursos/lista_consultas.png", 24, 24));
		mntmListarConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MostrarConsulta mostrarconsulta = new MostrarConsulta(auxDoctor);
				mostrarconsulta.setModal(true);
				mostrarconsulta.setVisible(true);
			}
		});
		mntmListarConsultas.setPreferredSize(new Dimension(240, 40));
		mntmListarConsultas.setForeground(Color.WHITE);
		mntmListarConsultas.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		mntmListarConsultas.setBackground(new Color(40, 40, 80));
		mnListado.add(mntmListarConsultas);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(25, 24, 59));
		panel_1.setLayout(new BorderLayout());
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		lblImagen = new JLabel("");
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblImagen, BorderLayout.CENTER);

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				//cargarImagenCentral();
			}
		});

		//cargarImagenCentral();
	}
	
	private ImageIcon cargarIcono(String ruta, int ancho, int alto) {
		try {
			ImageIcon icon = new ImageIcon(ruta);
			Image img = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
			return new ImageIcon(img);
		} catch (Exception e) {
			System.out.println("No se pudo cargar el icono: " + ruta);
			return null;
		}
	}
	
	private void cargarImagenCentral() {
		try {
			ImageIcon icon = new ImageIcon("recursos/PRECIOUS.jpg");
			if (icon.getImageLoadStatus() == java.awt.MediaTracker.COMPLETE) {
				int ancho = getWidth() - panelLateral.getWidth() - 40;
				int alto = getHeight() - 40;
				
				Image img = icon.getImage();
				Image imgScale = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
				ImageIcon scaledIcon = new ImageIcon(imgScale);
				lblImagen.setIcon(scaledIcon);
				lblImagen.setText(""); 
			}
		} catch (Exception e) {
			lblImagen.setIcon(null); 
			lblImagen.setText("Bienvenido al Sistema de Gestión Clínica");
			lblImagen.setFont(new Font("Bahnschrift", Font.BOLD, 24));
			lblImagen.setForeground(new Color(100, 200, 255));
		}
	}
	
}