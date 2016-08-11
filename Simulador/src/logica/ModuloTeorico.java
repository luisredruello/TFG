package logica;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.io.FileUtils;

@XmlRootElement(name="modulo_teorico")
public class ModuloTeorico {

	private int id_modulo;
	private int nivel;
	private byte[] pdf;
	
	public ModuloTeorico(){}	
	
	public ModuloTeorico(int id_modulo, int nivel) {
		this.id_modulo = id_modulo;
		this.nivel = nivel;
		this.pdf = null;
	}
	
	public ModuloTeorico(int id, int n, byte[] p){
		this.id_modulo=id;
		this.nivel=n;
		this.pdf=p;
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
	
	public byte[] getPdf() {
		return pdf;
	}

	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
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
		if (pdf!=null){
			try{
				FileUtils.writeByteArrayToFile(new File(this.getPath()), pdf);
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
