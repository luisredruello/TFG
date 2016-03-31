package controlador;

import javax.sql.DataSource;
import database.DAO;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logica.Usuario;

public class Controlador {
	
	//private Connection connection;
	private DAO database;
	private Stage stage;

	public Controlador(DataSource ds){ //acceso a base de datos/servidor web
		this.database = new DAO();
	}
	
	public Controlador(Stage s){
		this.database = new DAO();
		this.stage = s;
	}
	
	public Usuario validaUsuario(String name,String pass){
		Usuario user = null;
		user = this.database.getUser(name,pass);
		return user;
	}
	
	public Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Controlador.class.getResource(fxml));
        AnchorPane page = (AnchorPane) loader.load();
        
        Scene scene = new Scene(page, 560, 360);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
	
}
