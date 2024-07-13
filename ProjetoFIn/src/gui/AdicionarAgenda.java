package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Agenda;
import entities.Usuario;
import service.AgendaService;
import service.UsuarioService;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class AdicionarAgenda extends JFrame {

	private JPanel contentPane;
	private JTextField TfNomeAgenda;
	private JTextField TfDescricao;
	private GestaoAgenda gestao_agenda;
	private EditarAgenda EditarAgenda;
	private AdicionarCompromisso AdicionarCompromisso;
	private int id_login;
	private int id_agenda;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdicionarAgenda frame = new AdicionarAgenda(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdicionarAgenda(int id_login) {
		this.id_login = id_login;
		System.out.println(id_login);
		this.initComponents();
	}
	
	
	
	
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Adicionar Agenda");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setBounds(220, 10, 221, 45);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome da agenda:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 88, 141, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Descri\u00E7\u00E3o:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 161, 85, 30);
		contentPane.add(lblNewLabel_1_1);
		
		JButton BtConfirmar = new JButton("Confirmar");
		BtConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarAgenda();
				EditarAgenda  = new EditarAgenda(id_login, id_agenda);
				System.out.println("ID_LOGIN:"+ id_login);
				System.out.println("ID_AGENDA:"+ id_agenda);
				EditarAgenda.setVisible(true);
					
			}
		});
		BtConfirmar.setBounds(29, 310, 122, 45);
		contentPane.add(BtConfirmar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdicionarAgenda.this.dispose();
				gestao_agenda = new GestaoAgenda(id_login);
				gestao_agenda.setVisible(true);
				System.out.println();
			}
		});
		btnVoltar.setBounds(185, 310, 122, 45);
		contentPane.add(btnVoltar);
		
		TfNomeAgenda = new JTextField();
		TfNomeAgenda.setBounds(140, 96, 502, 19);
		contentPane.add(TfNomeAgenda);
		TfNomeAgenda.setColumns(10);
		
		TfDescricao = new JTextField();
		TfDescricao.setColumns(10);
		TfDescricao.setBounds(140, 169, 502, 19);
		contentPane.add(TfDescricao);
		
		JButton btnEditarAgenda = new JButton("Editar Agenda");
		btnEditarAgenda.setBounds(347, 310, 122, 45);
		contentPane.add(btnEditarAgenda);
		
		JLabel lblNewLabel_2 = new JLabel("J\u00E1 tem Agenda? Edite aqui");
		lblNewLabel_2.setBounds(347, 274, 122, 26);
		contentPane.add(lblNewLabel_2);
		
		JButton btnGestoCompromisso = new JButton("Gest\u00E3o Compromisso");
		btnGestoCompromisso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdicionarAgenda.this.dispose();
				AdicionarCompromisso = new AdicionarCompromisso(id_login);
				System.out.println("ID_LOGIN:"+ id_login);
				System.out.println("ID_AGENDA:"+ id_agenda);
				AdicionarCompromisso.setVisible(true);
				System.out.println();
			}
		});
		btnGestoCompromisso.setBounds(507, 310, 135, 45);
		contentPane.add(btnGestoCompromisso);
		
		JLabel lblNewLabel_2_1 = new JLabel("Adicione Compromisso");
		lblNewLabel_2_1.setBounds(507, 274, 135, 26);
		contentPane.add(lblNewLabel_2_1);
	}
	
	
	private void cadastrarAgenda() {
		
		try {
			Agenda agenda = new Agenda();
			
			agenda.setUsuarioId(id_login);
			agenda.setNome(this.TfNomeAgenda.getText());
			agenda.setDescricao(this.TfDescricao.getText()); 
			
			Agenda agenda_create = AgendaService.cadastrar(agenda);
			System.out.println(agenda_create.getId());
			this.id_agenda = agenda_create.getId();
		} catch (SQLException | IOException e) {
			System.out.println("Erro ao cadastrar agenda!");
		}	
	}
	
	
	
	
}
