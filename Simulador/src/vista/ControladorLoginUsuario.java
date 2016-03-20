package vista;

import controlador.Controlador;
import controlador.UserMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
			
			/*if (txtUsuario.getText().equals("UsuarioPrueba")&& txtContrase�a.getText().equals("Contrase�aPrueba")){
				etiquetaLogin.setText("Bienvenido" + txtUsuario.getText());
			}
			else {
				etiquetaLogin.setText("Usuario o contrase�a err�neo");
			}*/
			Usuario user = null;
			user=this.userMain.getControlador().validaUsuario(txtUsuario.getText(),txtContrase�a.getText());
				
		}

		public void setMainApp(UserMain userMain) {
			// TODO Auto-generated method stub
			this.userMain = userMain;
			
		}
}
