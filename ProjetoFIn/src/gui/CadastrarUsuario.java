package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import dao.UsuarioDAO;
import entities.Usuario;
import service.UsuarioService;

import javax.swing.JLabel;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CadastrarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField TFNome;
	private JTextField TFEmail;
	private JTextField TFNomeUsu;
	private JTextField TFSenha;
	private JTextField TFFoto;
	private JFormattedTextField FTFData;
	private JRadioButton BtMasculino;
	private JRadioButton BtFeminino;
	private JRadioButton BtOutro;
	private UsuarioDAO usuario;
	private Login login;
	
	
	
	private MaskFormatter mascaraData;

	/**
	 * Launch the application.
	 */
	
	
	public CadastrarUsuario() {
		this.criarMascaraData();
		this.initComponents();
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LNome = new JLabel("Nome Completo:");
		LNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LNome.setBounds(10, 38, 112, 19);
		contentPane.add(LNome);
		
		JLabel LData = new JLabel("Data de Nascimento:");
		LData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LData.setBounds(10, 77, 141, 19);
		contentPane.add(LData);
		
		JLabel LGenero = new JLabel("Gen\u00EAro:");
		LGenero.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LGenero.setBounds(10, 116, 65, 19);
		contentPane.add(LGenero);
		
		JLabel LFoto = new JLabel("Foto:");
		LFoto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LFoto.setBounds(10, 157, 112, 19);
		contentPane.add(LFoto);
		
		JLabel LEmail = new JLabel("E-mail:");
		LEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LEmail.setBounds(10, 196, 112, 19);
		contentPane.add(LEmail);
		
		JLabel LNomeUsu = new JLabel("Nome de Usu\u00E1rio:");
		LNomeUsu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LNomeUsu.setBounds(10, 238, 130, 19);
		contentPane.add(LNomeUsu);
		
		JLabel LSenha = new JLabel("Senha:");
		LSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LSenha.setBounds(10, 280, 112, 19);
		contentPane.add(LSenha);
		
		JButton BTCadastro = new JButton("Fazer Cadastro");
		BTCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarUsuario();
			}
		});
		BTCadastro.setBounds(147, 353, 112, 21);
		contentPane.add(BTCadastro);
		
		TFNome = new JTextField();
		TFNome.setBounds(147, 40, 425, 19);
		contentPane.add(TFNome);
		TFNome.setColumns(10);
		
		TFEmail = new JTextField();
		TFEmail.setColumns(10);
		TFEmail.setBounds(147, 198, 425, 19);
		contentPane.add(TFEmail);
		
		TFNomeUsu = new JTextField();
		TFNomeUsu.setColumns(10);
		TFNomeUsu.setBounds(147, 240, 425, 19);
		contentPane.add(TFNomeUsu);
		
		TFSenha = new JTextField();
		TFSenha.setColumns(10);
		TFSenha.setBounds(147, 282, 425, 19);
		contentPane.add(TFSenha);
		
		TFFoto = new JTextField();
		TFFoto.setColumns(10);
		TFFoto.setBounds(147, 159, 425, 19);
		contentPane.add(TFFoto);
		
		FTFData = new JFormattedTextField(mascaraData);
		FTFData.setBounds(161, 79, 103, 19);
		contentPane.add(FTFData);
		
		BtMasculino = new JRadioButton("Masculino");
		BtMasculino.setBounds(147, 117, 103, 21);
		contentPane.add(BtMasculino);
		
		BtFeminino = new JRadioButton("Feminino");
		BtFeminino.setBounds(277, 117, 103, 21);
		contentPane.add(BtFeminino);
		
		BtOutro = new JRadioButton("Outro");
		BtOutro.setBounds(415, 117, 103, 21);
		contentPane.add(BtOutro);
		
		JButton BtVoltar = new JButton("Voltar");
		BtVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarUsuario.this.dispose();
				login  = new Login();
				login.setVisible(true);
			}
		});
		BtVoltar.setBounds(394, 353, 112, 21);
		contentPane.add(BtVoltar);
	}
	

	/**
	 * Create the frame.
	 */
	
	
	private void criarMascaraData() { // Inicio mascara
		try {
			this.mascaraData = new MaskFormatter("##/##/####");
		} catch(ParseException e) {
			System.out.println("Erro na data: " + e.getMessage());
		}
	} // fim mascara
	
	private void cadastrarUsuario() {
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Usuario usuario = new Usuario();
			
			usuario.setDataNascimento(new java.sql.Date(sdf.parse(this.FTFData.getText()).getTime()));
			usuario.setFoto(this.TFFoto.getText());
			usuario.setNome(this.TFNome.getText()); 
			usuario.setGenero(verificarSexo());
			usuario.setEmail(this.TFEmail.getText());
			usuario.setNomeUsuario(this.TFNomeUsu.getText());
			usuario.setSenha(this.TFSenha.getText());
			
			String resposta = UsuarioService.cadastrar(usuario);
			if(resposta == "cadastro realizado !") {
				System.out.println("cadastro feito");
			} 
			
		} catch (ParseException | SQLException | IOException e) {
			System.out.println("Erro ao cadastrar!");
		}
			
		
	}
	
	private String verificarSexo() {
		if (this.BtMasculino.isSelected()) {
			return this.BtMasculino.getText();
		} else if (this.BtFeminino.isSelected()) {
			return this.BtFeminino.getText();
		} else {
			return this.BtOutro.getText();
		}
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarUsuario frame = new CadastrarUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
