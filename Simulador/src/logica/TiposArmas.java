package logica;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tipos_armas")
@XmlAccessorType(XmlAccessType.FIELD)
public class TiposArmas {
	
	@XmlElement(name = "tipo_arma")
	private List<TipoArma> tipos;

	/**
	 * @return the tipos
	 */
	public List<TipoArma> getTipos() {
		return tipos;
	}

	/**
	 * @param tipos the tipos to set
	 */
	public void setTipos(List<TipoArma> tipos) {
		this.tipos = tipos;
	}

}
