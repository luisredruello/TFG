package vista;

import java.net.URL;
import java.util.ResourceBundle;
import controlador.UserMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;


public class ControladorEscogeCertificacion implements Initializable { //esta clase puede hacer lo de ScreensController?

	
	public static final String MAIN_SCREEN = null; //revisar esto

	@FXML 
	private Label etiquetaEncabezado; // tengo dudas de si hay que poner aquí los Label
	
	@FXML 
	private Label etiquetaCertificacion;


	@FXML
	private Button botonC1;
	
	@FXML
	private Button botonC2;
	
	@FXML
	private Button botonC3;

	// private UserMain user;   no me acuerdo bien si realmente es necesario en todas las ventanas o sólo en algunas
	
	@FXML //los métodos de SceneBuilder también se marcan con etiqueta
	private void botonC1Action (ActionEvent event){  //AQUÍ HABRÁ QUE ACCEDER A LA BASE DE DATOS
		
//		try{
//			
//			if (user!=null){
//				ControladorEscogeCertificacion c1 = (ControladorEscogeCertificacion) this.userMain
//						.getControlador()
//						.replaceSceneContent("/vista/C1.fxml");
//				c1.setApp(this.userMain);
//			}
//			else {
//				alert.setHeaderText("Error");
//				alert.setContentText("No existe el usuario "+txtUsuario.getText());
//				alert.showAndWait();
//			}
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
	}
	
	@FXML
	private void botonC2Action (ActionEvent event){  //AQUÍ HABRÁ QUE ACCEDER A LA BASE DE DATOS
		
		//accedo a otra pantalla
			
	}
	
	@FXML
	private void botonC3Action (ActionEvent event){  //AQUÍ HABRÁ QUE ACCEDER A LA BASE DE DATOS
	
	//accedo a otra pantalla
		
	}

	public void setApp(UserMain userMain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	// habría que discutir si la clase padre es la del login, o esta? Ya que una vez logueado esta es la intefaz prinicpal del sistema
//	
//	
//	public interface ControlledScreen { // tengo que indiciar que esta es la ventana principal
//
//	     //This method will allow the injection of the Parent ScreenPane
//	     public void setScreenParent(ScreensController screenPage);
//	     //cada controlador tiene que implementar este método para poder asociar cuál es la interfaz "padre" a cada pantalla
//	  } 
//	
//	
//	// supongo que aquí se debe implementar el manejador de pantallas
//	
//	public class ScreensController extends StackPane { }
//	
//	
	
}
