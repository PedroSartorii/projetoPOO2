package dao;

import database.BancoDados;
import entities.Compromisso;
import entities.Convite;

import java.io.IOException;
import java.sql.*;

public class ConviteDAO {
    private Connection conn;

    public ConviteDAO(Connection conn) {
        this.conn = conn;
    }

    public String cadastrar(Convite convite) throws SQLException {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("insert into convites(convidante_id, convidado_id, aceito, compromisso_id) values(?, ?, ?, ?)");
            st.setInt(1, convite.getConvidanteId());
            st.setInt(2, convite.getConvidadoId());
            st.setBoolean(3, convite.isAceito());
            st.setInt(4, convite.getCompromissoId());

            st.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            return "Não foi possivel completar o cadastro de convite.";
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
        return "Cadastro de convite realizado com sucesso";
    }

    public String atualizar(Convite convite) throws SQLException {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("update convites set convidante_id = ?, convidado_id = ?, aceito = ?, compromisso_id = ? where id = ?");
            st.setInt(1, convite.getConvidanteId());
            st.setInt(2, convite.getConvidadoId());
            st.setBoolean(3, convite.isAceito());
            st.setInt(4, convite.getCompromissoId());
            st.setInt(5, convite.getId());

            st.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            return "Não foi possivel completar o update de convite.";
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
        return "Update de convite realizado com sucesso";
    }

    public Convite visualizar(int id) throws SQLException, IOException {
        PreparedStatement st = null;
        ResultSet rs = null;
        System.out.println("--");
        
        try {
            st = conn.prepareStatement(
            		"SELECT c.id AS convite_id, cp.id AS compromisso_id, cp.titulo, cp.descricao, " +
            				"cp.data_hora_inicio, cp.data_hora_fim, cp.local, cp.convidados, cp.data_hora_notificacao, " +
            				"u_convidante.nome AS nome_convidante, u_convidado.nome AS nome_convidado, " + 
            				"c.aceito AS isAceito " +
            				"FROM convites c " +
            				"LEFT JOIN compromisso cp ON c.compromisso_id = cp.id " +
            				"LEFT JOIN usuario u_convidante ON c.convidante_id = u_convidante.id " +
            				"LEFT JOIN usuario u_convidado ON c.convidado_id = u_convidado.id " +
            				"WHERE c.id = ?"
            );
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Convite convite = new Convite();
                convite.setId(rs.getInt("convite_id"));
                convite.setAceito(rs.getBoolean("isAceito"));
                

                int compromissoId = rs.getInt("compromisso_id");
                if (!rs.wasNull()) {
                    Compromisso compromisso = new Compromisso();
                    compromisso.setId(compromissoId);
                    compromisso.setTitulo(rs.getString("titulo"));
                    compromisso.setDescricao(rs.getString("descricao"));
                    compromisso.setDataHoraInicio(rs.getTimestamp("data_hora_inicio").toLocalDateTime());
                    compromisso.setDataHoraTermino(rs.getTimestamp("data_hora_fim").toLocalDateTime());
                    compromisso.setLocal(rs.getString("local"));
                    compromisso.setPessoasConvidadas(rs.getString("convidados"));
                    compromisso.setDataHoraNotificacao(rs.getTimestamp("data_hora_notificacao").toLocalDateTime());

                    convite.setCompromisso(compromisso);
                }

                convite.setNomeConvidante(rs.getString("nome_convidante"));
                convite.setNomeConvidado(rs.getString("nome_convidado"));
                return convite;
            }

        } catch (SQLException e) {
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
            st = conn.prepareStatement("delete from convites where id = ?");
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

    public String aceitarConvite(int id) throws SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("update convites set aceito = true where id = ?");
            st.setInt(1, id);
            int linhasManipuladas = st.executeUpdate();
            if (linhasManipuladas != 0) {
                return "Convite aceito com sucesso";
            }

        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }
        return "Erro ao tentar aceitar o convite";
    }

}
