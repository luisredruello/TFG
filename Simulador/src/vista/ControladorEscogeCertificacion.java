package vista;

import controlador.UserMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class ControladorEscogeCertificacion {

	
	@FXML 
	private Label etiquetaEncabezado; // tengo dudas de si hay que poner aqu� los Label
	
	@FXML 
	private Label etiquetaCertificacion;


	@FXML
	private Button botonC1;
	
	@FXML
	private Button botonC2;
	
	@FXML
	private Button botonC3;

		//private UserMain userMain;   no me acuerdo bien si realmente es necesario en todas las ventanas o s�lo en algunas
	
	@FXML //los m�todos de SceneBuilder tambi�n se marcan con etiqueta
	private void botonC1Action (ActionEvent event){  //AQU� HABR� QUE ACCEDER A LA BASE DE DATOS
		
		//accedo a otra pantalla
			
	}
	
	private void botonC2Action (ActionEvent event){  //AQU� HABR� QUE ACCEDER A LA BASE DE DATOS
		
		//accedo a otra pantalla
			
	}

	private void botonC3Action (ActionEvent event){  //AQU� HABR� QUE ACCEDER A LA BASE DE DATOS
	
	//accedo a otra pantalla
		
	}
	
	
	// habr�a que discutir si la clase padre es la del login, o esta? Ya que una vez logueado esta es la intefaz prinicpal del sistema
//	
//	
//	public interface ControlledScreen { // tengo que indiciar que esta es la ventana principal
//
//	     //This method will allow the injection of the Parent ScreenPane
//	     public void setScreenParent(ScreensController screenPage);
//	     //cada controlador tiene que implementar este m�todo para poder asociar cu�l es la interfaz "padre" a cada pantalla
//	  } 
//	
//	
//	// supongo que aqu� se debe implementar el manejador de pantallas
//	
//	public class ScreensController extends StackPane { }
//	
//	
	
}