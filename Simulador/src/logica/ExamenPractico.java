package logica;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="examen_practico")
public class ExamenPractico{
	
	private int id_examen;
	private int nivel;
	private int num_imagenes;
	private int tiempo_examen;
	
	public ExamenPractico (){
	}
	
	public ExamenPractico(int id, int n, int num, int t){
		this.id_examen=id;
		this.nivel=n;
		this.num_imagenes=num;
		this.tiempo_examen=t;
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

	/**
	 * @return the num_imagenes
	 */
	public int getNum_imagenes() {
		return num_imagenes;
	}

	/**
	 * @param num_imagenes the num_imagenes to set
	 */
	public void setNum_imagenes(int num_imagenes) {
		this.num_imagenes = num_imagenes;
	}

	/**
	 * @return the tiempo_examen
	 */
	public int getTiempo_examen() {
		return tiempo_examen;
	}

	/**
	 * @param tiempo_examen the tiempo_examen to set
	 */
	public void setTiempo_examen(int tiempo_examen) {
		this.tiempo_examen = tiempo_examen;
	}

	public String toString(){
		return "Exámen Práctico "+Integer.toString(this.id_examen);
	}
	
	/**
	 * Devuelve el tiempo que debe durar un examen en segundos
	 * @return int Representa milisegundos que dura el examen
	 */
	public int getSegundos(){
		return (tiempo_examen/100)*1000;
	}

}
