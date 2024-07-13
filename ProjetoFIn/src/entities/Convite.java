package entities;

public class Convite {
    private int id;
    private int convidadoId;
    private int convidanteId;
    private boolean aceito;
    private int compromissoId;
    private Compromisso compromisso;
    private String nomeConvidante;
    private String nomeConvidado;


    public Convite() {
    }

    public Convite(int id, int convidadoId, int convidanteId, boolean aceito, int compromissoId) {
        this.id = id;
        this.convidadoId = convidadoId;
        this.convidanteId = convidanteId;
        this.aceito = aceito;
        this.compromissoId = compromissoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConvidadoId() {
        return convidadoId;
    }

    public void setConvidadoId(int convidadoId) {
        this.convidadoId = convidadoId;
    }

    public int getConvidanteId() {
        return convidanteId;
    }

    public void setConvidanteId(int convidanteId) {
        this.convidanteId = convidanteId;
    }

    public boolean isAceito() {
        return aceito;
    }

    public void setAceito(boolean aceito) {
        this.aceito = aceito;
    }

    public int getCompromissoId() {
        return compromissoId;
    }

    public void setCompromissoId(int compromissoId) {
        this.compromissoId = compromissoId;
    }

    public Compromisso getCompromisso() {
        return compromisso;
    }

    public void setCompromisso(Compromisso compromisso) {
        this.compromisso = compromisso;
    }

    public String getNomeConvidante() {
        return nomeConvidante;
    }

    public void setNomeConvidante(String nomeConvidante) {
        this.nomeConvidante = nomeConvidante;
    }

    public String getNomeConvidado() {
        return nomeConvidado;
    }

    public void setNomeConvidado(String nomeConvidado) {
        this.nomeConvidado = nomeConvidado;
    }
}
