package controlador;

import java.util.LinkedList;
import database.DBLocal;
import database.DBServer;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logica.Usuario;

public class Controlador {
	
	//private Connection connection;
	private DBLocal database;
	private Stage stage;
	private BorderPane menuOpciones;
	private LinkedList<Scene> escenas;	
	private DBServer server;

	public Controlador(){ //acceso a base de datos/servidor web
		this.database = new DBLocal();
		this.server = new DBServer();
	}
	
	public Controlador(Stage s,BorderPane border){
		this.database = new DBLocal();
		this.stage = s;
		this.menuOpciones=border;
		this.server = new DBServer();
		//this.escenas = new LinkedList<Scene>();
	}
	
	public Usuario validaUsuario(String name,String pass){
		Usuario user = null;
		//user = this.database.getUser(name,pass);
		user = this.server.getUser(name, pass);
		return user;
	}
	
	public Initializable replaceSceneContent(String fxml) throws Exception {		
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Controlador.class.getResource(fxml));
        AnchorPane page = (AnchorPane) loader.load();
        
        this.menuOpciones.setCenter(page);
        return (Initializable) loader.getController();
    }
	
}
