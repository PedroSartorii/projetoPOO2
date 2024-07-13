import gui.Login;
import thread.AvisosThread;

public class Main {
	
	public static void main(String[] args) {
		
		Login login = new Login();
		login.run();
		
		AvisosThread aviso = new AvisosThread();
		aviso.run();
		
		
	}
	
}
