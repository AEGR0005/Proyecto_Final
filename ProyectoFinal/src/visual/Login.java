package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Clinica;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		/*
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				FileInputStream readClinica;
				FileOutputStream writeClinica;
				ObjectInputStream readClass;
				ObjectOutputStream writeClass;
				
				try {
					readClinica = new FileInputStream("clinica.dat");
					readClass = new ObjectInputStream(readClinica);
					
					Clinica.getInstancia().setClinica((Clinica)readClass.readObject());
					
					readClass.close();
					readClinica.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			
		});*/
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 395);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBackground(new Color(72, 61, 139));
		lblNewLabel.setFont(new Font("Microsoft JhengHei", Font.BOLD, 16));
		lblNewLabel.setBounds(60, 63, 69, 20);
		contentPane.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
		txtNombre.setBounds(60, 88, 232, 26);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Microsoft JhengHei", Font.BOLD, 16));
		lblContrasea.setBackground(new Color(72, 61, 139));
		lblContrasea.setBounds(60, 148, 97, 20);
		contentPane.add(lblContrasea);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
		txtPassword.setColumns(10);
		txtPassword.setBounds(60, 173, 232, 26);
		contentPane.add(txtPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(new Color(245, 222, 179));
		btnNewButton.setBounds(60, 244, 232, 29);
		contentPane.add(btnNewButton);
	}
}
