package logica;

public class ObjetoProhibido {
	
	private int id_objeto;
	private String nombre;
	private byte[] mapa_de_bits;
	private int id_arma;
	
	public ObjetoProhibido(int id, String n, byte[] mapa, int arm){
		this.id_objeto=id;
		this.nombre=n;
		this.mapa_de_bits=mapa;
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

	public byte[] getMapa_de_bits() {
		return mapa_de_bits;
	}

	public void setMapa_de_bits(byte[] mapa_de_bits) {
		this.mapa_de_bits = mapa_de_bits;
	}

	public int getId_arma() {
		return id_arma;
	}

	public void setId_arma(int id_arma) {
		this.id_arma = id_arma;
	}
	
	

}
