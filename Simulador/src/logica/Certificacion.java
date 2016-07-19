package logica;

public class Certificacion {
	
	private int nivel;
	private int limite;
	
	public Certificacion(int nivel, int limite) {
		this.nivel = nivel;
		this.limite = limite;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}
	
	public String toString(){
		return Integer.toString(this.nivel);
	}

}
