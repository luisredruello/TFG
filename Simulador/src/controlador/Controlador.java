package controlador;

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
	
	public String getPDF(int nivel, int modulo){
		return this.server.getPDFTeorico(nivel,modulo);
	}
	
	public int subeTeoria(int level, int idmodulo, byte[] files){
		int id = idmodulo+1;
		return this.database.uploadPDFTeorico(level, id, files);
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
	 * @param t Examen Teorico
	 * @return un entero positivo si se ha agregado la Certificacion, 0 en otro caso
	 */
	public int insertaCertificacion(Usuario a, ExamenTeorico t) {
		//return this.database.obtieneCertificacion(t.getNivel(), a.getDni());
		return this.server.obtieneCertificacion(t.getNivel(), a.getDni());				
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
	 * Accede al Web Service o a BBDD para aprobar al alumno
	 * @param a Usuario, alumno que ha aprobado
	 * @param t Examen Teorico que ha aprobado
	 * @return un entero mayor a 0 si no hay errores, 0 en otro caso
	 */
	public int apruebaExamenTeorico(Usuario a, ExamenTeorico t) {
		//return this.database.insertaAprobadoTeorico(a.getDni(), t.getId_examen(), Utilities.getSystemDate());
		return this.server.insertaAprobadoTeorico(a.getDni(), t.getId_examen(), Utilities.getSystemDate());
	}
	
	/**
	 * Comprueba si el usuario ha aprobado previamente un Examen Teorico
	 * @param dni del Alumno
	 * @return true si el usuario esta en la tabla de aprobado teorico, false en otro caso
	 */
	public boolean tieneAprobadoTeorico(String dni){
		//return this.database.tieneAprobadoTeorico(dni)>0?true:false;
		return this.server.tieneAprobadoTeorico(dni)>0?true:false;
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
	
	
}
