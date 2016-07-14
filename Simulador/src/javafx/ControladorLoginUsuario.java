package javafx;

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
	private void botonEntrarAction (ActionEvent event){
		Usuario user = null;
		user=this.userMain.getControlador()
				.validaUsuario(txtUsuario.getText(),txtContraseña.getText());
			
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Mensaje Entrada");
		try{
			if (user!=null && user.getTipo()==0){
				ControladorEscogeCertificacion c1 = (ControladorEscogeCertificacion) this.userMain
						.getControlador()
						.replaceSceneContent("/vista/EscogeCertificacion.fxml");
				c1.setApp(this.userMain);
				c1.setControl(this.userMain.getControlador());
				c1.setUsuario(user);
			}
			else if (user!=null && user.getTipo()==1){
				ControladorEscogeAdministrador c2 = (ControladorEscogeAdministrador) this.userMain
						.getControlador()
						.replaceSceneContent("/vista/EscogeAdministrador.fxml");
				//c2.setApp(this.userMain);
				c2.setControl(this.userMain.getControlador());
				c2.setUsuario(user);
				
			}
			else {
				alert.setHeaderText("Error");
				alert.setContentText("No existe el usuario "+txtUsuario.getText());
				alert.showAndWait();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
				
	}

	public void setMainApp(UserMain userMain) {
		// TODO Auto-generated method stub
		this.userMain = userMain;
	}
}
