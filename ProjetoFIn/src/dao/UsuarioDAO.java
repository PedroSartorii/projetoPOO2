package dao;

import javax.xml.transform.Result;

import database.BancoDados;
import entities.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private Connection conn;

    public UsuarioDAO(Connection conn) {
        this.conn = conn;
    }

    public String cadastrar(Usuario usuario) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("insert into usuario(nome, data_nascimento, genero, foto, email, nome_usuario, senha) values(?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, usuario.getNome());
            java.sql.Date sqlDate = new java.sql.Date(usuario.getDataNascimento().getTime());
            st.setDate(2, sqlDate);
            st.setString(3, usuario.getGenero());
            st.setString(4, usuario.getFoto());
            st.setString(5, usuario.getEmail());
            st.setString(6, usuario.getNomeUsuario());
            st.setString(7, usuario.getSenha());
            st.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
        
        return "cadastro realizado !";
    }

    public String atualizar(Usuario usuario) throws SQLException {
        PreparedStatement st = null;
//        int usuarioId = getUsuario(usuario.getEmail());

        try {
            st = conn.prepareStatement("update usuario set nome = ?, data_nascimento = ?, genero = ?, foto = ?, email = ?, nome_usuario = ?, senha = ? where id = ?");
            st.setString(1, usuario.getNome());
            java.sql.Date sqlDate = new java.sql.Date(usuario.getDataNascimento().getTime());
            st.setDate(2, sqlDate);
            st.setString(3, usuario.getGenero());
            st.setString(4, usuario.getFoto());
            st.setString(5, usuario.getEmail());
            st.setString(6, usuario.getNomeUsuario());
            st.setString(7, usuario.getSenha());
            st.setInt(8, usuario.getId());
            st.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }

        return "update realizado !";
    }

    public Usuario visualizar(int id) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("select * from usuario where id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNomeUsuario(rs.getString("nome_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setGenero(rs.getString("genero"));
                usuario.setDataNascimento(rs.getDate("data_nascimento"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setFoto(rs.getString("foto"));

                return usuario;
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
            st = conn.prepareStatement("delete from usuario where id = ?");
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


    public int getUsuario(String email) throws SQLException {
        ResultSet rs = null;
        PreparedStatement st = null;
        int usuarioId = -1;
        try {
            st = conn.prepareStatement("select id from usuario where email = ?");
            st.setString(1, email);
            st.executeUpdate();

            rs = st.executeQuery();

            if (rs.next()) {
                usuarioId = rs.getInt("id");
            }
            System.out.println(usuarioId);
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            BancoDados.finalizarResultSet(rs);
        }

        return usuarioId;
    }
}
