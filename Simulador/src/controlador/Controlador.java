package controlador;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import database.DBLocal;
import database.DBServer;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logica.*;

public class Controlador {
	
	//private Connection connection;
	private DBLocal database;	
	private DBServer server;

	public Controlador(){ //acceso a base de datos/servidor web
		this.database = new DBLocal();
		this.server = new DBServer();
	}
	
	public Controlador(Stage s,BorderPane border){
		this.database = new DBLocal();
		this.server = new DBServer();
	}
	
	public Usuario validaUsuario(String name,String pass){
		Usuario user = null;
		//user = this.database.getUser(name,pass);
		user = this.server.getUser(name, pass);
		return user;
	}
	
	public int insertaUsuario(String nombre, String dni, String pass){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String fecha = dateFormat.format(cal.getTime());
		java.util.Date date = null;
		
		try{
			date = dateFormat.parse(fecha);			
		}
		catch(ParseException p){
			p.printStackTrace();
		}
		
		java.sql.Date fechaInsert = new java.sql.Date(date.getTime());
		//return this.database.insertaUsuario(nombre, dni, pass, fechaInsert);
		return this.server.insertaUsuario(nombre, dni, pass, fechaInsert);
	}
	
	public List<Usuario> dameListaUsuarios(){
		//return this.database.getUserList();
		return this.server.getUserList();
	}
	
	public List<Certificacion> getListaCertificados(){
		//return this.database.getCertificados();
		return this.server.getCertificados();
	}
	
	public ExamenPractico getExamenPractico(int l){
		//return this.database.getExamenPractico(l);
		return this.server.getExamenPractico(l);
	}
	
	public ExamenTeorico getExamenTeorico(int l){
		//return this.database.getExamenTeorico(l);
		return this.server.getExamenTeorico(l);
	}
	
}
