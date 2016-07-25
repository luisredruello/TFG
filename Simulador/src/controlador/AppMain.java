package controlador;

import vista.LoginWindow;;;

public class AppMain {

	public static void main(String[] args) {
		Controlador controlador = new Controlador();
		
		LoginWindow login = new LoginWindow(controlador);

	}

}
