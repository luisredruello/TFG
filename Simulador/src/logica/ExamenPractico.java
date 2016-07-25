package logica;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="examen_practico")
public class ExamenPractico{
	
	private int id_examen;
	private int nivel;
	private int numImagenes;
	
	public ExamenPractico (){
	}
	
	public ExamenPractico(int id, int n, int num){
		this.id_examen=id;
		this.nivel=n;
		this.numImagenes=num;
	}

	public int getNumImagenes() {
		return numImagenes;
	}

	public void setNumImagenes(int numImagenes) {
		this.numImagenes = numImagenes;
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

	public String toString(){
		return "Exámen Práctico "+Integer.toString(this.id_examen);
	}

}
