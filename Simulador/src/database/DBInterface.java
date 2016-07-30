package database;

import java.sql.Date;
import java.util.List;

import logica.*;

public interface DBInterface {
	
	public Usuario getUser(String user,String pass);
	
	public int insertaUsuario(String name, String dni, String pass, Date f);
	
	public List<Usuario> getUserList();
	
	public List<Certificacion> getCertificados();
	
	public ExamenPractico getExamenPractico(int level);
	
	public ExamenTeorico getExamenTeorico(int level);
	
	public String getPDFTeorico(int nivel, int idmodulo);
	
	public ModuloTeorico getModuloTeorico(int nivel, int id);
	
	public List<ModuloTeorico> getListModTeorico(int nivel);

}
