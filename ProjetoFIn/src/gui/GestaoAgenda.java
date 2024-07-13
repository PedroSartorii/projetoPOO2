package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;
import entities.Agenda;
import dao.AgendaDAO;
import service.AgendaService;
import service.UsuarioService;

public class GestaoAgenda extends JFrame {

	private JPanel contentPane;
	private EditarUsuario EditarUsuario;
	private AdicionarAgenda AdicionarAgenda;
	private int id_login;
	private AdicionarConvite AdicionarConvite;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestaoAgenda frame = new GestaoAgenda(0);
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
	
	
	public GestaoAgenda(int id_login) {
		this.id_login = id_login;
		this.initComponents();
	}

	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Editar Usu\u00E1rio");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestaoAgenda.this.dispose();
				EditarUsuario = new EditarUsuario(id_login);
				EditarUsuario.setVisible(true);
				System.out.println(id_login);
			}
		});
		btnNewButton.setBounds(563, 10, 141, 26);
		contentPane.add(btnNewButton);
		
		JButton btnAdicionarCompromisso = new JButton("Adicionar Convite");
		btnAdicionarCompromisso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestaoAgenda.this.dispose();
				AdicionarConvite = new AdicionarConvite(id_login);
				AdicionarConvite.setVisible(true);
				System.out.println(id_login);
			}
		});
		btnAdicionarCompromisso.setBounds(10, 13, 152, 41);
		contentPane.add(btnAdicionarCompromisso);
		
		JCalendar calendario = new JCalendar();
		calendario.setBounds(240, 89, 439, 272);
		contentPane.add(calendario);
		
		JButton btnAdicionarAgenda = new JButton("Gest\u00E3o Agenda");
		btnAdicionarAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestaoAgenda.this.dispose();
				AdicionarAgenda = new AdicionarAgenda(id_login);
				AdicionarAgenda.setVisible(true);
				System.out.println(id_login);
			}
		});
		btnAdicionarAgenda.setBounds(10, 89, 152, 41);
		contentPane.add(btnAdicionarAgenda);
	}


	public void mostrar() {
//		frame.setVisible(true);
	}
}
