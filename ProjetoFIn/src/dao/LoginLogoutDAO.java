package dao;

import database.BancoDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class LoginLogoutDAO {
    private Connection conn;

    public LoginLogoutDAO(Connection conn) {
        this.conn = conn;
    }

    public int login(String userName, String senha) throws SQLException {
        PreparedStatement st = null;
        int id = getUsuario(userName, senha);

        try {
            st = conn.prepareStatement("update usuario set logado = 1 where id = ?");
            st.setInt(1, id);

            st.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }

        if(id != -1) {
        	return id;
        } 

        return 0;
    }

    public String logout(String userName, String senha) throws SQLException {
        PreparedStatement st = null;
        int id = getUsuario(userName, senha);

        try {
            st = conn.prepareStatement("update usuario set logado = 0 where id = ?");
            st.setInt(1, id);

            st.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }

        return "Logout realizado com sucesso";
    }

    public int getUsuario(String userName, String senha) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        int usuarioId = -1;

        try {
            st = conn.prepareStatement("select id from usuario where nome_usuario = ? and senha = ?");
            st.setString(1, userName);
            st.setString(2, senha);

            rs = st.executeQuery();
            if (rs.next()) {
                usuarioId = rs.getInt("id");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            BancoDados.finalizarResultSet(rs);
        }

        return usuarioId;
    }
}
