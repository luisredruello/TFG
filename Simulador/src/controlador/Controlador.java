package controlador;

import java.util.List;
import database.DBLocal;
import database.DBServer;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logica.Certificacion;
import logica.Usuario;

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
		user = this.database.getUser(name,pass);
		//user = this.server.getUser(name, pass);
		return user;
	}
	
	public List<Usuario> dameListaUsuarios(){
		return this.database.getUserList();
		//return this.server.getUserList();
	}
	
	public List<Certificacion> getListaCertificados(){
		//return this.database.getCertificados();
		return this.server.getCertificados();
	}
	
}
