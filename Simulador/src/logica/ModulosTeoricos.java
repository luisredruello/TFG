package logica;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "modulos_teoricos")
@XmlAccessorType(XmlAccessType.FIELD)
public class ModulosTeoricos {
	
	@XmlElement(name = "modulo_teorico")
	private List<ModuloTeorico> modulos;

	public List<ModuloTeorico> getModulos() {
		return modulos;
	}

	public void setModulos(List<ModuloTeorico> modulos) {
		this.modulos = modulos;
	}
	
	public int numModulos(){
		return this.modulos.size();
	}

}
