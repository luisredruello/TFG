package vista;

import java.awt.Desktop;
import java.io.File;
import java.util.ResourceBundle;
import javax.print.DocFlavor.URL;
import controlador.Controlador;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	
	String address = " ";
	

	final ComboBox emailComboBox = new ComboBox();
	
	private void recargaCombo () {
		emailComboBox.getItems().addAll(
	            "Modulo 1",
	            "Modulo 2",
	            "Modulo 3",
	            "Modulo 4",
	            "Modulo 5"  
	        );
	        emailComboBox.setPromptText("Elige un módulo");
	        emailComboBox.setEditable(true);        
	        emailComboBox.valueProperty().addListener(new ChangeListener<String>() {
	        	@Override 
	            public void changed(ObservableValue ov, String t, String t1) {                
	                address = t1;                
	            }   
	        });
	        
	      
	     
	        
	       
	}
	
	
	
	@FXML
	private void botonTeoriaAction (ActionEvent event){  //AQUÍ HABRÁ QUE ACCEDER A LA BASE DE DATOS
		
		 botonTeoria.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	                if (emailComboBox.getValue() != null && 
	                    !emailComboBox.getValue().toString().isEmpty()){
	                	
	                	if (emailComboBox.getValue() == ("Modulo 1")){
	                		cargaPdf ("C:\\Modulo1.pdf");
	                	}
	                	else if (emailComboBox.getValue() == ("Modulo 2")){
	                		cargaPdf ("C:\\Modulo2.pdf");
	                	}
	                	else if (emailComboBox.getValue() == ("Modulo 3")){
	                		cargaPdf ("C:\\Modulo3.pdf");
	                	}
	                	else if (emailComboBox.getValue() == ("Modulo 4")){
	                		cargaPdf ("C:\\Modulo4.pdf");
	                	}
	                	else if (emailComboBox.getValue() == ("Modulo 5")){
	                		cargaPdf ("C:\\Modulo5.pdf");
	                	}
	                			
	            	
	                }
	                else {
	                    
	                }
	            }
	        });
					
	}
	
private void cargaPdf (String url) {
    	
    	try {

    		
			File pdfFile = new File(url);
			if (pdfFile.exists()) {

				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(pdfFile);
				} else {
					System.out.println("Awt Desktop is not supported!");
				}

			} else {
				System.out.println("File is not exists!");
			}

			System.out.println("Done");

		  } catch (Exception ex) {
			ex.printStackTrace();
		  }

			
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
