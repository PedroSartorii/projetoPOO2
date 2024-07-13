package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Usuario;
import service.LoginLogoutService;
import dao.UsuarioDAO;
import gui.CadastrarUsuario;
import gui.EditarUsuario;
import gui.GestaoAgenda;
import service.UsuarioService;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.awt.event.ActionEvent;



public class Login extends JFrame {

	private JPanel panel;
	private JTextField TFNome;
	private JTextField TFSenha;
	private GestaoAgenda gestao_agenda; // definindo a tela que vai seguir
	private CadastrarUsuario CadastrarUsuario;
	private EditarUsuario EditarUsuario;// definindo a tela que vai seguir
	private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private int id_login;

	
	public void run() {
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

	public Login() {
		
		this.initComponents();
	}
	
	
	public void initComponents() {
		setTitle("Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 457);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel LNome = new JLabel("Nome:");
		LNome.setFont(new Font("Tahoma", Font.PLAIN, 19));
		LNome.setBackground(new Color(240, 240, 240));
		LNome.setBounds(97, 129, 78, 30);
		panel.add(LNome);
		
		TFNome = new JTextField();
		TFNome.setBounds(174, 134, 333, 29);
		panel.add(TFNome);
		TFNome.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:\r\n");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSenha.setBackground(SystemColor.menu);
		lblSenha.setBounds(97, 201, 78, 30);
		panel.add(lblSenha);
		
		TFSenha = new JTextField();
		TFSenha.setColumns(10);
		TFSenha.setBounds(174, 206, 333, 29);
		panel.add(TFSenha);
		
		
		JButton BTConfirmar = new JButton("Confirmar");
		BTConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					login();
					
					if(id_login != 0) {
						Login.this.dispose();
						gestao_agenda = new GestaoAgenda(id_login);
						gestao_agenda.setVisible(true);
						System.out.println(id_login);
						
					} else {
						System.out.println("Não foi possível fazer login!"); // Criar uma mensagem de erro depois
					}
						
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
			}
		});
		BTConfirmar.setBounds(219, 305, 101, 30);
		panel.add(BTConfirmar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.this.dispose();
				CadastrarUsuario  = new CadastrarUsuario();
				CadastrarUsuario.setVisible(true);
			}
		});
		btnCadastrar.setBounds(362, 305, 101, 30);
		panel.add(btnCadastrar);
		
		JLabel lblNewLabel = new JLabel("Sem Login? Clique Aqui!");
		lblNewLabel.setBounds(352, 265, 115, 30);
		panel.add(lblNewLabel);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblLogin.setBounds(275, 60, 149, 37);
		panel.add(lblLogin);
	}
	
	public void login() throws SQLException, IOException {
		
		String LoginNome = this.TFNome.getText();
		String senha = this.TFSenha.getText();
		this.id_login = LoginLogoutService.login(LoginNome, senha);
		
	}
	
	
}
