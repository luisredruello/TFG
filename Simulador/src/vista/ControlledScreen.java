package vista;

import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

public interface ControlledScreen {
	
	  //This method will allow the injection of the Parent ScreenPane
    public void setScreenParent(ScreensController screenPage);
    //cada controlador tiene que implementar este método para poder asociar cuál es la interfaz "padre" a cada pantalla

	void initialize(URL url, ResourceBundle rb);
 

    
    //debería ser método de la interfaz?
//	@FXML   
//	   private void goToMain(ActionEvent event){
//	     myController.setScreen(ScreensFramework.MAIN_SCREEN);
//	   } 

}
