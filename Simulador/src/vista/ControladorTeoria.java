package vista;

import java.util.ResourceBundle;
import javax.print.DocFlavor.URL;
import controlador.Controlador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ControladorTeoria implements Initializable, ControlledScreen {

	@FXML 
	private Label etiquetaBienvenidaC1; // tengo dudas de si hay que poner aquí los Label y de si se pueden usar los mismos nombres de variables
	
	@FXML 
	private Label eligeC1;
	
	@FXML
	private Button botonTeoria;
	
	private Controlador control;
	
//	ObservableList<String> options = FXCollections.observableArrayList(
//		        "Option 1",
//		        "Option 2",
//		        "Option 3"
//		    );
	@FXML
		private ComboBox<String> eligeModulo;
	
	//eligeModulo.getItems().addAll("Option4", "Option5", "Option6");
	
	@FXML
	private void botonTeoriaAction (ActionEvent event){  //AQUÍ HABRÁ QUE ACCEDER A LA BASE DE DATOS
		
		//accedo a otra pantalla
			
	}
	
	
	
	
	@Override
	public void setScreenParent(ScreensController screenPage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}




	public void setControl(Controlador control) {
		
			this.control = control;
		
		
	}

}
