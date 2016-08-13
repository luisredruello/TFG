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
	
	/**
	 * Devuelve todas las preguntas pertenecientes a un examen teorico
	 * @param idExamen del examen teorico
	 * @return Lista de Preguntas de un examen teorico
	 */
	public List<Pregunta> getListaPreguntasFromExamen(int idExamen);
	
	/**
	 * Devuelve una lista con todas las respuestas posibles para un pregunta
	 * @param idPregunta de la pregunta
	 * @return Lista con las respuestas a una pregunta pasada por parámetro
	 */
	public List<Respuesta> getListaRespuestasFromPregunta(int idPregunta);

}
