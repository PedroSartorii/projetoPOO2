package service;

import database.BancoDados;
import entities.Compromisso;
import entities.Convite;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.CompromissoDAO;
import dao.ConviteDAO;

public class ConviteService {
	
	public ConviteService(){
		
	}
	
	 public static String cadastrar(Convite convite) throws SQLException, IOException {
		 	Connection conn = BancoDados.conectar();
			return new ConviteDAO(conn).cadastrar(convite);
	 }
	 
	 public static Convite visualizar(int id) throws SQLException, IOException {
		 Connection conn = BancoDados.conectar();
		return new ConviteDAO(conn).visualizar(id);
	 }
	 
	 public static String deletar(int id) throws  SQLException, IOException {
		Connection conn = BancoDados.conectar();
		return new ConviteDAO(conn).deletar(id); 
	 }
	 
	 public static String atualizar(Convite convite) throws SQLException, IOException {
		 Connection conn = BancoDados.conectar();
		return new ConviteDAO(conn).atualizar(convite); 
	 }
	 
	 public static String aceitarConvite(int id) throws SQLException, IOException {
		Connection conn = BancoDados.conectar();
		return new ConviteDAO(conn).aceitarConvite(id); 
	 }
	 

}
