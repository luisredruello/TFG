package controlador;

import java.io.InputStream;
import javax.sql.DataSource;
import database.DAO;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
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
	
	public Controlador(){
		this.database = new DAO();
	}
	
	public Usuario validaUsuario(String name,String pass){
		Usuario user = null;
		user = this.database.getUser(name,pass);
		return user;
	}
	
	public Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Controlador.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Controlador.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        Scene scene = new Scene(page, 800, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
	
}
