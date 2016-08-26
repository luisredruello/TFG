package logica;

public class ObjetoProhibido {
	
	private int id_objeto;
	private String nombre;
	private int posx;
	private int posy;
	private int alto;
	private int ancho;
	private int id_arma;
	
	public ObjetoProhibido(){}
	
	public ObjetoProhibido(int id, String n, int x, int y, int al, int anc, int arm){
		this.id_objeto=id;
		this.nombre=n;
		this.posx=x;
		this.posy=y;
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
	 * @return the posx
	 */
	public int getPosx() {
		return posx;
	}

	/**
	 * @param posx the posx to set
	 */
	public void setPosx(int posx) {
		this.posx = posx;
	}

	/**
	 * @return the posy
	 */
	public int getPosy() {
		return posy;
	}

	/**
	 * @param posy the posy to set
	 */
	public void setPosy(int posy) {
		this.posy = posy;
	}

	/**
	 * @return the alto
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * @param alto the alto to set
	 */
	public void setAlto(int alto) {
		this.alto = alto;
	}

	/**
	 * @return the ancho
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	

}
