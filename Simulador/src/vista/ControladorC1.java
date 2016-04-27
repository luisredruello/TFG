package vista;

import java.util.ResourceBundle;
import javax.print.DocFlavor.URL;
import controlador.Controlador;
//import vista.ControladorEscogeCertificacion.ControlledScreen;
import controlador.UserMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class ControladorC1 implements Initializable, ControlledScreen {

	@FXML 
	private Label etiquetaBienvenidaC1; // tengo dudas de si hay que poner aquí los Label y de si se pueden usar los mismos nombres de variables
	
	@FXML 
	private Label eligeC1;
	
	@FXML
	private Button atras;

	@FXML
	private Button botonC1Teoria;
	
	@FXML
	private Button botonC1Examen;
	
	private Controlador control;
	
	ScreensController myController;// para moverme por las pantallas
	

		//private UserMain userMain;   no me acuerdo bien si realmente es necesario en todas las ventanas o sólo en algunas
	
	@FXML //los métodos de SceneBuilder también se marcan con etiqueta
	private void botonC1TeoriaAction (ActionEvent event){  //AQUÍ HABRÁ QUE ACCEDER A LA BASE DE DATOS
		
		try{
			ControladorTeoria ct = (ControladorTeoria) this.control.replaceSceneContent("/vista/Teoria.fxml");
			ct.setControl(control);
		}
		catch(Exception e){
			e.printStackTrace();
		}
			
	}
	
	@FXML //los métodos de SceneBuilder también se marcan con etiqueta
	private void botonC1ExamenAction (ActionEvent event){
		
	}
	
	@FXML
	private void goToCertification (ActionEvent event){
		try{
			ControladorEscogeCertificacion cEC = (ControladorEscogeCertificacion) this.control
					.replaceSceneContent("/vista/EscogeCertificacion.fxml");
			cEC.setControl(control);
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	
//	public class Screen1Controller implements Initializable, ControlledScreen { // con este método cada controlador tiene la referencia de su padre
//
//		
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
	
	public void setScreenParent(ScreensController ScreenParent){  //le paso por parámetro el controlador de la interfaz padre
        myController = ScreenParent;
     } 
	
	@FXML
	   private void goToMain(ActionEvent event){
	     myController.setScreen(ScreensFramework.MAIN_SCREEN);
	   }


	public void setApp(UserMain userMain) {
		// TODO Auto-generated method stub
		
	}


	public void setControl(Controlador control) {
		this.control = control;
	}

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	} 
}
