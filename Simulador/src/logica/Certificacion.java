package logica;

public class Certificacion {
	
	private int nivel;
	private ExamenTeorico teorico;
	private ExamenPractico practico;
	
	public Certificacion(){	};
	
	public Certificacion(int nivel) {
		this.nivel = nivel;
		this.teorico = null;
		this.practico = null;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	public ExamenTeorico getTeorico() {
		return teorico;
	}

	public void setTeorico(ExamenTeorico teorico) {
		this.teorico = teorico;
	}

	public ExamenPractico getPractico() {
		return practico;
	}

	public void setPractico(ExamenPractico practico) {
		this.practico = practico;
	}
	
	public String pintaExamenPractico(){
		if (this.practico==null) return "No hay Exámenes Prácticos para esta Certificación";
		else return this.practico.toString();
	}
	

	public String toString(){
		return "C"+Integer.toString(this.nivel);
	}

}
