package logica;

import java.sql.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {

	private final StringProperty DNI;
	private final Date fecha;
	private final StringProperty nombre;
	private final StringProperty pass;
	private final int tipo;
	
	
	    /**
	     * Default constructor.
	     */
	    public Usuario() {
	        this(null, null, null, null, 0);
	    }

	    public Usuario(String dni, Date f,String nombre, String contrase�a, int type) {
	        this.nombre = new SimpleStringProperty(nombre);
	        this.pass = new SimpleStringProperty(contrase�a);
	        this.fecha = f;
	        this.DNI = new SimpleStringProperty(dni);
	        this.tipo = type;
	    }

	    public StringProperty getDNI() {
			return DNI;
		}

		public Date getFecha() {
			return fecha;
		}

		public String getNombre() {
	        return nombre.get();
	    }

	    public void setNombre(String nombre) {
	        this.nombre.set(nombre);
	    }

	    public StringProperty nombreProperty() {
	        return nombre;
	    }

	    public String getContrase�a() {
	        return pass.get();
	    }

	    public void setContrase�a(String contrase�a) {
	        this.pass.set(contrase�a);
	    }

	    public StringProperty contrase�aProperty() {
	        return pass;
	    }

		public int getType() {
			return tipo;
		}
	    
	    




}
