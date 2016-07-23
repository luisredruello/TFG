package logica;

public class Pregunta {
	
	private int id_pregunta;
	private String enunciado;
	private int id_examen;
	
	
	public Pregunta(int id_pregunta, String enunciado, int id_examenTeorico) {
		this.id_pregunta = id_pregunta;
		this.enunciado = enunciado;
		this.id_examen = id_examenTeorico;
	}


	public int getId_pregunta() {
		return id_pregunta;
	}


	public void setId_pregunta(int id_pregunta) {
		this.id_pregunta = id_pregunta;
	}


	public String getEnunciado() {
		return enunciado;
	}


	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}


	public int getId_examen() {
		return id_examen;
	}


	public void setId_examen(int id_examen) {
		this.id_examen = id_examen;
	}
	
	
}
