package dao;

import database.BancoDados;
import entities.Agenda;
import entities.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import entities.Agenda;

public class AgendaDAO {
	 private Connection conn;

	    public AgendaDAO(Connection conn) {
	        this.conn = conn;
	    }

	    public Agenda cadastrar(Agenda agenda) throws SQLException {
	        PreparedStatement st = null;
	        ResultSet rs = null;
	       
	        
	        try {
	            st = conn.prepareStatement("insert into agenda(usuario_id, nome, descricao) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
	            st.setInt(1, agenda.getUsuarioId());
	            st.setString(2, agenda.getNome());
	            st.setString(3, agenda.getDescricao());
	            st.executeUpdate();
	            
	            

	            rs = st.getGeneratedKeys();
	            if (rs.next()) {
	                agenda.setId(rs.getInt(1));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            BancoDados.finalizarStatement(st);
	            BancoDados.desconectar();
	        }

	        return agenda;
	    }

	    public String atualizar(Agenda agenda) throws SQLException {
	        PreparedStatement st = null;
	        boolean agendaExist = agendaExists(agenda.getId());

	        if (!agendaExist) {
	            return "agenda não encontrada";
	        }

	        try {
	            st = conn.prepareStatement("update agenda set nome = ?, descricao = ? where id = ?");
	            st.setString(1, agenda.getNome());
	            st.setString(2, agenda.getDescricao());
	            st.setInt(3, agenda.getId());
	            

	            int rowsAffected = st.executeUpdate();

	            if (rowsAffected > 0) {
	                return "update de agenda realizado!";
	            } else {
	                return "falha ao atualizar a agenda.";
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return "erro durante a atualização.";
	        } finally {
	            BancoDados.finalizarStatement(st);
	            BancoDados.desconectar();
	        }
	    }

	    public Agenda visualizar(int id_agenda, int usuario_id) throws SQLException {
	        PreparedStatement st = null;
	        ResultSet rs = null;
	        
	        System.out.println("DAO:"+ id_agenda);
	        System.out.println("DAO:"+ usuario_id);
	        try {
	            st = conn.prepareStatement("select * from agenda where id = ? and usuario_id = ?");
	            st.setInt(1, usuario_id);
	            st.setInt(2, id_agenda);
	            rs = st.executeQuery();

	            if(rs.next()) {
	                Agenda agenda = new Agenda();
	                agenda.setId(rs.getInt("id"));
	                agenda.setNome(rs.getString("nome"));
	                agenda.setDescricao(rs.getString("descricao"));
	                agenda.setUsuarioId(rs.getInt("usuario_id"));

	                return agenda;
	            }

	        } catch(SQLException e) {
	            e.printStackTrace();
	        } finally {
	            BancoDados.finalizarStatement(st);
	            BancoDados.desconectar();
	        }

	        return null;
	    }

	    public String deletar(int id) throws  SQLException {
	        PreparedStatement st = null;

	        try {
	            st = conn.prepareStatement("delete from agenda where id = ?");
	            st.setInt(1, id);
	            int linhasManipuladas = st.executeUpdate();

	            if(linhasManipuladas != 0) {
	                return "Exclusão efetuada com sucesso";
	            }

	            return "Erro ao tentar excluir";
	        } finally {
	            BancoDados.finalizarStatement(st);
	            BancoDados.desconectar();
	        }
	    }

	    public boolean agendaExists(int agendaId) throws SQLException {
	        PreparedStatement st = null;
	        ResultSet rs = null;
	        boolean exists = false;

	        try {
	            st = conn.prepareStatement("SELECT COUNT(*) FROM agenda WHERE id = ?");
	            st.setInt(1, agendaId);
	            rs = st.executeQuery();

	            if (rs.next()) {
	                exists = rs.getInt(1) > 0;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            BancoDados.finalizarResultSet(rs);
	            BancoDados.finalizarStatement(st);
	        }

	        return exists;
	    }
	    
	    
	    
	    
	}

