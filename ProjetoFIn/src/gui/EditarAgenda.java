package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.Agenda;
import entities.Usuario;
import service.AgendaService;
import service.UsuarioService;

public class EditarAgenda extends JFrame {

	private JPanel contentPane;
	private JTextField TfNomeAgenda;
	private JTextField TfDescricao;
	private GestaoAgenda gestao_agenda;
	private int id_login;
	private JTable TblAgenda;
	private int id_agenda;
	private JTextField TfId_agenda;
	private int agendaDigitada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarAgenda frame = new EditarAgenda(0, 0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public EditarAgenda(int id_login, int id_agenda) {
		this.id_login = id_login;
		this.id_agenda = id_agenda;
		this.initComponents();
	}
	

	
	public void visualizar(int id_agenda, int usuario_id) {
			try {
			
				System.out.println("Agenda AgendaDigitada:"+ id_agenda);
				System.out.println("Agenda ID_Usuario:"+ usuario_id);
				Agenda agenda = AgendaService.visualizar(id_agenda, usuario_id); // Usuario escolhe qual agenda deseja visualizar
				
				 if (agenda != null) {
		                TfNomeAgenda.setText(agenda.getNome());
		                TfDescricao.setText(agenda.getDescricao());
		         } else if (agenda == null) {
		        	 System.out.println("Não há agenda cadastrada!");
		        	 TfDescricao.setText("");
		        	 TfNomeAgenda.setText("");
		         }	 
			}catch (SQLException | IOException e) {
				System.out.println("Não existe agendas cadastradas!");	 
			}
	}
	
	private void deletar() throws SQLException, IOException {
		int idAgenda = Integer.parseInt(TfId_agenda.getText()); // Usuario escolhe qual agenda deseja deletar
		AgendaService.deletar(idAgenda);
	}
	
	private void atualizarAgenda(int id_agenda) {
		try {
			Agenda agenda = new Agenda();
			
			agenda.setNome(this.TfNomeAgenda.getText());
			agenda.setDescricao(this.TfDescricao.getText()); 
			agenda.setId(id_agenda);
			
			String resposta = AgendaService.atualizar(agenda);
			if(resposta == "update de agenda realizado!") {
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
		setBounds(100, 100, 837, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Atualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idAgenda = Integer.parseInt(TfId_agenda.getText());
				atualizarAgenda(idAgenda);
			}
		});
		btnNewButton.setBounds(168, 471, 122, 21);
		contentPane.add(btnNewButton);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deletar();
					System.out.println("Agenda Excluída!");
					TfNomeAgenda.setText("");
					TfDescricao.setText("");
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnExcluir.setBounds(348, 471, 122, 21);
		contentPane.add(btnExcluir);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarAgenda.this.dispose();
				gestao_agenda = new GestaoAgenda(id_login);
				gestao_agenda.setVisible(true);
				System.out.println();
			}
		});
		btnVoltar.setBounds(520, 471, 122, 21);
		contentPane.add(btnVoltar);
		
		JLabel lblNewLabel_1 = new JLabel("Nome da agenda:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 122, 141, 30);
		contentPane.add(lblNewLabel_1);
		
		TfNomeAgenda = new JTextField();
		TfNomeAgenda.setColumns(10);
		TfNomeAgenda.setBounds(140, 130, 502, 19);
		contentPane.add(TfNomeAgenda);
		
		JLabel lblNewLabel_1_1 = new JLabel("Descri\u00E7\u00E3o:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 162, 85, 30);
		contentPane.add(lblNewLabel_1_1);
		
		TfDescricao = new JTextField();
		TfDescricao.setColumns(10);
		TfDescricao.setBounds(140, 170, 502, 19);
		contentPane.add(TfDescricao);
		
		JLabel lblEditarAgenda = new JLabel("Editar Agenda");
		lblEditarAgenda.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblEditarAgenda.setBounds(301, 10, 152, 45);
		contentPane.add(lblEditarAgenda);
		
		JPanel panel = new JPanel();
		panel.setBounds(33, 218, 679, 229);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 15, 663, 204);
		panel.add(scrollPane);
		
		TblAgenda = new JTable();
		scrollPane.setViewportView(TblAgenda);
		TblAgenda.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Descri\u00E7\u00E3o", "Nome Agenda", "Agenda_id"
			}
		));
		
		JLabel lblNewLabel_1_2 = new JLabel("Agenda:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(279, 57, 93, 30);
		contentPane.add(lblNewLabel_1_2);
		
		TfId_agenda = new JTextField();
		TfId_agenda.setColumns(10);
		TfId_agenda.setBounds(382, 65, 159, 19);
		contentPane.add(TfId_agenda);
		
		JButton btnVisualizarAgenda = new JButton("Visualizar Agenda");
		btnVisualizarAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id_login2;
				id_login2 = id_login;
				agendaDigitada = Integer.parseInt(TfId_agenda.getText());
				visualizar(id_login2, agendaDigitada);
				
			}
		});
		btnVisualizarAgenda.setBounds(592, 34, 122, 51);
		contentPane.add(btnVisualizarAgenda);
	}

	
	
}
