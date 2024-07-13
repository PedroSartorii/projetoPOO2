package arquivos;

import javax.swing.JFileChooser;

public class SalvarArquivos{

	public static void main(String[] args) {
		
		JFileChooser seletor = new JFileChooser();
		int valorRetorno = seletor.showSaveDialog(null);
	}
}