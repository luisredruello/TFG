package logica;

public class ModuloTeorico {

	private int id_modulo;
	private int nivel;
	private byte[] PDF;
	
	public ModuloTeorico(){}	
	
	public ModuloTeorico(int id_modulo, int nivel) {
		this.id_modulo = id_modulo;
		this.nivel = nivel;
		this.PDF = null;
	}
	
	public ModuloTeorico(int id, int n, byte[] p){
		this.id_modulo=id;
		this.nivel=n;
		this.PDF=p;
	}

	public int getId_modulo() {
		return id_modulo;
	}

	public void setId_modulo(int id_modulo) {
		this.id_modulo = id_modulo;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public byte[] getPDF() {
		return PDF;
	}

	public void setPDF(byte[] pDF) {
		PDF = pDF;
	}
	
	public String toString(){
		return "Modulo "+Integer.toString(id_modulo);
	}
	
	
}
