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
	 * @return n�mero de certificaciones conseguidas
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
	 * @return Lista con las respuestas a una pregunta pasada por par�metro
	 */
	public List<Respuesta> getListaRespuestasFromPregunta(int idPregunta);
	
	/**
	 * Inserta en la tabla de aprobado teorico un nuevo registro, es decir, que el alumno ha aprobado este modulo
	 * @param dni del alumno
	 * @param idExamenTeorico del Examen Teorico
	 * @return un entero, 1 si se ha insertado correctamente el valor en la tabla, 0 en si ha habido un error
	 */
	public int insertaAprobadoTeorico(String dni, int idExamenTeorico);
	
	/**
	 * Comprueba si un alumno tiene aprobado un examen teorico
	 * @param dni del alumno
	 * @param idExamenTeorico el examen que ha aprobado el alumno previamente
	 * @return un entero positivo si el usuario ha aprobado el examen teorico, 0 en caso contrario 
	 */
	public int tieneAprobadoTeorico(String dni, int idExamenTeorico);
	
	/**
	 * Inserta en la tabla tiene una nueva certificacion para el alumno
	 * @param level nivel de la certificacion obtenido
	 * @param dni del alumno que la ha obtenido
	 * @return un entero 1 si se ha insertado correctamente, 0 en otro caso 
	 */
	public int obtieneCertificacion(int level, String dni);
	
	/**
	 * Obtiene una imagen desde el Web Service
	 * @param tipo normal,blanco y negro, organico o inorganico
	 * @param idImagen entero
	 * @param idExamen a la que pertenece esta imagen
	 * @return array de bytes que representa una imagen
	 */
	public byte[] getImagenBytes(String tipo, int idImagen, int idExamen);
	
	/**
	 * Devuelve una lista de imagenes pertenecientes a un examen determinado
	 * @param idExamen del examen practico al que pertenecen las imagenes
	 * @return Lista de imagenes
	 */
	public List<Imagen> getListImagesFromExam(int idExamen);
	
	/**
	 * Devuelve un objeto prohibido perteneciente a una imagen
	 * @param idObjeto entero
	 * @return Objeto Prohibido
	 */
	public ObjetoProhibido getObjetoProhibido(int idObjeto);
	
	/**
	 * Inserta un nuevo registro en la tabla Aprobado_Practico
	 * @param dni del alumno que la ha conseguido
	 * @param idExamenPractico entero
	 * @return 1 si se ha insertado correctamente, 0 en otro caso
	 */
	public int insertaAprobadoPractico(String dni, int idExamenPractico);

}
