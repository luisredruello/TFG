package controlador;

import java.util.List;
import database.DBLocal;
import database.DBServer;
import logica.*;
import tools.Utilities;

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
		//return this.database.insertaUsuario(nombre, dni, pass, Utilities.getSystemDate());
		return this.server.insertaUsuario(nombre, dni, pass, Utilities.getSystemDate());
	}
	
	public int borraUsuario(String dni){
		return this.database.deleteUsuario(dni);
		//return this.server.deleteUsuario(dni);
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
		//return this.database.getModuloTeorico(nivel, id);
		return this.server.getModuloTeorico(nivel, id);
	}
	
	public List<ModuloTeorico> getListaModulosTeoricos(int nivel){
		//return this.database.getListModTeorico(nivel);
		return this.server.getListModTeorico(nivel);
	}

	public boolean existeUsuario(String dni) {
		return this.database.existeUsuario(dni);
		//return this.server.existeUsuario(dni);
	}
	
}
