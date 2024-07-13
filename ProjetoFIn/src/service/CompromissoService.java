package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.UsuarioDAO;
import database.BancoDados;
import entities.Usuario;
import dao.AgendaDAO;
import entities.Agenda;
import dao.CompromissoDAO;
import entities.Compromisso;


public class CompromissoService {
	
	public CompromissoService() {
	
	}

	public static String cadastrar(Compromisso compromisso) throws SQLException, IOException {
		Connection conn = BancoDados.conectar();
		return new CompromissoDAO(conn).cadastrar(compromisso);
	}
	
	public static Compromisso visualizar(int id) throws SQLException,IOException {
		Connection conn = BancoDados.conectar();
		return new CompromissoDAO(conn).visualizar(id);
	}
	
	public static String deletar(int id) throws  SQLException,IOException {
		Connection conn = BancoDados.conectar();
		return new CompromissoDAO(conn).deletar(id);
	}
	
	public static String atualizar(Compromisso compromisso) throws SQLException,IOException{
		Connection conn = BancoDados.conectar();
		return new CompromissoDAO(conn).atualizar(compromisso);
	}
}
