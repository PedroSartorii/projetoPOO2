package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Agenda;
import entities.Convite;
import service.AgendaService;
import service.ConviteService;

import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdicionarConvite extends JFrame {

	private JPanel contentPane;
	private JTextField TfCompromisso;
	private JTextField TfConvidado;
	private Integer id_login;
	private EditarConvite EditarConvite;
	private Integer id_convite;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdicionarConvite frame = new AdicionarConvite(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public AdicionarConvite(int id_login) {
		this.id_login = id_login;
		this.initComponents();
	}
	
	
	private void cadastrarConvite() {
		
		try {
			Convite convite = new Convite();
			
			convite.setCompromissoId(Integer.parseInt(TfCompromisso.getText()));
			convite.setConvidadoId(Integer.parseInt(TfConvidado.getText()));
			convite.setConvidanteId(id_login);
			convite.setAceito(false);
			
			System.out.println(Integer.parseInt(TfCompromisso.getText()));
			System.out.println(Integer.parseInt(TfConvidado.getText()));
			System.out.println();

			
			String resposta = ConviteService.cadastrar(convite);
			this.id_convite = convite.getId();
			System.out.println("ID Convite: " + id_convite);
			if(resposta == "Cadastro de convite realizado com sucesso") {
				System.out.println("Convite enviado!");
			} else {
				System.out.println("Convite não enviado!");
			}
			
		} catch (SQLException | IOException e) {
			System.out.println("Erro ao cadastrar agenda!");
		}	
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 784, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Adicionar Convite");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(308, 10, 126, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Compromisso:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(29, 87, 126, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Convidado:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(29, 149, 85, 28);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarConvite();
			}
		});
		btnNewButton.setBounds(244, 373, 126, 35);
		contentPane.add(btnNewButton);
		
		TfCompromisso = new JTextField();
		TfCompromisso.setBounds(141, 94, 191, 19);
		contentPane.add(TfCompromisso);
		TfCompromisso.setColumns(10);
		
		TfConvidado = new JTextField();
		TfConvidado.setColumns(10);
		TfConvidado.setBounds(118, 156, 214, 19);
		contentPane.add(TfConvidado);
		
		JButton btnGestoConvite = new JButton("Gest\u00E3o Convite");
		btnGestoConvite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdicionarConvite.this.dispose();
				EditarConvite = new EditarConvite(id_convite);
				EditarConvite.setVisible(true);
			}
		});
		btnGestoConvite.setBounds(429, 373, 126, 35);
		contentPane.add(btnGestoConvite);
	}
	
	
	

}
