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
		//user = this.database.getUser(dni,Utilities.md5(pass));
		user = this.server.getUser(dni, Utilities.md5(pass));
		return user;
	}
	
	public int insertaUsuario(String nombre, String dni, String pass){
		//return this.database.insertaUsuario(nombre, dni, Utilities.md5(pass), Utilities.getSystemDate());
		return this.server.insertaUsuario(nombre, dni, Utilities.md5(pass), Utilities.getSystemDate());
	}
	
	public int borraUsuario(String dni){
		//return this.database.deleteUsuario(dni);
		return this.server.deleteUsuario(dni);
	}
	
	public int actualizaUsuario(Usuario u,String n,String p){
		u.setNombre_completo(n);
		u.setPass(Utilities.md5(p));
		//return this.database.updateUsuario(u);
		return this.server.updateUsuario(u);
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
	
	public String getPDF(int nivel, int modulo){
		return this.server.getPDFTeorico(nivel,modulo);
	}
	
	public int subeTeoria(int level, int idmodulo, byte[] files){
		int id = idmodulo+1;
		return this.database.uploadPDFTeorico(level, id, files);
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
		//return this.database.existeUsuario(dni);
		return this.server.existeUsuario(dni);
	}
	
	/**
	 * Devuelve el numero de certificaciones conseguidas por el alumnos
	 * @param dni del alumno
	 * @return numero de certificaciones o 0 si no ha conseguido ninguna aún
	 */
	public int numCertificaciones(String dni) {
		List<Integer> l = this.database.getCertificadosFromUser(dni);
		return (l.size()>0)?l.size():0;
	}
	
}
