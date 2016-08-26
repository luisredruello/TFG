package logica;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "listaImagenes")
@XmlAccessorType(XmlAccessType.FIELD)
public class Imagenes {
	
	@XmlElement(name = "imagen")
	private List<Imagen> listaImagenes;

	/**
	 * @return the listaImagenes
	 */
	public List<Imagen> getImagenes() {
		return listaImagenes;
	}

	/**
	 * @param listaImagenes the listaImagenes to set
	 */
	public void setImagenes(List<Imagen> imagenes) {
		this.listaImagenes = imagenes;
	}

}
