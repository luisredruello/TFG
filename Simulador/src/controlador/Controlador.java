package controlador;

import java.sql.Connection;
import javax.sql.DataSource;


public class Controlador {
	
	private boolean probando;
	private int sumando;

	public Controlador(DataSource ds){ //acceso a base de datos/servidor web
		
	}
	
	public Controlador(Connection c){
		
	}
	
	public Controlador(){
		//Segunda prueba
		this.probando = true;
		this.sumando = 5;
	}
	
	public int seControla(){
		if (this.probando) {
			for (int i=0;i<10;i++){
				this.sumando+=i;
			}
		}
		else this.sumando*=2;
		return this.sumando;
	}

	public boolean getPrueba(){
		
		return probando; //PRUEBA 2.0
	}
}
