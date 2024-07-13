package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.UsuarioDAO;
import database.BancoDados;
import entities.Usuario;

public class UsuarioService {

	public UsuarioService() {
		
	}
	
	public static String cadastrar(Usuario usuario) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new UsuarioDAO(conn).cadastrar(usuario);
	}
	
	public static Usuario visualizar(int id) throws SQLException, IOException{
		Connection conn = BancoDados.conectar();
		return new UsuarioDAO(conn).visualizar(id);
	}
	
	public static String atualizar(Usuario usuario) throws SQLException, IOException{
		Connection conn = BancoDados.conectar();
		return new UsuarioDAO(conn).atualizar(usuario);
	}
	
	public static void deletar(int id)throws SQLException, IOException{
		Connection conn = BancoDados.conectar();
		new UsuarioDAO(conn).deletar(id);
		
	}
}
