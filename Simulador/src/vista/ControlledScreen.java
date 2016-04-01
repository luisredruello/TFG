package vista;

import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

public interface ControlledScreen { //cada controlador de la interfaz es un controller screen?
	
	  //This method will allow the injection of the Parent ScreenPane
    public void setScreenParent(ScreensController screenPage);
    //cada controlador tiene que implementar este m�todo para poder asociar cu�l es la interfaz "padre" a cada pantalla

	void initialize(URL url, ResourceBundle rb);
 

    
    //deber�a ser m�todo de la interfaz?
//	@FXML   
//	   private void goToMain(ActionEvent event){
//	     myController.setScreen(ScreensFramework.MAIN_SCREEN);
//	   } 

}
