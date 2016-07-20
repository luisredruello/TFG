package logica;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "certificados")
@XmlAccessorType(XmlAccessType.FIELD)
public class Certificaciones {
	
	@XmlElement(name = "certificado")
	private List<Certificacion> certificados;

	public List<Certificacion> getCertificados() {
		return certificados;
	}

	public void setCertificados(List<Certificacion> certificados) {
		this.certificados = certificados;
	}
	
	

}
