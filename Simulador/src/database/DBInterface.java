package database;

import java.sql.Date;
import java.util.List;

import logica.*;

public interface DBInterface {
	
	public Usuario getUser(String user,String pass);
	
	public int insertaUsuario(String name, String dni, String pass, Date f);
	
	public int updateUsuario(Usuario user);
	
	public int deleteUsuario(String dni);
	
	public boolean existeUsuario(String dni);
	
	/**
	 * Devuelve las Certificaciones que ha aprobado el alumno
	 * @param dni del alumnos
	 * @return número de certificaciones conseguidas
	 */
	public List<Integer> getCertificadosFromUser(String dni);
	
	public List<Usuario> getUserList();
	
	public List<Certificacion> getCertificados();
	
	public ExamenPractico getExamenPractico(int level);
	
	public ExamenTeorico getExamenTeorico(int level);
	
	public int uploadPDFTeorico(int nivel, int idmodulo, byte[] files);
	
	public ModuloTeorico getModuloTeorico(int nivel, int id);
	
	public List<ModuloTeorico> getListModTeorico(int nivel);

}
