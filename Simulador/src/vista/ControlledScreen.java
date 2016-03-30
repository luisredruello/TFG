package vista;

public interface ControlledScreen {
	
	  //This method will allow the injection of the Parent ScreenPane
    public void setScreenParent(ScreensController screenPage);
    //cada controlador tiene que implementar este método para poder asociar cuál es la interfaz "padre" a cada pantalla
 

    
    //debería ser método de la interfaz?
//	@FXML   
//	   private void goToMain(ActionEvent event){
//	     myController.setScreen(ScreensFramework.MAIN_SCREEN);
//	   } 

}
