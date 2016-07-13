package javafx;

import java.awt.Desktop;
import java.io.File;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ComboBoxSample extends Application{

	public static void main(String[] args) {
        launch(args);
    }
    
    final Button button = new Button ("Send");
   
    
    String address = " ";
    
    @Override public void start(Stage stage) {
        stage.setTitle("ComboBoxSample");
        Scene scene = new Scene(new Group(), 450, 250);
        
        final ComboBox emailComboBox = new ComboBox();
        emailComboBox.getItems().addAll(
            "Modulo 1",
            "Modulo 2",
            "Modulo 3",
            "Modulo 4",
            "Modulo 5"  
        );
        emailComboBox.setPromptText("Email address");
        emailComboBox.setEditable(true);        
        emailComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {                
                address = t1;                
            }    
        });
        
      
     
        
        button.setOnAction(new EventHandler<ActionEvent>() {
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
        
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("To: "), 0, 0);
        grid.add(emailComboBox, 1, 0);
        
        
        grid.add(new Label("Subject: "), 0, 1);
              
       
        grid.add(button, 0, 3);
        
        
        Group root = (Group)scene.getRoot();
        root.getChildren().add(grid);
        stage.setScene(scene);
        stage.show();
 
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
}
