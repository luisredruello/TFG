package vista;

import controlador.UserMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Esta clase es un controlador, pero de la vista, es decir, hace de unión entre la vista de eclipse
 * y la vista de sceneBuilder. Comunica ambas vistas
 * Cualquier método conectado con @FXML es accesible desde SceneBuilder
 */

	public class ControladorLoginUsuario {

		@FXML //aquí van los code-id que he asignado en SceneBuilder
			  // una etiqueta FXML por cada elemento de la interfaz
		private Label etiquetaLogin;
		
		@FXML
		private TextField txtUsuario;
		
		@FXML
		private PasswordField txtContraseña;
		
		@FXML
		private Button botonEntrar;
		
		private UserMain userMain;
		
		@FXML //los métodos de SceneBuilder también se marcan con etiqueta
		private void botonEntrarAction (ActionEvent event){  //AQUÍ HABRÁ QUE ACCEDER A LA BASE DE DATOS
			
			if (txtUsuario.getText().equals("UsuarioPrueba")&& txtContraseña.getText().equals("ContraseñaPrueba")){
				etiquetaLogin.setText("Bienvenido" + txtUsuario.getText());
			}
			else {
				etiquetaLogin.setText("Usuario o contraseña erróneo");
			}
				
		}

		public void setMainApp(UserMain userMain) {
			// TODO Auto-generated method stub
			this.userMain = userMain;
			
		}
		
		
}
