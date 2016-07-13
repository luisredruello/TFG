package database;

import logica.Usuario;

public interface DBInterface {
	
	public Usuario getUser(String user,String pass);

}
