package controlador;

import vista.LoginWindow;

public class AppMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controlador controlador = new Controlador();
		
		LoginWindow login = new LoginWindow(controlador);

	}

}
