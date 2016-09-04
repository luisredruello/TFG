package database;

import java.sql.Date;
import java.util.List;

import logica.*;

public interface DBInterface {
	
	/**
	 * Devuelve un usuario
	 * @param user dni del usuario
	 * @param pass password
	 * @return Usuario
	 */
	public Usuario getUser(String user,String pass);
	
	/**
	 * Inserta un nuevo usuario en el sistema
	 * @param name String
	 * @param dni String
	 * @param pass String
	 * @param f Date
	 * @return 1 si ha habido exito al insertar, 0 en caso contrario
	 */
	public int insertaUsuario(String name, String dni, String pass, Date f);
	
	/**
	 * Actualiza los datos de un alumno
	 * @param user Usuario
	 * @return 1 si ha habido exito al actualizar, 0 en caso contrario
	 */
	public int updateUsuario(Usuario user);
	
	/**
	 * Elimina un usuario del sistema
	 * @param dni String
	 * @return 1 si ha habido exito al eliminar, 0 en caso contrario
	 */
	public int deleteUsuario(String dni);
	
	/**
	 * Comprueba si existe un usuario en el sistema
	 * @param dni String
	 * @return true si el usuario existe, false en caso contrario
	 */
	public boolean existeUsuario(String dni);
	
	/**
	 * Devuelve las Certificaciones que ha aprobado el alumno
	 * @param dni del alumnos
	 * @return número de certificaciones conseguidas
	 */
	public List<Integer> getCertificadosFromUser(String dni);
	
	/**
	 * Devuelve los usuarios del sistema
	 * @return Lista de objetos de tipo Usuario
	 */
	public List<Usuario> getUserList();
	
	/**
	 * Devuelve los certificados del sistema
	 * @return Lista de objettos de tipo Certificacion
	 */
	public List<Certificacion> getCertificados();
	
	/**
	 * Devuelve un ExamenPractico perteneciente a un determinado nivel de Certificacion
	 * @param level int nivel de certificacion
	 * @return ExamenPractico
	 */
	public ExamenPractico getExamenPractico(int level);
	
	/**
	 * Devuelve un ExamenTeorico perteneciente a un determinado nivel de Certificacion
	 * @param level int nivel de certificacion
	 * @return ExamenTeorico
	 */
	public ExamenTeorico getExamenTeorico(int level);
	
	/**
	 * Carga un nuevo pdf teorico en el sistema
	 * @param nivel int Certificacion
	 * @param idmodulo int ModuloTeorico
	 * @param files array de bytes que representa el PDF
	 * @return 1 si se ha subido correctamente, 0 en caso contrario
	 */
	public int uploadPDFTeorico(int nivel, int idmodulo, byte[] files);
	
	/**
	 * Devuelve un ModuloTeorico de una determinada Certificacion
	 * @param nivel int Certificacion
	 * @param id int clave del ModuloTeorico
	 * @return ModuloTeorico
	 */
	public ModuloTeorico getModuloTeorico(int nivel, int id);
	
	/**
	 * Devuelve una lista de Modulos Teoricos pertenecientes a una cierta Certificacion
	 * @param nivel int Certificacion
	 * @return Lista de objetos ModuloTeorico
	 */
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
	 * Comprueba si un alumno tiene aprobado un examen practico
	 * @param dni String
	 * @param idExamenPractico int
	 * @return un entero positivo si el alumno ha aprobado el examen practico, 0 en caso contrario
	 */
	public int tieneAprobadoPractico(String dni, int idExamenPractico);
	
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
	
	/**
	 * Inserta una nueva pregunta en el sistema
	 * @param enunciado de la nueva pregunta
	 * @param idExamen int
	 * @return el idPregunta generado o 0 si ha habido un error
	 */
	public int insertaPregunta(String enunciado, int idExamen);
	
	/**
	 * Inserta una nueva respuesta en el sistema
	 * @param idPregunta int
	 * @param respuesta String
	 * @param correcta int 1 si es correcta, 0 si es falsa
	 * @return 1 si se ha insertado correctamente, 0 en caso contrario
	 */
	public int insertaRespuesta(int idPregunta, String respuesta, int correcta);
	
	/**
	 * Devuelve el tipo del objeto prohibido
	 * @param idArma int
	 * @return objeto TipoArma
	 */
	public TipoArma getTipoArma(int idArma);
	
	/**
	 * Devuelve una lista con todos los tipos de armas de la BBDD
	 * @return List<TipoArma>
	 */
	public List<TipoArma> getListaTipoArma();
	
	/**
	 * Inserta una nueva imagen sin objeto prohibido en la BBDD
	 * @param idExamenPractico int
	 * @param normal byte[]
	 * @param bn byte[]
	 * @param organ byte[]
	 * @param inorgan byte[]
	 * @return 1 si se ha insertado correctamente
	 */
	public int insertaImagenLimpia(int idExamenPractico, byte[] normal, byte[] bn, byte[] organ, byte[] inorgan);
	
	/**
	 * Inserta una nueva imagen con objeto peligroso en la BBDD
	 * @param idPractico int id del examen practico
	 * @param normal byte[]
	 * @param bn byte[]
	 * @param organ byte[]
	 * @param inorgan byte[]
	 * @param x int posicion del objeto prohibido
	 * @param y int posiscion del objeto prohibido
	 * @param ancho int tam del objeto prohibido
	 * @param alto int tam del objeto prohibido
	 * @param idTipoArma int id del tipo de objeto prohibido
	 * @return 1 si se ha insertado correctamente, 0 en otro caso
	 */
	public int insertaImagenProhibido(int idPractico, byte[] normal, byte[] bn, byte[] organ, byte[] inorgan,
			int x, int y, int ancho, int alto, int idTipoArma);

}
