package controlador;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import database.DBLocal;
import database.DBServer;
import logica.*;

public class Controlador {
	
	private DBLocal database;	
	private DBServer server;

	public Controlador(){ //acceso a base de datos/servidor web
		this.database = new DBLocal();
		this.server = new DBServer();
	}
	
	public Usuario validaUsuario(String dni,String pass){
		Usuario user = null;
		//user = this.database.getUser(dni,pass);
		user = this.server.getUser(dni, pass);
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
	
	public String getTeoria(int nivel, int modulo){
		return this.server.getPDFTeorico(nivel,modulo);
	}
	
	public ModuloTeorico getModuloTeorico(int nivel, int id){
		return this.database.getModuloTeorico(nivel, id);
		//return this.server.getModuloTeorico(nivel, id);
	}
	
	public List<ModuloTeorico> getListaModulosTeoricos(int nivel){
		return this.database.getListModTeorico(nivel);
		//return this.server.getListModTeorico(nivel);
	}
	
}
