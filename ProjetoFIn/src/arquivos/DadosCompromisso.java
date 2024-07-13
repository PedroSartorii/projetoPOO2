package arquivos;

import entities.Compromisso;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DadosCompromisso {

    public static List<Compromisso> carregarCompromissosDoUsuario() {
      
        List<Compromisso> compromissos = new ArrayList<>();

        
        
        return compromissos;
    }

    public static void main(String[] args) {
        List<Compromisso> compromissos = carregarCompromissosDoUsuario();
        compromissos.forEach(c -> System.out.println(c.getDataHoraInicio() + " " + c.getDataHoraNotificacao() + " " + c.getDescricao()));
    }
}
