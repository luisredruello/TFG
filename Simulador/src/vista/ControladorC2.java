package vista;

import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

//import vista.ControladorEscogeCertificacion.ControlledScreen;
import controlador.UserMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class ControladorC2 implements Initializable, ControlledScreen{
	
	
	@FXML 
	private Label etiquetaBienvenidaC2; // tengo dudas de si hay que poner aquí los Label y de si se pueden usar los mismos nombres de variables
	
	@FXML 
	private Label eligeC2;


	@FXML
	private Button botonC2Teoria;
	
	@FXML
	private Button botonC2Practica;
	
	
	ScreensController myController;// para moverme por las pantallas
	

		//private UserMain userMain;   no me acuerdo bien si realmente es necesario en todas las ventanas o sólo en algunas
	
	@FXML //los métodos de SceneBuilder también se marcan con etiqueta
	private void botonC2TeoriaAction (ActionEvent event){  //AQUÍ HABRÁ QUE ACCEDER A LA BASE DE DATOS
		
		//accedo a otra pantalla
			
	}
	
	@FXML
	private void botonC2PracticaAction (ActionEvent event){  //AQUÍ HABRÁ QUE ACCEDER A LA BASE DE DATOS
		
		//accedo a otra pantalla
			
	}

	
	
	
//	public class Screen1Controller implements Initializable, ControlledScreen { // con este método cada controlador tiene la referencia de su padre
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
	
	public void setScreenParent(ScreensController ControladorEscogeCertificacion){  //le paso por parámetro el controlador de la interfaz padre
        myController = ControladorEscogeCertificacion;
     } 
	
	@FXML
	   private void goToMain(ActionEvent event){
	     
		//myController.setScreen(ScreensFramework.MAIN_SCREEN);
	   } 

}
