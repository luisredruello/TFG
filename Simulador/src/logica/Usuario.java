package logica;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="usuario")
@XmlType(propOrder = { "dni", "nombre_completo", "fecha", "tipo", "pass" })
public class Usuario {	
	
	private String dni;
	private String fecha;
	private String nombre_completo;
	private String pass;
	private int tipo;
	
	
	    /**
	     * Default constructor.
	     */
	    public Usuario() {
	        //this(null, null, null, null, 0);
	    }

	    public Usuario(String doc, String f,String name, String contraseña, int type) {
	        this.nombre_completo = name;
	        this.pass = contraseña;
	        this.fecha = f;
	        this.dni = doc;
	        this.tipo = type;
	    }

		public String getDni() {
			return dni;
		}

		public void setDni(String dni) {
			this.dni = dni;
		}

		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		public String getNombre_completo() {
			return nombre_completo;
		}

		public void setNombre_completo(String nombre_completo) {
			this.nombre_completo = nombre_completo;
		}

		public String getPass() {
			return pass;
		}

		public void setPass(String pass) {
			this.pass = pass;
		}

		public int getTipo() {
			return tipo;
		}

		public void setTipo(int tipo) {
			this.tipo = tipo;
		}
	    
	    

}
