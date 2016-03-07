package logica;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {

	 private final StringProperty nombre;
	 private final StringProperty pass;
	 private final boolean esAdmin;
	
	
	    /**
	     * Default constructor.
	     */
	    public Usuario() {
	        this(null, null, false);
	    }

	    public Usuario(String nombre, String contrase�a, boolean type) {
	        this.nombre = new SimpleStringProperty(nombre);
	        this.pass = new SimpleStringProperty(contrase�a);
	        this.esAdmin = type;
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

		public boolean isEsAdmin() {
			return esAdmin;
		}
	    
	    




}
