package logica;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="examen_teorico")
public class ExamenTeorico{
	
	private int id_examen;
	private int nivel;
	private String nombre;
	private String descripcion;
	private int tiempo_examen;
	private int numPreguntas;
	
	public ExamenTeorico (){
		super();
	}
	
	public ExamenTeorico(int id, int n, String name, String desc, int t, int preg){
		this.id_examen=id;
		this.nombre=name;
		this.descripcion=desc;
		this.tiempo_examen=t;
		this.numPreguntas=preg;
		this.nivel=n;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getTiempo_examen() {
		return tiempo_examen;
	}

	public void setTiempo_examen(int tiempo_examen) {
		this.tiempo_examen = tiempo_examen;
	}

	public int getNumPreguntas() {
		return numPreguntas;
	}

	public void setNumPreguntas(int numPreguntas) {
		this.numPreguntas = numPreguntas;
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
