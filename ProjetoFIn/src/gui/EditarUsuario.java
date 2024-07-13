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
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarUsuario extends JFrame {

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
	private MaskFormatter mascaraData;
	private Login login;
	private int id_login;
	private JButton btnVoltar;
	private JButton btnDeletar;
	private GestaoAgenda gestao_agenda;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarUsuario frame = new EditarUsuario(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public EditarUsuario(int id_login) {
		this.criarMascaraData();
		this.id_login = id_login;
		System.out.println(id_login);
		this.initComponents();
		this.visualizarUsuario(id_login);
	}
	
	private void atualizar(int id_login) {
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
			usuario.setId(id_login);
			
			String resposta = UsuarioService.atualizar(usuario);
			if(resposta == "update realizado !") {
				System.out.println("Atualização feita");
			} 
				
		} catch (ParseException | SQLException | IOException e) {
			System.out.println("Erro ao cadastrar!");
		}
		
	}

	public void visualizarUsuario(int id_login) {
		try {
			
			Usuario usuario = UsuarioService.visualizar(id_login);
			
			 if (usuario != null) {
	                TFNome.setText(usuario.getNome());
	                FTFData.setText(new SimpleDateFormat("dd/MM/yyyy").format(usuario.getDataNascimento()));
	                TFFoto.setText(usuario.getFoto());
	                TFNomeUsu.setText(usuario.getNomeUsuario());
	                TFSenha.setText(usuario.getSenha());
	                TFEmail.setText(usuario.getEmail());
	                if (usuario.getGenero().equalsIgnoreCase("masculino")) {
	                    BtMasculino.setSelected(true);
	                } else if (usuario.getGenero().equalsIgnoreCase("feminino")) {
	                    BtFeminino.setSelected(true);
	                } else {
	                    BtOutro.setSelected(true);
	                }
	            }
		}catch (SQLException | IOException e) {
			System.out.println("Erro ao visualizar!");	 
		}
	}
		
	private void deletar(int id_login) throws SQLException, IOException {
		UsuarioService.deletar(id_login);
	}	
		
		private void criarMascaraData() { // Inicio mascara
			try {
				this.mascaraData = new MaskFormatter("##/##/####");
			} catch(ParseException e) {
				System.out.println("Erro na data: " + e.getMessage());
			}
		} // fim mascara

	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LNome = new JLabel("Nome Completo:");
		LNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LNome.setBounds(60, 10, 112, 19);
		contentPane.add(LNome);
		
		TFNome = new JTextField();
		TFNome.setColumns(10);
		TFNome.setBounds(197, 12, 425, 19);
		contentPane.add(TFNome);
		
		JLabel LData = new JLabel("Data de Nascimento:");
		LData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LData.setBounds(60, 49, 141, 19);
		contentPane.add(LData);
		
		FTFData = new JFormattedTextField(mascaraData);
		FTFData.setBounds(211, 51, 103, 19);
		contentPane.add(FTFData);
		
		JLabel LGenero = new JLabel("Gen\u00EAro:");
		LGenero.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LGenero.setBounds(60, 88, 65, 19);
		contentPane.add(LGenero);
		
		BtMasculino = new JRadioButton("Masculino");
		BtMasculino.setBounds(197, 89, 103, 21);
		contentPane.add(BtMasculino);
		
		BtFeminino = new JRadioButton("Feminino");
		BtFeminino.setBounds(327, 89, 103, 21);
		contentPane.add(BtFeminino);
		
		BtOutro = new JRadioButton("Outro");
		BtOutro.setBounds(465, 89, 103, 21);
		contentPane.add(BtOutro);
		
		JLabel LFoto = new JLabel("Foto:");
		LFoto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LFoto.setBounds(60, 129, 112, 19);
		contentPane.add(LFoto);
		
		TFFoto = new JTextField();
		TFFoto.setColumns(10);
		TFFoto.setBounds(197, 131, 425, 19);
		contentPane.add(TFFoto);
		
		JLabel LEmail = new JLabel("E-mail:");
		LEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LEmail.setBounds(60, 168, 112, 19);
		contentPane.add(LEmail);
		
		TFEmail = new JTextField();
		TFEmail.setColumns(10);
		TFEmail.setBounds(197, 170, 425, 19);
		contentPane.add(TFEmail);
		
		JLabel LNomeUsu = new JLabel("Nome de Usu\u00E1rio:");
		LNomeUsu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LNomeUsu.setBounds(60, 210, 130, 19);
		contentPane.add(LNomeUsu);
		
		TFNomeUsu = new JTextField();
		TFNomeUsu.setColumns(10);
		TFNomeUsu.setBounds(197, 212, 425, 19);
		contentPane.add(TFNomeUsu);
		
		JLabel LSenha = new JLabel("Senha:");
		LSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LSenha.setBounds(60, 252, 112, 19);
		contentPane.add(LSenha);
		
		TFSenha = new JTextField();
		TFSenha.setColumns(10);
		TFSenha.setBounds(197, 254, 425, 19);
		contentPane.add(TFSenha);
		
		JButton btnEditar = new JButton("Atualizar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar(id_login);
			}
		});
		btnEditar.setBounds(174, 323, 112, 21);
		contentPane.add(btnEditar);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarUsuario.this.dispose();
				gestao_agenda = new GestaoAgenda(id_login);
				gestao_agenda.setVisible(true);
			}
		});
		btnVoltar.setBounds(485, 323, 112, 21);
		contentPane.add(btnVoltar);
		
		btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deletar(id_login);
					System.out.println("Usuário excluído!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDeletar.setBounds(327, 323, 112, 21);
		contentPane.add(btnDeletar);
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
	
	
	
	
}
