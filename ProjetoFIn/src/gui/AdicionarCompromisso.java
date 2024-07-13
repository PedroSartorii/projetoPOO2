package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.mysql.cj.protocol.a.LocalDateTimeValueEncoder;

import dao.CompromissoDAO;
import entities.Compromisso;
import service.CompromissoService;
import entities.Usuario;
import service.UsuarioService;

import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdicionarCompromisso extends JFrame {

	private JPanel contentPane;
	private JTextField TfTitulo;
	private JTextField TfDescricao;
	private JTextField TfLocal;
	private JTextField TfAgenda;
	private JTextField TfConvidados;
	private MaskFormatter mascaraData;
	private int id_login;
	private MaskFormatter mascaraHora;
	private JFormattedTextField TfDataInicio;
	private JFormattedTextField TfDataFim;
	private JFormattedTextField DataNotificacao;
	private EditarCompromisso EditarCompromisso;
	private int id_compromisso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdicionarCompromisso frame = new AdicionarCompromisso(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public AdicionarCompromisso(int id_login) {
		this.id_login = id_login;
		System.out.println(id_login);
		this.criarMascaraData();
		this.initComponents();
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 996, 651);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Adicionar Compromisso:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(343, 10, 247, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EDtulo:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 79, 102, 19);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Descri\u00E7\u00E3o:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 132, 102, 19);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Data/Hora In\u00EDcio:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(10, 184, 131, 19);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Data/Hora Fim:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(10, 247, 148, 19);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_6 = new JLabel("Local:");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_6.setBounds(10, 317, 102, 19);
		contentPane.add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_7 = new JLabel("Agenda:");
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_7.setBounds(10, 378, 102, 19);
		contentPane.add(lblNewLabel_1_7);
		
		JLabel lblNewLabel_1_8 = new JLabel("Pessoas convidadas:");
		lblNewLabel_1_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_8.setBounds(10, 442, 160, 19);
		contentPane.add(lblNewLabel_1_8);
		
		JLabel lblNewLabel_1_9 = new JLabel("Data/Hora Notifica\u00E7\u00E3o:");
		lblNewLabel_1_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_9.setBounds(10, 500, 160, 19);
		contentPane.add(lblNewLabel_1_9);
		
		TfTitulo = new JTextField();
		TfTitulo.setBounds(74, 81, 268, 19);
		contentPane.add(TfTitulo);
		TfTitulo.setColumns(10);
		
		TfDescricao = new JTextField();
		TfDescricao.setColumns(10);
		TfDescricao.setBounds(88, 134, 268, 19);
		contentPane.add(TfDescricao);
		
		TfLocal = new JTextField();
		TfLocal.setColumns(10);
		TfLocal.setBounds(74, 319, 268, 19);
		contentPane.add(TfLocal);
		
		TfAgenda = new JTextField();
		TfAgenda.setColumns(10);
		TfAgenda.setBounds(74, 380, 268, 19);
		contentPane.add(TfAgenda);
		
		TfConvidados = new JTextField();
		TfConvidados.setColumns(10);
		TfConvidados.setBounds(161, 444, 268, 19);
		contentPane.add(TfConvidados);
		
		
		TfDataInicio = new JFormattedTextField(mascaraData);
		TfDataInicio.setBounds(151, 186, 224, 19);
		contentPane.add(TfDataInicio);
		
		TfDataFim = new JFormattedTextField(mascaraData);
		TfDataFim.setBounds(151, 249, 224, 19);
		contentPane.add(TfDataFim);
		
		DataNotificacao = new JFormattedTextField(mascaraData);
		DataNotificacao.setBounds(185, 502, 244, 19);
		contentPane.add(DataNotificacao);
		
		JButton BtAdicionar = new JButton("Adicionar");
		BtAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarCompromisso();
			}
		});
		BtAdicionar.setBounds(257, 566, 131, 38);
		contentPane.add(BtAdicionar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdicionarCompromisso.this.dispose();
				EditarCompromisso = new EditarCompromisso(id_login, id_compromisso);
				System.out.println("ID_LOGIN Compromisso:"+ id_login);
				System.out.println("ID_Compromisso:"+ id_compromisso);
				
				EditarCompromisso.setVisible(true);
				System.out.println();
			}
		});
		btnEditar.setBounds(760, 10, 131, 38);
		contentPane.add(btnEditar);
	}

	
	
	private void criarMascaraData() { // Inicio mascara
		try {
			this.mascaraData = new MaskFormatter("##/##/#### ##:##:##");
		} catch(ParseException e) {
			System.out.println("Erro na data: " + e.getMessage());
		}
	}
	
	private void cadastrarCompromisso() {
		
		try {
			DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			String dataInicio = this.TfDataInicio.getText();
			LocalDateTime dataInicioLocalDate = LocalDateTime.parse(dataInicio, sdf); // DataHoraInicio
			String dataFim = this.TfDataFim.getText();
			LocalDateTime dataFimLocalDate = LocalDateTime.parse(dataFim, sdf); // DataHoraFim
			String dataNotificacao = this.DataNotificacao.getText();
			LocalDateTime dataNotificacaoLocalDate = LocalDateTime.parse(dataNotificacao, sdf); // DataHoraFim
			
			
			Compromisso compromisso = new Compromisso();
			
			compromisso.setDataHoraInicio(dataInicioLocalDate);
			compromisso.setDataHoraTermino(dataFimLocalDate);
			compromisso.setDataHoraNotificacao(dataNotificacaoLocalDate);
			compromisso.setTitulo(this.TfTitulo.getText());
			compromisso.setDescricao(this.TfDescricao.getText()); 
			compromisso.setPessoasConvidadas(this.TfConvidados.getText());
			compromisso.setLocal(this.TfLocal.getText());
			compromisso.setAgendaId(Integer.parseInt(this.TfAgenda.getText())); //Id agenda
			
			
			String resposta = CompromissoService.cadastrar(compromisso);
			this.id_compromisso = compromisso.getId();
			if(resposta == "cadastro  de compromisso realizado !") {
				System.out.println("cadastro feito");
			} 
			
		} catch (SQLException | IOException e) {
			System.out.println("Erro ao cadastrar!");
		}
				
	}
	
	
}
