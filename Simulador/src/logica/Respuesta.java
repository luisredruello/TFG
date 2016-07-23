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

	public int getId_respuesta() {
		return id_respuesta;
	}

	public void setId_respuesta(int id_respuesta) {
		this.id_respuesta = id_respuesta;
	}

	public int getEs_correcta() {
		return es_correcta;
	}

	public void setEs_correcta(int es_correcta) {
		this.es_correcta = es_correcta;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public int getId_pregunta() {
		return id_pregunta;
	}

	public void setId_pregunta(int id_pregunta) {
		this.id_pregunta = id_pregunta;
	}
	
	

}
