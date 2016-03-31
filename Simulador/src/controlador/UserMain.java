package controlador;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import vista.ControladorLoginUsuario;
/*import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.Usuario;*/

public class UserMain extends Application {

	
	 private Stage primaryStage;  //escenario, es el primer marco
	 private BorderPane rootLayout;
	 private Controlador controlador;
	 
	 @Override
	 public void start(Stage primaryStage) throws Exception {
		 this.primaryStage = primaryStage;
		 this.primaryStage.setTitle("Simulador");
		 
		 this.controlador = new Controlador(this.primaryStage);

		 initRootLayout();

		 showLoginView(this.controlador);
			
	}
	
	 /**
     * usamos datos que son una lista de Usuarios.
     */
    //private ObservableList<Usuario> datosUsuario = FXCollections.observableArrayList();

	
	 public UserMain() {
	        // datos de prueba que luego van a estar en la base de datos
		 /*datosUsuario.add(new Usuario("Hans", "00001"));
		 datosUsuario.add(new Usuario("Ruth", "00002"));
		 datosUsuario.add(new Usuario("Heinz", "00003"));
		 datosUsuario.add(new Usuario("Cornelia", "00004"));
		 datosUsuario.add(new Usuario("Werner", "00005"));
		 datosUsuario.add(new Usuario("Lydia", "00006"));
		 datosUsuario.add(new Usuario("Anna", "00007"));
		 datosUsuario.add(new Usuario("Stefan", "00008"));
		 datosUsuario.add(new Usuario("Martin", "00009"));*/
	    }
	
	 /**
	  * Initializes the root layout.
	  */
	 public void initRootLayout() {
		 try {
	         //  Cargas el layout principal  del archivo fxml
	         FXMLLoader loader = new FXMLLoader();
	         loader.setLocation(UserMain.class.getResource("/vista/RootLayout.fxml"));
	         rootLayout = (BorderPane) loader.load();
	            
	         // Show the scene containing the root layout.
	         Scene scene = new Scene(rootLayout);
	         primaryStage.setScene(scene);
	         primaryStage.show();
	     }
		 catch (IOException e) {
	         e.printStackTrace();
	     }
	 }
	 

	   //si hubiera otro escenario dentro del root layout
	 public void showLoginView(Controlador c) {
		 try {
	         // Load person overview.
	         FXMLLoader loader = new FXMLLoader();
	         loader.setLocation(UserMain.class.getResource("/vista/LoginUsuario.fxml"));
	         AnchorPane loginOverview = (AnchorPane) loader.load();

	         // Set person overview into the center of root layout.
	         rootLayout.setCenter(loginOverview);

	         // Give the controller access to the main app.
	         ControladorLoginUsuario controller = loader.getController();
	            controller.setMainApp(this);

	     } catch (IOException e) {
	          e.printStackTrace();
	     }
	 }
	 
	 	 
	 public Stage getPrimaryStage() {
		return primaryStage;
	}

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		Application.launch(args);
	}

}
