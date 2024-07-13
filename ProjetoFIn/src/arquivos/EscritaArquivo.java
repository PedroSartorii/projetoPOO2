package arquivos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import entities.Compromisso;

public class EscritaArquivo {

	public static void exportarCompromissos(List<Compromisso> compromissos, String caminhoArquivo) {
        try (BufferedWriter documento = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            // Escreve cabeçalho do CSV
            documento.write("Data,Hora,Descrição");
            documento.newLine();

            // Escreve cada compromisso no arquivo CSV
            for (Compromisso compromisso : compromissos) {
                String linha = compromisso.getDataHoraInicio() + "," + compromisso.getDataHoraNotificacao() + "," + compromisso.getDescricao();
                documento.write(linha);
                documento.newLine();
            }

            System.out.println("Compromissos exportados para: " + caminhoArquivo);

        } catch (IOException e) {
            System.out.println("Erro ao exportar os compromissos: " + e.getMessage());
        }
    }
}

