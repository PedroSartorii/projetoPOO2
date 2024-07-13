package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import database.BancoDados;
import entities.Compromisso;
import entities.Usuario;

public class CompromissoDAO {
    private Connection conn;

    public CompromissoDAO(Connection conn) {
        this.conn = conn;
    }

    public String cadastrar(Compromisso compromisso) throws SQLException {
        PreparedStatement st = null;
        System.out.println(compromisso.getAgendaId() + "algo");
        
        System.out.println("DataHoraInicio:"+ compromisso.getDataHoraInicio());
        System.out.println("DataHoraNotificacao:"+ compromisso.getDataHoraNotificacao());
        System.out.println("DataHoraTermino:"+ compromisso.getDataHoraTermino());
        try {
            st = conn.prepareStatement("insert into compromisso(titulo, descricao, data_hora_inicio, data_hora_fim, local, agenda_id, convidados, data_hora_notificacao) values(?, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, compromisso.getTitulo());
            st.setString(2, compromisso.getDescricao());
            st.setTimestamp(3, Timestamp.valueOf(compromisso.getDataHoraInicio()));
            st.setTimestamp(4, Timestamp.valueOf(compromisso.getDataHoraTermino()));
            st.setString(5, compromisso.getLocal());
            st.setInt(6, compromisso.getAgendaId());
            st.setString(7, compromisso.getPessoasConvidadas());
            st.setTimestamp(8, Timestamp.valueOf(compromisso.getDataHoraNotificacao()));

            st.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }

        return "cadastro  de compromisso realizado !";
    }

    public Compromisso visualizar(int id) throws SQLException,IOException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("select * from compromisso where id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()) {
                Compromisso compromisso = new Compromisso();
                compromisso.setId(rs.getInt("id"));
                compromisso.setTitulo(rs.getString("titulo"));
                compromisso.setDescricao(rs.getString("descricao"));
                compromisso.setPessoasConvidadas(rs.getString("convidados"));
                compromisso.setLocal(rs.getString("local"));
                compromisso.setDataHoraInicio(rs.getTimestamp("data_hora_inicio").toLocalDateTime());
                compromisso.setDataHoraTermino(rs.getTimestamp("data_hora_fim").toLocalDateTime());
                compromisso.setDataHoraNotificacao(rs.getTimestamp("data_hora_notificacao").toLocalDateTime());
                compromisso.setAgendaId(rs.getInt("agenda_id"));

                return compromisso;
            }

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }

        return null;
    }

    public String atualizar(Compromisso compromisso) throws SQLException {
        PreparedStatement st = null;
        try {
        	System.out.println(compromisso.getTitulo());
        	System.out.println(compromisso.getDescricao());
        	System.out.println(compromisso.getDataHoraInicio());
        	System.out.println(compromisso.getDataHoraTermino());
        	System.out.println(compromisso.getLocal());
        	System.out.println(compromisso.getAgendaId());
        	System.out.println(compromisso.getPessoasConvidadas());
        	System.out.println(compromisso.getDataHoraNotificacao());
        	System.out.println(compromisso.getId());
            st = conn.prepareStatement("update compromisso set titulo = ?, descricao = ?, data_hora_inicio = ?, data_hora_fim = ?, local = ?, agenda_id = ?, convidados = ?, data_hora_notificacao = ? where id = ?");
            st.setString(1, compromisso.getTitulo());
            st.setString(2, compromisso.getDescricao());
            st.setTimestamp(3, Timestamp.valueOf(compromisso.getDataHoraInicio()));
            st.setTimestamp(4, Timestamp.valueOf(compromisso.getDataHoraTermino()));
            st.setString(5, compromisso.getLocal());
            st.setInt(6, compromisso.getAgendaId());
            st.setString(7, compromisso.getPessoasConvidadas());
            st.setTimestamp(8, Timestamp.valueOf(compromisso.getDataHoraNotificacao()));
            st.setInt(9, compromisso.getId());

            st.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }

        return "update de compromisso realizado !";
    }

    public String deletar(int id) throws  SQLException {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("delete from compromisso where id = ?");
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
    
    public List<LocalDateTime> getAllDataHoraInicio() throws SQLException, IOException {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<LocalDateTime> dataHoraInicioList = new ArrayList<>();

        try {
            st = conn.prepareStatement("SELECT data_hora_notificacao FROM compromisso");
            rs = st.executeQuery();

            while (rs.next()) {
                LocalDateTime dataHoraInicio = rs.getTimestamp("data_hora_notificacao").toLocalDateTime();
                dataHoraInicioList.add(dataHoraInicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.desconectar();
        }

        return dataHoraInicioList;
    }
}
