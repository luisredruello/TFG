package database;

import java.util.List;

import logica.*;

public interface DBInterface {
	
	public Usuario getUser(String user,String pass);
	
	public List<Usuario> getUserList();
	
	public List<Certificacion> getCertificados();

}
