package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Compromisso;
import entities.Convite;
import service.CompromissoService;
import service.ConviteService;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarConvite extends JFrame {

	private JPanel contentPane;
	private JTextField TfID;
	private JTextField TfConvidado;
	private JTextField TfAceito;
	private Integer id_convite;
	private JButton btnExcluir;
	private JButton btnAceitarConvite;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarConvite frame = new EditarConvite(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public EditarConvite(int id_convite) {
		this.id_convite = id_convite;
		this.initComponents();
	}
	
	
	public void visualizar(int id) {
		try {
			
			Convite convite = ConviteService.visualizar(id); // Usuario escolhe qual agenda deseja visualizar
			System.out.println(convite.isAceito());
			
			 if (convite != null) {
				 
				 String isAceito = "";
				 if(convite.isAceito()) {
					isAceito = "aceito"; 
				 } else {
					 isAceito = "não aceito";
				 }
					
				 
				 
				 TfAceito.setText(isAceito);
				 
				 
				 TfConvidado.setText(convite.getNomeConvidado());
				 	   
	         } else  {
	        	 System.out.println("Não há Compromisso cadastrada!");
 
	         }	 
		}catch (SQLException | IOException e) {
			System.out.println("Não existe agendas cadastradas!");	 
		}
}
	
	public String deletar() throws SQLException, IOException {
		int idConvite = Integer.parseInt(TfID.getText()); // Usuario escolhe qual agenda deseja deletar
		String retornoExclusao = ConviteService.deletar(idConvite);
		
		if(retornoExclusao == "Exclusão efetuada com sucesso") {
			System.out.println("Convite Excluído!");
			
			TfConvidado.setText("");
			TfAceito.setText("");
		} else {
			System.out.println("Erro ao excluir!");
		}
		return retornoExclusao;
		
	}
	
	 public String aceitarConvite(int id) throws SQLException, IOException {
		 
		 String retornoAceite = ConviteService.aceitarConvite(id);
		 
		 if(retornoAceite == "Convite aceito com sucesso") {
			 System.out.println("Convite aceito!");
		 } else {
			 System.out.println("Não foi possível aceitar o convite!");
		 }
		 return retornoAceite;
	 }
	

	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton BtnVisualizar = new JButton("Visualizar");
		BtnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idConvite = Integer.parseInt(TfID.getText());
				visualizar(idConvite);
			}
		});
		BtnVisualizar.setBounds(552, 32, 99, 30);
		contentPane.add(BtnVisualizar);
		
		TfID = new JTextField();
		TfID.setBounds(395, 38, 111, 19);
		contentPane.add(TfID);
		TfID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Insira o Convite:");
		lblNewLabel.setBounds(395, 10, 88, 13);
		contentPane.add(lblNewLabel);
		
		TfConvidado = new JTextField();
		TfConvidado.setBounds(101, 116, 96, 19);
		contentPane.add(TfConvidado);
		TfConvidado.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Convidado:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 119, 81, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Aceito:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 181, 81, 13);
		contentPane.add(lblNewLabel_1_1);
		
		TfAceito = new JTextField();
		TfAceito.setColumns(10);
		TfAceito.setBounds(101, 178, 96, 19);
		contentPane.add(TfAceito);
		
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
		btnExcluir.setBounds(552, 97, 99, 30);
		contentPane.add(btnExcluir);
		
		btnAceitarConvite = new JButton("Aceitar Convite");
		btnAceitarConvite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idConvite2 = Integer.parseInt(TfID.getText());
				try {
					aceitarConvite(idConvite2);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAceitarConvite.setBounds(552, 164, 122, 30);
		contentPane.add(btnAceitarConvite);
	}
	
	

}
