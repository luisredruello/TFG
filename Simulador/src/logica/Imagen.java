package logica;

public class Imagen {
	
	private int id_imagen;
	private byte[] imagenPropia;
	private byte[] contrasteOrganico;
	private byte[] contrasteInorganico;
	private byte[] contrasteBN;
	private int id_examen;
	
	public Imagen(int id, byte[] propia, byte[] organico, byte[] inorganico, byte[] bn, int examen){
		this.id_imagen=id;
		this.imagenPropia=propia;
		this.contrasteOrganico=organico;
		this.contrasteInorganico=inorganico;
		this.contrasteBN=bn;
		this.id_examen=examen;
	}

	public int getId_imagen() {
		return id_imagen;
	}

	public void setId_imagen(int id_imagen) {
		this.id_imagen = id_imagen;
	}

	public byte[] getImagenPropia() {
		return imagenPropia;
	}

	public void setImagenPropia(byte[] imagenPropia) {
		this.imagenPropia = imagenPropia;
	}

	public byte[] getContrasteOrganico() {
		return contrasteOrganico;
	}

	public void setContrasteOrganico(byte[] contrasteOrganico) {
		this.contrasteOrganico = contrasteOrganico;
	}

	public byte[] getContrasteInorganico() {
		return contrasteInorganico;
	}

	public void setContrasteInorganico(byte[] contrasteInorganico) {
		this.contrasteInorganico = contrasteInorganico;
	}

	public byte[] getContrasteBN() {
		return contrasteBN;
	}

	public void setContrasteBN(byte[] contrasteBN) {
		this.contrasteBN = contrasteBN;
	}

	public int getId_examen() {
		return id_examen;
	}

	public void setId_examen(int id_examen) {
		this.id_examen = id_examen;
	}
	
	

}
