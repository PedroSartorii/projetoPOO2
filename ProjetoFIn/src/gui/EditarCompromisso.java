package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import entities.Agenda;
import entities.Compromisso;
import service.AgendaService;
import service.CompromissoService;

import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarCompromisso extends JFrame {

	private JPanel contentPane;
	private JTextField TfTitulo;
	private JTextField TfDescricao;
	private JTextField TfLocal;
	private JTextField TfAgenda;
	private JTextField TfPessoasConvidada;
	private JTextField TfId_compromisso;
	private int id_login;
	private int id_compromisso;
	private MaskFormatter mascaraData;
	private JFormattedTextField TfDataInicio;
	private JFormattedTextField TfDataNotificacao;
	JFormattedTextField TfDataFim;
	private int compromissoID;
	private JButton btnExcluir;
	private JButton btnEditar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarCompromisso frame = new EditarCompromisso(0,0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public EditarCompromisso(int id_login, int id_compromisso) {
		this.id_login = id_login;
		this.id_compromisso = id_compromisso;
		criarMascaraData();
		this.initComponents();
	}
	
	public void visualizar(int id_login, int id_compromisso) {
		try {
		
			System.out.println("Agenda AgendaDigitada:"+ id_login);
			System.out.println("Agenda ID_Usuario:"+ id_compromisso);
			Compromisso compromisso = CompromissoService.visualizar(id_compromisso); // Usuario escolhe qual agenda deseja visualizar
			
			 if (compromisso != null) {
				 	
				 	
				 	DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
				 	
					String dataInicio = compromisso.getDataHoraInicio().format(sdf);
					
					String dataFim = compromisso.getDataHoraTermino().format(sdf);
					
					String dataNotificacao = compromisso.getDataHoraNotificacao().format(sdf);
					
					
				 
				 	TfTitulo.setText(compromisso.getTitulo());
				 	TfDescricao.setText(compromisso.getDescricao());
				 	TfDataInicio.setText(dataInicio);
				 	TfDataFim.setText(dataFim);
				 	TfLocal.setText(compromisso.getLocal());
				 	TfAgenda.setText(String.valueOf(compromisso.getAgendaId()));
				 	TfPessoasConvidada.setText(compromisso.getPessoasConvidadas());
				 	TfDataNotificacao.setText(dataNotificacao);
				 	   
	         } else  {
	        	 System.out.println("Não há Compromisso cadastrada!");
	        	 
	        	 
	         }	 
		}catch (SQLException | IOException e) {
			System.out.println("Não existe agendas cadastradas!");	 
		}
}

	
	public String deletar() throws SQLException, IOException {
		int idCompromisso = Integer.parseInt(TfId_compromisso.getText()); // Usuario escolhe qual agenda deseja deletar
		String retornoExclusao = CompromissoService.deletar(idCompromisso);
		if(retornoExclusao == "ExclusÃ£o efetuada com sucesso") {
			System.out.println("Compromisso Excluído!");
			TfTitulo.setText("");
       	 	TfDescricao.setText("");
       	 	TfDataInicio.setText("");
       	 	TfDataFim.setText("");
       	 	TfLocal.setText("");
       	 	TfAgenda.setText("");
       	 	TfPessoasConvidada.setText("");
       	 	TfDataNotificacao.setText("");
		} else {
			System.out.println("Erro ao excluir!");
		}
		return retornoExclusao;
		
	}
	
	private void atualizarCompromisso(int id_compromisso) {
		try {
			Compromisso compromisso = new Compromisso();
			
			DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			
			
			String dataInicio = this.TfDataInicio.getText();
			LocalDateTime dataInicioLocalDate = LocalDateTime.parse(dataInicio, sdf);
			compromisso.setDataHoraInicio(dataInicioLocalDate);
			

			String dataFim = this.TfDataFim.getText();
			LocalDateTime dataFimLocalDate = LocalDateTime.parse(dataFim, sdf); // DataHoraFim
			compromisso.setDataHoraTermino(dataFimLocalDate);
			
			String dataNotificacao = this.TfDataNotificacao.getText();
			LocalDateTime dataNotificacaoLocalDate = LocalDateTime.parse(dataNotificacao, sdf); // DataHoraFim
			compromisso.setDataHoraNotificacao(dataNotificacaoLocalDate);
			
			compromisso.setTitulo(TfTitulo.getText());
			compromisso.setDescricao(TfDescricao.getText());
			compromisso.setLocal(TfLocal.getText());
			compromisso.setAgendaId(Integer.parseInt(TfAgenda.getText()));
			compromisso.setPessoasConvidadas(TfPessoasConvidada.getText());
//		 	TfTitulo.setText(compromisso.getTitulo());
//		 	TfDescricao.setText(compromisso.getDescricao());
		 
//		 	TfLocal.setText(compromisso.getLocal());
//		 	TfAgenda.setText(String.valueOf(compromisso.getAgendaId()));
//		 	TfPessoasConvidada.setText(compromisso.getPessoasConvidadas());
		 	
		 	compromisso.setId(id_compromisso);
			
			String resposta = CompromissoService.atualizar(compromisso);
			if(resposta == "update de compromisso realizado !") {
				System.out.println("Atualização feita");
			} else {
				System.out.println("Não foi possível fazer a atualização!");
			}	
		} catch (SQLException | IOException e) {
			System.out.println("Erro ao cadastrar!");
		}	
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 926, 691);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EDtulo:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 56, 102, 19);
		contentPane.add(lblNewLabel_1);
		
		TfTitulo = new JTextField();
		TfTitulo.setColumns(10);
		TfTitulo.setBounds(74, 58, 268, 19);
		contentPane.add(TfTitulo);
		
		JLabel lblNewLabel_1_1 = new JLabel("Descri\u00E7\u00E3o:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 109, 102, 19);
		contentPane.add(lblNewLabel_1_1);
		
		TfDescricao = new JTextField();
		TfDescricao.setColumns(10);
		TfDescricao.setBounds(88, 111, 268, 19);
		contentPane.add(TfDescricao);
		
		JLabel lblNewLabel_1_2 = new JLabel("Data/Hora In\u00EDcio:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(10, 161, 131, 19);
		contentPane.add(lblNewLabel_1_2);
		
		 TfDataInicio = new JFormattedTextField(mascaraData);
		TfDataInicio.setBounds(151, 163, 224, 19);
		contentPane.add(TfDataInicio);
		
		JLabel lblNewLabel_1_3 = new JLabel("Data/Hora Fim:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(10, 224, 148, 19);
		contentPane.add(lblNewLabel_1_3);
		
		 TfDataFim = new JFormattedTextField(mascaraData);
		TfDataFim.setBounds(151, 226, 224, 19);
		contentPane.add(TfDataFim);
		
		JLabel lblNewLabel_1_6 = new JLabel("Local:");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_6.setBounds(10, 294, 102, 19);
		contentPane.add(lblNewLabel_1_6);
		
		TfLocal = new JTextField();
		TfLocal.setColumns(10);
		TfLocal.setBounds(74, 296, 268, 19);
		contentPane.add(TfLocal);
		
		JLabel LAgenda = new JLabel("Agenda:");
		LAgenda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LAgenda.setBounds(10, 355, 102, 19);
		contentPane.add(LAgenda);
		
		TfAgenda = new JTextField();
		TfAgenda.setColumns(10);
		TfAgenda.setBounds(74, 357, 268, 19);
		contentPane.add(TfAgenda);
		
		JLabel lblNewLabel_1_8 = new JLabel("Pessoas convidadas:");
		lblNewLabel_1_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_8.setBounds(10, 419, 160, 19);
		contentPane.add(lblNewLabel_1_8);
		
		TfPessoasConvidada = new JTextField();
		TfPessoasConvidada.setColumns(10);
		TfPessoasConvidada.setBounds(161, 421, 268, 19);
		contentPane.add(TfPessoasConvidada);
		
		JLabel lblNewLabel_1_9 = new JLabel("Data/Hora Notifica\u00E7\u00E3o:");
		lblNewLabel_1_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_9.setBounds(10, 477, 160, 19);
		contentPane.add(lblNewLabel_1_9);
		
		 TfDataNotificacao = new JFormattedTextField(mascaraData);
		TfDataNotificacao.setBounds(185, 479, 244, 19);
		contentPane.add(TfDataNotificacao);
		
		JButton btnNewButton = new JButton("Visualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id_compromisso2;
				id_compromisso2 = id_compromisso;
				compromissoID = Integer.parseInt(TfId_compromisso.getText());
				visualizar(id_login, compromissoID);
			}
		});
		btnNewButton.setBounds(723, 10, 119, 41);
		contentPane.add(btnNewButton);
		
		TfId_compromisso = new JTextField();
		TfId_compromisso.setBounds(528, 21, 170, 30);
		contentPane.add(TfId_compromisso);
		TfId_compromisso.setColumns(10);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deletar();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnExcluir.setBounds(723, 87, 119, 41);
		contentPane.add(btnExcluir);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idCompromisso2 = Integer.parseInt(TfId_compromisso.getText());
				atualizarCompromisso(idCompromisso2);
			}
		});
		btnEditar.setBounds(723, 163, 119, 41);
		contentPane.add(btnEditar);
	}
	
	private void criarMascaraData() { // Inicio mascara
		try {
			this.mascaraData = new MaskFormatter("##/##/#### ##:##:##");
		} catch(ParseException e) {
			System.out.println("Erro na data: " + e.getMessage());
		}
	}
	
	
}
