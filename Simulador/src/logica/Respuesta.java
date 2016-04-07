package logica;

public class Respuesta {
	
	private int id_respuesta;
	private int es_correcta;
	private String enunciado;
	private int id_pregunta;
	
	public Respuesta(int id_respuesta, int es_correcta, String enunciado, int id_pregunta) {
		this.id_respuesta = id_respuesta;
		this.es_correcta = es_correcta;
		this.enunciado = enunciado;
		this.id_pregunta = id_pregunta;
	}
	
	

}
