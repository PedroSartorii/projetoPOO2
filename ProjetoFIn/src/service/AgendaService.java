package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.UsuarioDAO;
import database.BancoDados;
import entities.Usuario;
import dao.AgendaDAO;
import entities.Agenda;

public class AgendaService {
	
	public AgendaService() {
		
	}
	
	public static Agenda cadastrar(Agenda agenda) throws SQLException, IOException {
		Connection conn = BancoDados.conectar();
		return new AgendaDAO(conn).cadastrar(agenda);
	}

	
	public static void deletar(int id) throws  SQLException, IOException {
		Connection conn = BancoDados.conectar();
		new AgendaDAO(conn).deletar(id);
	}
	
	public static String atualizar(Agenda agenda) throws SQLException, IOException {
		Connection conn = BancoDados.conectar();
		return new AgendaDAO(conn).atualizar(agenda);
	}
	
	public static Agenda visualizar(int id, int usuario_id) throws SQLException, IOException {
		Connection conn = BancoDados.conectar();
		System.out.println("Service: " + id);
		System.out.println("Service: " + usuario_id);
		return new AgendaDAO(conn).visualizar(id, usuario_id);
	}
}
