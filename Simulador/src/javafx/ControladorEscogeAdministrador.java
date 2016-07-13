package javafx;

import java.net.URL;
import java.util.ResourceBundle;

import controlador.Controlador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import logica.Usuario;

public class ControladorEscogeAdministrador implements Initializable{

	@FXML 
	private Label etiquetaEncabezado; // tengo dudas de si hay que poner aquí los Label
	
	@FXML 
	private Label etiquetaCertificacion;


	@FXML
	private Button botonAdministrarUsuarios;
	
	@FXML
	private Button botonAdministrarExamenes;
	
	@FXML
	private Button botonAdministrarGalerias;

	@FXML
	private Button botonAjustes;
	
	@FXML
	private void botonAdministrarUsuariosAction (ActionEvent event){
		
	}
	@FXML
	private void botonAdministrarExamenesAction (ActionEvent event){
		
	}
	@FXML
	private void botonAdministrarGaleriasAction (ActionEvent event){
		
	}
	@FXML
	private void botonAjustesAction (ActionEvent event){
		
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void setControl(Controlador controlador) {
		// TODO Auto-generated method stub
		
	}

	public void setUsuario(Usuario user) {
		// TODO Auto-generated method stub
		
	}

}
