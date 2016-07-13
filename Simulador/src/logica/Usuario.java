package logica;

import java.sql.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="usuario")
public class Usuario {

	private String DNI;
	private Date fecha;
	private String nombre;
	private String pass;
	private int tipo;
	
	
	    /**
	     * Default constructor.
	     */
	    public Usuario() {
	        //this(null, null, null, null, 0);
	    }

	    public Usuario(String dni, Date f,String nombre, String contraseña, int type) {
	        this.nombre = nombre;
	        this.pass = contraseña;
	        this.fecha = f;
	        this.DNI = dni;
	        this.tipo = type;
	    }

	    public String getDNI() {
			return DNI;
		}

		public Date getFecha() {
			return fecha;
		}

		public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String nombreProperty() {
	        return nombre;
	    }

	    public String getContraseña() {
	        return pass;
	    }

	    public void setContraseña(String contraseña) {
	        this.pass=contraseña;
	    }

	    public String contraseñaProperty() {
	        return pass;
	    }

		public int getType() {
			return tipo;
		}

}
