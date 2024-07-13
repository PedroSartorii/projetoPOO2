package thread;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dao.CompromissoDAO;
import dao.UsuarioDAO;
import database.BancoDados;

public class AvisosThread extends Thread{
	
	private LocalTime horaLocal;

	public AvisosThread() {
		
	}
	
    public AvisosThread(LocalTime targetTime) {
        this.horaLocal = targetTime;
    }
    
    
    


@Override
    public void run() {
	try {
		Connection conn = BancoDados.conectar();
		List<LocalDateTime> horarios =  new  CompromissoDAO(conn).getAllDataHoraInicio();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String horaMinuto = horarios.get(0).format(formatter);
		System.out.println(horaMinuto);
		String[] partes = horaMinuto.split(":");
	    String hora = partes[0];
	    String minuto = partes[1];
	    
	    System.out.println("Hora: " + hora);
	    System.out.println("Minuto: " + minuto);
	    
	    int horaInt = Integer.parseInt(hora);
	    int minutoInt = Integer.parseInt(minuto);

		LocalTime horaLocal = LocalTime.of(horaInt, minutoInt);
	    AvisosThread alertThread = new AvisosThread(horaLocal);
	    alertThread.start();
	}catch(Exception e){
		System.out.println();
	}
	
        while (true) {
   
            LocalTime now = LocalTime.now();
      
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = now.format(formatter2);
            
            System.out.println("Formatted Time: " + formattedTime);
            
            // Separar a string em hora e minuto
            String[] partes1 = formattedTime.split(":");
            int hora1 = Integer.parseInt(partes1[0]);
            int minuto1 = Integer.parseInt(partes1[1]);
            LocalTime horaLocal2 = LocalTime.of(hora1, minuto1);
            System.out.println("NOW: " + horaLocal2);
            System.out.println("LOCAL: " + horaLocal);
            if (horaLocal2.equals(horaLocal)) {
                alertUser();
                break;
            }
            try {

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
	private void alertUser() {
        System.out.println("Alerta de compromisso!");
    }

    
}
