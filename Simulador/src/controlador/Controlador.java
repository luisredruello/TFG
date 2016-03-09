package controlador;

import java.sql.Connection;
import javax.sql.DataSource;


public class Controlador {
	
	private boolean probando;

	public Controlador(DataSource ds){ //acceso a base de datos/servidor web
		
	}
	
	public Controlador(Connection c){
		
	}
	
	public Controlador(){
		//Segunda prueba
		this.probando = true;
	}

}
