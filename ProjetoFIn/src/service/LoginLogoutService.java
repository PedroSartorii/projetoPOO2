package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.UsuarioDAO;
import database.BancoDados;
import entities.Usuario;
import dao.LoginLogoutDAO;

public class LoginLogoutService {

	public LoginLogoutService() {
		
	}
	
	public static int login(String userName, String senha) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		return new LoginLogoutDAO(conn).login(userName, senha);
	}
}
