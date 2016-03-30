package vista;

import controlador.UserMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import logica.Usuario;

/**
 * Esta clase es un controlador, pero de la vista, es decir, hace de uni�n entre la vista de eclipse
 * y la vista de sceneBuilder. Comunica ambas vistas
 * Cualquier m�todo conectado con @FXML es accesible desde SceneBuilder
 */

	public class ControladorLoginUsuario {

		@FXML //aqu� van los code-id que he asignado en SceneBuilder
			  // una etiqueta FXML por cada elemento de la interfaz
		private Label etiquetaLogin;
		
		@FXML
		private TextField txtUsuario;
		
		@FXML
		private PasswordField txtContrase�a;
		
		@FXML
		private Button botonEntrar;
		
		private UserMain userMain;
		
		
		@FXML //los m�todos de SceneBuilder tambi�n se marcan con etiqueta
		private void botonEntrarAction (ActionEvent event){
			Usuario user = null;
			user=this.userMain.getControlador().validaUsuario(txtUsuario.getText(),txtContrase�a.getText());
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Saludo Inicial");
			if (user!=null){
				alert.setContentText("Bienvenido "+user.getNombre());
				alert.showAndWait();
			}
			else {
				alert.setHeaderText("Error");
				alert.setContentText("No existe el usuario "+txtUsuario.getText());
				alert.showAndWait();
			}
				
		}

		public void setMainApp(UserMain userMain) {
			// TODO Auto-generated method stub
			this.userMain = userMain;
			
		}
}
