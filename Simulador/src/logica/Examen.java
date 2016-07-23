package logica;

public class Examen {
	
	private int id_examen;
	private int nivel;
	
	public Examen(){
		
	}
	
	public Examen(int id, int n){
		this.id_examen=id;
		this.nivel=n;
	}

	public int getId_examen() {
		return id_examen;
	}

	public void setId_examen(int id_examen) {
		this.id_examen = id_examen;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	

}
