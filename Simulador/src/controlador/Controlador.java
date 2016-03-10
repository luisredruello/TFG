package controlador;

import java.sql.Connection;
import javax.sql.DataSource;

boolean probando = false;
boolean probando2 = false;

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

	public boolean getPrueba(){
		
		return probando; //PRUEBA 2.0
	}
}
