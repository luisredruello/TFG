package logica;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="tipo_arma")
public class TipoArma {
	
	private int id_arma;
	private String descripcion;
	
	public TipoArma(){}
	
	public TipoArma(int id, String d){
		this.id_arma=id;
		this.descripcion=d;
	}

	public int getId_arma() {
		return id_arma;
	}

	public void setId_arma(int id_arma) {
		this.id_arma = id_arma;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
