package logica;

public class ObjetoProhibido {
	
	private int id_objeto;
	private String nombre;
	private float alto;
	private float ancho;
	private int id_arma;
	
	public ObjetoProhibido(){}
	
	public ObjetoProhibido(int id, String n, float al, float anc,int arm){
		this.id_objeto=id;
		this.nombre=n;
		this.alto=al;
		this.ancho=anc;
		this.id_arma=arm;
	}

	public int getId_objeto() {
		return id_objeto;
	}

	public void setId_objeto(int id_objeto) {
		this.id_objeto = id_objeto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId_arma() {
		return id_arma;
	}

	public void setId_arma(int id_arma) {
		this.id_arma = id_arma;
	}

	/**
	 * @return the alto
	 */
	public float getAlto() {
		return alto;
	}

	/**
	 * @param alto the alto to set
	 */
	public void setAlto(float alto) {
		this.alto = alto;
	}

	/**
	 * @return the ancho
	 */
	public float getAncho() {
		return ancho;
	}

	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(float ancho) {
		this.ancho = ancho;
	}
	
	

}
