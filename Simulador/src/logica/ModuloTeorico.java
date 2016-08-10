package logica;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.io.FileUtils;

@XmlRootElement(name="modulo_teorico")
public class ModuloTeorico {

	private int id_modulo;
	private int nivel;
	private String PDF;
	private byte[] file;
	
	public ModuloTeorico(){}	
	
	public ModuloTeorico(int id_modulo, int nivel) {
		this.id_modulo = id_modulo;
		this.nivel = nivel;
		this.file = null;
	}
	
	public ModuloTeorico(int id, int n, byte[] p){
		this.id_modulo=id;
		this.nivel=n;
		this.file=p;
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

	/*public byte[] getPDF() {
		return file;
	}

	public void setPDF(byte[] pDF) {
		file = pDF;
	}*/	
	
	public String getPDF() {
		return PDF;
	}

	public void setPDF(String pDF) {
		PDF = pDF;
	}
	
	@XmlTransient
	public byte[] getFile() {
		return file;
	}
	
	public void setFile(byte[] file) {
		this.file = file;
	}

	public String toString(){
		return "Modulo "+Integer.toString(id_modulo);
	}
	
	public String getPath(){
		String path = ModuloTeorico.class.getClass().getResource("/pdf/c"+this.nivel+"/").getPath();
		String fileName = "teoria"+this.id_modulo+".pdf";
		String filePath = path + fileName;
		return filePath;
	}
	
	public boolean existeFichero(){
		File file = new File(this.getPath());
		return file.exists();
	}
	
	public void createPDFFile(){
		if (file!=null){
			try{
				FileUtils.writeByteArrayToFile(new File(this.getPath()), file);
			}
			catch (IOException e){
				e.printStackTrace();
			}
			catch(NullPointerException p){
				p.printStackTrace();
			}
		}
	}
	
	
}
