package vista;

public interface ControlledScreen {
	
	  //This method will allow the injection of the Parent ScreenPane
    public void setScreenParent(ScreensController screenPage);
    //cada controlador tiene que implementar este m�todo para poder asociar cu�l es la interfaz "padre" a cada pantalla
 

    
    //deber�a ser m�todo de la interfaz?
//	@FXML   
//	   private void goToMain(ActionEvent event){
//	     myController.setScreen(ScreensFramework.MAIN_SCREEN);
//	   } 

}
