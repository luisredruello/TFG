package controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import database.DBLocal;
import database.DBServer;
import logica.*;
import tools.Utilities;

public class Controlador {
	
	private DBLocal database;	
	private DBServer server;

	public Controlador(){ //acceso a base de datos/servidor web
		this.database = new DBLocal();
		this.server = new DBServer();
	}
	
	public Usuario validaUsuario(String dni,String pass){
		Usuario user = null;
		user = this.server.getUser(dni, Utilities.md5(pass));
		return user;
	}
	
	public int insertaUsuario(String nombre, String dni, String pass){
		return this.server.insertaUsuario(nombre, dni, Utilities.md5(pass), Utilities.getSystemDate());
	}
	
	public int borraUsuario(String dni){
		return this.server.deleteUsuario(dni);
	}
	
	public int actualizaUsuario(Usuario u,String n,String p){
		u.setNombre_completo(n);
		u.setPass(Utilities.md5(p));
		return this.server.updateUsuario(u);
	}
	
	public List<Usuario> dameListaUsuarios(){
		return this.server.getUserList();
	}
	
	public List<Certificacion> getListaCertificados(){
		return this.server.getCertificados();
	}
	
	public ExamenPractico getExamenPractico(int l){
		return this.server.getExamenPractico(l);
	}
	
	public ExamenTeorico getExamenTeorico(int l){
		return this.server.getExamenTeorico(l);
	}
	
	/**
	 * Devuelve un modulo teorico a la vista
	 * @param nivel int
	 * @param modulo int
	 * @return String que representa el path donde esta el modulo
	 */
	public String getPDF(int nivel, int modulo){
		return this.server.getPDFTeorico(nivel,modulo);
	}
	
	/**
	 * Sube un modulo teorico al sistema
	 * @param level int nivel al que pertenece este modulo
	 * @param idmodulo int este numero es el siguiente a insertar
	 * @param file File
	 * @return 1 si se ha subido correctamente el archivo
	 */
	public int subeTeoria(int level, int idmodulo, File file){
		FileInputStream is;
		byte[] fileContent = null;
		try {
			fileContent = new byte[(int)file.length()];
			is = new FileInputStream(file);
			is.read(fileContent);
			is.close();
			return this.server.uploadPDFTeorico(level, idmodulo, fileContent);
		} catch (IOException i) {
			i.printStackTrace();
		}
		return 0;
	}
	
	public ModuloTeorico getModuloTeorico(int nivel, int id){
		return this.server.getModuloTeorico(nivel, id);
	}
	
	public List<ModuloTeorico> getListaModulosTeoricos(int nivel){
		return this.server.getListModTeorico(nivel);
	}

	public boolean existeUsuario(String dni) {
		return this.server.existeUsuario(dni);
	}
	
	/**
	 * Devuelve el numero de certificaciones conseguidas por el alumnos
	 * @param dni del alumno
	 * @return numero de certificaciones o 0 si no ha conseguido ninguna aún
	 */
	public int numCertificaciones(String dni) {
		List<Integer> l = this.server.getCertificadosFromUser(dni);
		return (l.size()>0)?l.size():0;
	}
	
	/**
	 * Agrega una nueva Certificacion al Alumno
	 * @param a Usuario
	 * @param level nivel que ha obtenido el alumno
	 * @return un entero positivo si se ha agregado la Certificacion, 0 en otro caso
	 */
	public int insertaCertificacion(Usuario a, int level) {
		return this.server.obtieneCertificacion(level, a.getDni());				
	}
	
	/**
	 * Accede a BBDD y devuelve una lista de preguntas pertenecientes a un examen
	 * @param idExamen
	 * @return Lista de Preguntas
	 */
	public List<Pregunta> getListaPreguntasFromExamen(int idExamen){
		return this.server.getListaPreguntasFromExamen(idExamen);
	}
	
	/**
	 * Accede a BBDD y devuelve la lista de respuestas disponibles para una pregunta
	 * @param idPregunta
	 * @return Lista de Respuestas
	 */
	public List<Respuesta> getListaRespuestasFromPregunta(int idPregunta){
		return this.server.getListaRespuestasFromPregunta(idPregunta);
	}
	
	/**
	 * Accede al Web Service para aprobar al alumno un examen teorico
	 * @param a Usuario, alumno que ha aprobado
	 * @param t Examen Teorico que ha aprobado
	 * @return un entero mayor a 0 si no hay errores, 0 en otro caso
	 */
	public int apruebaExamenTeorico(Usuario a, ExamenTeorico t) {
		return this.server.insertaAprobadoTeorico(a.getDni(), t.getId_examen());
	}
	
	/**
	 * Comprueba si el usuario ha aprobado previamente un Examen Teorico
	 * @param dni del Alumno
	 * @return true si el usuario esta en la tabla de aprobado teorico, false en otro caso
	 */
	public boolean tieneAprobadoTeorico(String dni, int idExamen){
		return this.server.tieneAprobadoTeorico(dni, idExamen)>0?true:false;
	}
	
	/**
	 * Comprueba si el alumno tiene aprobado un Examen Practico
	 * @param dni String
	 * @param idPractico int
	 * @return true si el alumno ha aprobado el examen previamente, false en otro caso
	 */
	public boolean tieneAprobadoPractico(String dni, int idPractico){
		return this.server.tieneAprobadoPractico(dni, idPractico)>0?true:false;
	}
	
	/**
	 * Devuelve un array que representa una imagen
	 * @param tipo (normal,organico,inorganico o B/N)
	 * @param idIma id de la imagen
	 * @param idExam id del examen practico al que pertenece la imagen
	 * @return array de bytes que corresponde a una imagen
	 */
	public byte[] getImageBytes(String tipo, int idIma, int idExam){
		return this.server.getImagenBytes(tipo, idIma, idExam);
	}
	
	/**
	 * Devuelve una lista de imagenes pertenecientes a un examen practico
	 * @param idExam del Examen Practico
	 * @return List de imagenes
	 */
	public List<Imagen> getImagenesFromExamen(int idExam){
		return this.server.getListImagesFromExam(idExam);
	}
	
	/**
	 * Devuelve un ObjetoProhibido perteneciente a una imagen
	 * @param id_objeto
	 * @return ObjetoProhibido
	 */
	public ObjetoProhibido getObjetoProhibido(int id_objeto) {
		return this.server.getObjetoProhibido(id_objeto);
	}
	
	/**
	 * El alumno aprueba el examen practico
	 * @param alum Usuario
	 * @param prac ExamenPractico
	 * @return un entero positivo si se ha insertado correctamente, 0 en otro caso
	 */
	public int apruebaExamenPractico(Usuario alum, ExamenPractico prac) {
		return this.server.insertaAprobadoPractico(alum.getDni(), prac.getId_examen());
	}
	
	/**
	 * Inserta una pregunta en el sistema
	 * @param pregunta String enunciado de la pregunta
	 * @param id_examen int
	 * @return devuelve el id de la pregunta insertada, 0 si no se ha podido insertar
	 */
	public int insertaPregunta(String pregunta, int id_examen) {
		return this.server.insertaPregunta(pregunta, id_examen);
	}
	
	/**
	 * Inserta una respuesta en el sistema
	 * @param idPregunta int
	 * @param respuesta enunciado respuesta
	 * @param correcta 1 si esta pregunta es la correcta, 0 si no es la correcta
	 * @return 1 si se ha insertado correctamente, 0 en caso contrario
	 */
	public int insertaRespuesta(int idPregunta, String respuesta, int correcta) {
		return this.server.insertaRespuesta(idPregunta, respuesta, correcta);
	}
	
	/**
	 * Devuelve el tipo de arma de un objeto prohibido
	 * @param idArma int
	 * @return
	 */
	public TipoArma getTipoArma(int idArma){
		return this.server.getTipoArma(idArma);
	}
	
	/**
	 * Devuelve una lista con todos los tipos de objeto prohibido del sistema
	 * @return List<TipoArma>
	 */
	public List<TipoArma> getListaTipoArma() {
		return this.server.getListaTipoArma();
	}
	
	/**
	 * Devuelve un array de bytes a partir de un archivo
	 * @param file File
	 * @return byte[] o null si ha habido un error
	 */
	public byte[] getBytesFromFile(File file){
		FileInputStream is;
		byte[] fileContent = null;
		try {
			fileContent = new byte[(int)file.length()];
			is = new FileInputStream(file);
			is.read(fileContent);
			is.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
		return fileContent;
	}
	
	/**
	 * Envia a la clase encargada de llamar al web service una nueva imagen sin objeto prohibido
	 * @param id_examen int
	 * @param arrayNormal byte[]
	 * @param arrayBN byte[]
	 * @param arrayOrganico byte[]
	 * @param arrayInorganico byte[]
	 * @return int 1 si se ha insertado correctao, 0 en otro caso
	 */
	public int subeImagenLimpia(int id_examen, byte[] arrayNormal, byte[] arrayBN, byte[] arrayOrganico,
			byte[] arrayInorganico) {
		return this.server.insertaImagenLimpia(id_examen, arrayNormal, arrayBN, arrayOrganico, arrayInorganico);
	}
	
	/**
	 * Envia a la clase encargada de llamar al WebService una imagen con ObjetoProhibido
	 * @param id_examen int id del examen practico asociado
	 * @param arrayNormal byte[]
	 * @param arrayBN byte[]
	 * @param arrayOrganico byte[]
	 * @param arrayInorganico byte[]
	 * @param prohibido byte[]
	 * @param idTipoArma byte[]
	 * @return int 1 si se ha insertado correctamente, 0 en otro caso
	 */
	public int subeImagenProhibido(int id_examen, byte[] arrayNormal, byte[] arrayBN, byte[] arrayOrganico,
			byte[] arrayInorganico, ObjetoProhibido prohibido, int idTipoArma) {
		return this.server.insertaImagenProhibido(id_examen, arrayNormal, arrayBN, arrayOrganico, arrayInorganico
				, prohibido.getPosx(), prohibido.getPosy(), prohibido.getAncho(), prohibido.getAlto(), idTipoArma);
	}	
	
}
