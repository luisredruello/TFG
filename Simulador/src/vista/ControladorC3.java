package vista;

import vista.ControladorEscogeCertificacion.ControlledScreen;
import controlador.UserMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class ControladorC3 Initializable, ControlledScreen{
	
	@FXML 
	private Label etiquetaBienvenidaC3; // tengo dudas de si hay que poner aqu� los Label y de si se pueden usar los mismos nombres de variables
	
	@FXML 
	private Label eligeC3;


	@FXML
	private Button botonC3Teoria;
	
	@FXML
	private Button botonC3Practica;
	
	
	

		//private UserMain userMain;   no me acuerdo bien si realmente es necesario en todas las ventanas o s�lo en algunas
	
	@FXML //los m�todos de SceneBuilder tambi�n se marcan con etiqueta
	private void botonC3TeoriaAction (ActionEvent event){  //AQU� HABR� QUE ACCEDER A LA BASE DE DATOS
		
		//accedo a otra pantalla
			
	}
	
	private void botonC3PracticaAction (ActionEvent event){  //AQU� HABR� QUE ACCEDER A LA BASE DE DATOS
		
		//accedo a otra pantalla
			
	}

	
	
//	
//	public class Screen1Controller implements Initializable, ControlledScreen { // con este m�todo cada controlador tiene la referencia de su padre
//
//		ScreensController myController;
//
//		@Override
//			public void initialize(URL url, ResourceBundle rb) {
//				// TODO
//			}
//
//			public void setScreenParent(ScreensController screenParent){
//				myController = screenParent;
//			}
//
//			//any required method here
//	}

	 @Override
     public void initialize(URL url, ResourceBundle rb) {
         // TODO
     } 
	
	public void setScreenParent(ScreensController ControladorEscogeCertificacion){  //le paso por par�metro el controlador de la interfaz padre
        myController = ControladorEscogeCertificacion;
     } 
	
	@FXML
	   private void goToMain(ActionEvent event){
	     myController.setScreen(ScreensFramework.MAIN_SCREEN);
	   } 

}
